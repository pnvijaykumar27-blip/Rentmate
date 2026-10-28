package com.smamove.rentmate.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object RoleSelection : Screen("role_selection")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Dashboard : Screen("dashboard")
    object Properties : Screen("properties")
    object Tenants : Screen("tenants")
    object Payments : Screen("payments")
    object PayRent : Screen("pay_rent")
    object PaymentSuccessful : Screen("payment_successful")
    object Receipt : Screen("receipt")
    object Maintenance : Screen("maintenance")
    object NewMaintenance : Screen("new_maintenance")
    object MaintenanceStatus : Screen("maintenance_status")
    object RequestSubmitted : Screen("request_submitted")
    object Messages : Screen("messages")
    object Chat : Screen("chat")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}
