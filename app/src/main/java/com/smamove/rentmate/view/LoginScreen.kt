package com.smamove.rentmate.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppOutlinedButton
import com.smamove.rentmate.components.AppTextField
import com.smamove.rentmate.utils.AppColors

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.height(32.dp))

            // Key Icon Circle
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFEFFDF5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.VpnKey,
                    contentDescription = null,
                    tint = AppColors.PrimaryGreen,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome Back!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Login to continue to RentMate",
                fontSize = 14.sp,
                color = AppColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(28.dp))

            AppTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                placeholder = "Enter your email",
                leadingIcon = Icons.Outlined.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Enter your password",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = !isPasswordVisible,
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = AppColors.TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Forgot Password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColors.PrimaryGreen,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onForgotPasswordClick() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppButton(
                text = "Login",
                onClick = { onLoginClick(email, password) }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = AppColors.BorderLight
                )
                Text(
                    text = "  or continue with  ",
                    fontSize = 12.sp,
                    color = AppColors.TextSecondary
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = AppColors.BorderLight
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            AppOutlinedButton(
                text = "Continue with Google",
                onClick = { /* Social login */ }
            )

            Spacer(modifier = Modifier.height(12.dp))

            AppOutlinedButton(
                text = "Continue with Apple",
                onClick = { /* Social login */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don’t have an account? ",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )
                Text(
                    text = "Sign Up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.PrimaryGreen,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
