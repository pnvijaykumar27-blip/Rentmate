package com.smamove.rentmate.model

enum class UserRole {
    TENANT,
    LANDLORD
}

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val role: UserRole = UserRole.TENANT,
    val propertyName: String = "",
    val unitDetails: String = "",
    val monthlyRent: String = "",
    val leaseEnds: String = "",
    val landlordName: String = ""
)

data class Property(
    val id: String,
    val name: String,
    val address: String,
    val status: String, // "Occupied", "Vacant"
    val rentAmount: String
)

data class Tenant(
    val id: String,
    val initials: String,
    val name: String,
    val propertyDetails: String,
    val phone: String,
    val status: String, // "Paid", "Overdue"
    val leaseEnds: String
)

data class RentalPayment(
    val id: String,
    val tenantName: String,
    val propertyDetails: String,
    val amount: String,
    val status: String, // "Paid", "Overdue", "Pending"
    val dateOrDue: String,
    val paymentMethod: String = ""
)

data class MaintenanceRequest(
    val id: String,
    val title: String,
    val category: String,
    val date: String,
    val priority: String, // "Low", "Medium", "High"
    val status: String, // "Submitted", "In Progress", "Completed"
    val description: String,
    val note: String = ""
)

data class ChatMessage(
    val id: String,
    val senderName: String,
    val text: String,
    val time: String,
    val isFromMe: Boolean,
    val isRead: Boolean = true
)

data class Conversation(
    val id: String,
    val name: String,
    val initials: String,
    val category: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0
)
