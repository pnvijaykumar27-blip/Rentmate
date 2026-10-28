package com.smamove.rentmate.repository

import com.smamove.rentmate.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepository {
    private val _currentUser = MutableStateFlow(
        User(
            id = "user_1",
            name = "Aarav Shah",
            email = "aarav.shah@email.com",
            phone = "+91 98765 43210",
            role = UserRole.TENANT,
            propertyName = "Green Residency",
            unitDetails = "Flat 204, Baner Road, Pune",
            monthlyRent = "₹15,000",
            leaseEnds = "30 September 2027",
            landlordName = "Rajesh Mehta"
        )
    )
    val currentUser: StateFlow<User> = _currentUser.asStateFlow()

    fun setRole(role: UserRole) {
        _currentUser.value = _currentUser.value.copy(
            role = role,
            name = if (role == UserRole.LANDLORD) "Rajesh Mehta" else "Aarav Shah"
        )
    }

    fun login(email: String, role: UserRole) {
        setRole(role)
    }

    fun signUp(name: String, email: String, phone: String, role: UserRole) {
        _currentUser.value = User(
            id = "user_${System.currentTimeMillis()}",
            name = name.ifBlank { if (role == UserRole.LANDLORD) "Rajesh Mehta" else "Aarav Shah" },
            email = email.ifBlank { "aarav.shah@email.com" },
            phone = phone.ifBlank { "+91 98765 43210" },
            role = role,
            propertyName = "Green Residency",
            unitDetails = "Flat 204, Baner Road, Pune",
            monthlyRent = "₹15,000",
            leaseEnds = "30 September 2027",
            landlordName = "Rajesh Mehta"
        )
    }
}

object PropertyRepository {
    private val propertiesList = listOf(
        Property("1", "Green Residency", "Flat 204, Baner Road, Pune", "Occupied", "₹15,000 / month"),
        Property("2", "Maple Heights", "Flat 302, Wakad, Pune", "Vacant", "₹15,000 / month"),
        Property("3", "Oak Residency", "Flat 101, Aundh, Pune", "Occupied", "₹15,000 / month"),
        Property("4", "Sunrise Apartments", "Flat 402, Kothrud, Pune", "Occupied", "₹15,000 / month")
    )
    val properties: List<Property> = propertiesList
}

object TenantRepository {
    private val tenantsList = listOf(
        Tenant("1", "AS", "Aarav Shah", "Green Residency · Flat 204", "+91 98765 43210", "Paid", "30 September 2027"),
        Tenant("2", "NK", "Neha Kulkarni", "Oak Residency · Flat 101", "+91 98220 45167", "Overdue", "31 August 2027"),
        Tenant("3", "KS", "Kabir Sethi", "Sunrise Apartments · Flat 402", "+91 98901 72345", "Overdue", "30 June 2027")
    )
    val tenants: List<Tenant> = tenantsList
}

object PaymentRepository {
    private val paymentsList = MutableStateFlow(
        listOf(
            RentalPayment("1", "Aarav Shah", "Green Residency · Flat 204", "₹15,000", "Paid", "Paid 5 October 2026 · UPI", "UPI"),
            RentalPayment("2", "Neha Kulkarni", "Oak Residency · Flat 101", "₹15,000", "Overdue", "Due 5 October 2026"),
            RentalPayment("3", "Kabir Sethi", "Sunrise Apartments · Flat 402", "₹15,000", "Overdue", "Due 5 October 2026")
        )
    )
    val payments: StateFlow<List<RentalPayment>> = paymentsList.asStateFlow()

    fun markAsPaid(paymentId: String, method: String) {
        paymentsList.value = paymentsList.value.map {
            if (it.id == paymentId) {
                it.copy(status = "Paid", dateOrDue = "Paid 5 October 2026 · $method", paymentMethod = method)
            } else it
        }
    }
}

object MaintenanceRepository {
    private val requestsList = MutableStateFlow(
        listOf(
            MaintenanceRequest(
                id = "RM-M1003",
                title = "Kitchen Tap Leakage",
                category = "Plumbing",
                date = "3 October 2026",
                priority = "Medium",
                status = "In Progress",
                description = "Tap drips continuously, even when fully closed.",
                note = "Plumber visit scheduled for 7 October."
            ),
            MaintenanceRequest(
                id = "RM-M1002",
                title = "Bedroom Door Lock",
                category = "Carpentry",
                date = "25 September 2026",
                priority = "Low",
                status = "Completed",
                description = "Bedroom door lock is jammed and hard to turn.",
                note = "Lock replaced on 27 September."
            )
        )
    )
    val requests: StateFlow<List<MaintenanceRequest>> = requestsList.asStateFlow()

    fun addRequest(title: String, category: String, description: String, priority: String) {
        val newReq = MaintenanceRequest(
            id = "RM-M100${requestsList.value.size + 4}",
            title = title.ifBlank { "Bedroom Light Flickering" },
            category = category,
            date = "6 October 2026",
            priority = priority,
            status = "Submitted",
            description = description.ifBlank { "The bedroom ceiling light flickers when switched on." }
        )
        requestsList.value = listOf(newReq) + requestsList.value
    }
}

object ChatRepository {
    private val messagesList = MutableStateFlow(
        listOf(
            ChatMessage("1", "Aarav", "Hi Rajesh, any update on the leaking kitchen tap?", "4:10 PM", isFromMe = true),
            ChatMessage("2", "Rajesh Mehta", "Hi Aarav, the plumber can visit tomorrow, 7 October, between 10 and 11 AM.", "4:20 PM", isFromMe = false),
            ChatMessage("3", "Aarav", "That works. I'll be home. Please ask him to call before arriving.", "4:22 PM", isFromMe = true),
            ChatMessage("4", "Rajesh Mehta", "Of course. I'll cover the repair. The plumber will visit tomorrow, 10–11 AM. I've updated your request.", "4:25 PM", isFromMe = false)
        )
    )
    val messages: StateFlow<List<ChatMessage>> = messagesList.asStateFlow()

    private val conversationsList = listOf(
        Conversation("1", "Rajesh Mehta", "RM", "Landlord · Maintenance", "The plumber will visit tomorrow, 10–11 AM.", "4:25 PM", 2),
        Conversation("2", "Rajesh Mehta", "RM", "Landlord · October rent", "Received ₹15,000. Thank you, Aarav!", "5 Oct", 0),
        Conversation("3", "Rajesh Mehta", "RM", "Landlord · Green Residency", "Your agreement is valid until Sep 2027.", "1 Oct", 0),
        Conversation("4", "Rajesh Mehta", "RM", "Landlord · Door lock repair", "Glad the new lock is working well.", "27 Sep", 0)
    )
    val conversations: List<Conversation> = conversationsList

    fun sendMessage(text: String) {
        if (text.isBlank()) return
        val newMsg = ChatMessage(
            id = System.currentTimeMillis().toString(),
            senderName = "Aarav",
            text = text,
            time = "4:26 PM",
            isFromMe = true
        )
        messagesList.value = messagesList.value + newMsg
    }
}
