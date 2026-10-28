package com.smamove.rentmate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.view.*
import com.smamove.rentmate.viewmodel.*

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = viewModel(),
    dashboardViewModel: DashboardViewModel = viewModel(),
    propertyViewModel: PropertyViewModel = viewModel(),
    tenantViewModel: TenantViewModel = viewModel(),
    paymentViewModel: PaymentViewModel = viewModel(),
    maintenanceViewModel: MaintenanceViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel()
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val payments by paymentViewModel.payments.collectAsState()
    val requests by maintenanceViewModel.requests.collectAsState()
    val messages by chatViewModel.messages.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStartedClick = { navController.navigate(Screen.RoleSelection.route) },
                onAlreadyHaveAccountClick = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(
                onRoleSelected = { role ->
                    authViewModel.selectRole(role)
                    navController.navigate(Screen.SignUp.route)
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { email, _ ->
                    authViewModel.login(email, currentUser.role)
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onSignUpClick = { navController.navigate(Screen.RoleSelection.route) },
                onForgotPasswordClick = { /* Forgot pass */ },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onSignUpClick = { name, email, phone, role ->
                    authViewModel.signUp(name, email, phone, role)
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Screen.Login.route) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Dashboard.route) {
            if (currentUser.role == UserRole.LANDLORD) {
                LandlordDashboardScreen(
                    user = currentUser,
                    properties = dashboardViewModel.propertiesList,
                    onNavigate = { route -> navController.navigate(route) }
                )
            } else {
                TenantDashboardScreen(
                    user = currentUser,
                    onNavigate = { route -> navController.navigate(route) }
                )
            }
        }

        composable(Screen.Properties.route) {
            PropertyListScreen(
                properties = propertyViewModel.propertiesList,
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Tenants.route) {
            TenantListScreen(
                tenants = tenantViewModel.tenantsList,
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Payments.route) {
            PaymentScreen(
                payments = payments,
                userRole = currentUser.role,
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.PayRent.route) {
            PayRentScreen(
                onPaySuccess = { method ->
                    paymentViewModel.processPayment("1", method)
                    navController.navigate(Screen.PaymentSuccessful.route)
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.PaymentSuccessful.route) {
            PaymentSuccessfulScreen(
                onDownloadReceiptClick = { navController.navigate(Screen.Receipt.route) },
                onBackToDashboardClick = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Receipt.route) {
            ReceiptScreen(
                userRole = currentUser.role,
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Maintenance.route) {
            MaintenanceScreen(
                requests = requests,
                userRole = currentUser.role,
                onNewRequestClick = { navController.navigate(Screen.NewMaintenance.route) },
                onRequestClick = { navController.navigate(Screen.MaintenanceStatus.route) },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.NewMaintenance.route) {
            NewMaintenanceRequestScreen(
                onSubmitClick = { title, cat, desc, priority ->
                    maintenanceViewModel.submitNewRequest(title, cat, desc, priority)
                    navController.navigate(Screen.RequestSubmitted.route)
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.MaintenanceStatus.route) {
            val req = requests.firstOrNull()
            if (req != null) {
                MaintenanceStatusScreen(
                    request = req,
                    userRole = currentUser.role,
                    onMessageLandlordClick = { navController.navigate(Screen.Chat.route) },
                    onBackClick = { navController.popBackStack() },
                    onNavigate = { route -> navController.navigate(route) }
                )
            }
        }

        composable(Screen.RequestSubmitted.route) {
            RequestSubmittedScreen(
                userRole = currentUser.role,
                onViewStatusClick = { navController.navigate(Screen.MaintenanceStatus.route) },
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Messages.route) {
            MessagesScreen(
                conversations = chatViewModel.conversations,
                userRole = currentUser.role,
                onConversationClick = { navController.navigate(Screen.Chat.route) },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Chat.route) {
            ChatScreen(
                messages = messages,
                userRole = currentUser.role,
                onSendMessage = { text -> chatViewModel.sendMessage(text) },
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                user = currentUser,
                onSettingsClick = { navController.navigate(Screen.Settings.route) },
                onLogoutClick = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                userRole = currentUser.role,
                onBackClick = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) }
            )
        }
    }
}
