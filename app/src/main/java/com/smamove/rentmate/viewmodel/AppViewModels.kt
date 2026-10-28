package com.smamove.rentmate.viewmodel

import androidx.lifecycle.ViewModel
import com.smamove.rentmate.model.*
import com.smamove.rentmate.repository.*
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    val currentUser: StateFlow<User> = AuthRepository.currentUser

    fun selectRole(role: UserRole) {
        AuthRepository.setRole(role)
    }

    fun login(email: String, role: UserRole) {
        AuthRepository.login(email, role)
    }

    fun signUp(name: String, email: String, phone: String, role: UserRole) {
        AuthRepository.signUp(name, email, phone, role)
    }
}

class DashboardViewModel : ViewModel() {
    val currentUser: StateFlow<User> = AuthRepository.currentUser
    val propertiesList: List<Property> = PropertyRepository.properties
    val tenantsList: List<Tenant> = TenantRepository.tenants
}

class PropertyViewModel : ViewModel() {
    val propertiesList: List<Property> = PropertyRepository.properties
}

class TenantViewModel : ViewModel() {
    val tenantsList: List<Tenant> = TenantRepository.tenants
}

class PaymentViewModel : ViewModel() {
    val payments: StateFlow<List<RentalPayment>> = PaymentRepository.payments

    fun processPayment(paymentId: String, method: String) {
        PaymentRepository.markAsPaid(paymentId, method)
    }
}

class MaintenanceViewModel : ViewModel() {
    val requests: StateFlow<List<MaintenanceRequest>> = MaintenanceRepository.requests

    fun submitNewRequest(title: String, category: String, description: String, priority: String) {
        MaintenanceRepository.addRequest(title, category, description, priority)
    }
}

class ChatViewModel : ViewModel() {
    val messages: StateFlow<List<ChatMessage>> = ChatRepository.messages
    val conversations: List<Conversation> = ChatRepository.conversations

    fun sendMessage(text: String) {
        ChatRepository.sendMessage(text)
    }
}

class ProfileViewModel : ViewModel() {
    val currentUser: StateFlow<User> = AuthRepository.currentUser
}
