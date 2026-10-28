package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppTextField
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun SignUpScreen(
    onSignUpClick: (String, String, String, UserRole) -> Unit,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf(UserRole.TENANT) }
    var isTermsAccepted by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, AppColors.BorderLight, RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = AppColors.TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(AppColors.PrimaryGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "RentMate",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Create your account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Your smarter rental journey starts here.",
                fontSize = 14.sp,
                color = AppColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full Name",
                placeholder = "Enter your full name",
                leadingIcon = Icons.Outlined.Person
            )

            Spacer(modifier = Modifier.height(14.dp))

            AppTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                placeholder = "Enter your email",
                leadingIcon = Icons.Outlined.Email
            )

            Spacer(modifier = Modifier.height(14.dp))

            AppTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Phone Number",
                placeholder = "Enter your phone number",
                leadingIcon = Icons.Outlined.Phone
            )

            Spacer(modifier = Modifier.height(14.dp))

            AppTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Create a password",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(14.dp))

            AppTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                placeholder = "Confirm your password",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Select Role Header & Options
            Text(
                text = "Select Role",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tenant Choice
                val isTenant = selectedRole == UserRole.TENANT
                Surface(
                    onClick = { selectedRole = UserRole.TENANT },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = if (isTenant) Color(0xFFEFFDF5) else Color.White,
                    border = BorderStroke(
                        1.dp,
                        if (isTenant) AppColors.PrimaryGreen else AppColors.BorderLight
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = if (isTenant) AppColors.PrimaryGreen else AppColors.TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tenant",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isTenant) AppColors.PrimaryGreen else AppColors.TextSecondary
                        )
                        if (isTenant) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                tint = AppColors.PrimaryGreen,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                // Landlord Choice
                val isLandlord = selectedRole == UserRole.LANDLORD
                Surface(
                    onClick = { selectedRole = UserRole.LANDLORD },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = if (isLandlord) Color(0xFFEFFDF5) else Color.White,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isLandlord) AppColors.PrimaryGreen else AppColors.BorderLight
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Apartment,
                            contentDescription = null,
                            tint = if (isLandlord) AppColors.PrimaryGreen else AppColors.TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Landlord",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isLandlord) AppColors.PrimaryGreen else AppColors.TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Terms Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isTermsAccepted,
                    onCheckedChange = { isTermsAccepted = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColors.PrimaryGreen
                    )
                )
                Text(
                    text = "I agree to the ",
                    fontSize = 13.sp,
                    color = AppColors.TextSecondary
                )
                Text(
                    text = "Terms & Conditions",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.PrimaryGreen
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            AppButton(
                text = "Create Account",
                onClick = { onSignUpClick(fullName, email, phone, selectedRole) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.PrimaryGreen,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
