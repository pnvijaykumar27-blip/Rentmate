package com.smamove.rentmate.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.R
import com.smamove.rentmate.components.AppButton
import com.smamove.rentmate.components.AppOutlinedButton
import com.smamove.rentmate.utils.AppColors

@Composable
fun WelcomeScreen(
    onGetStartedClick: () -> Unit,
    onAlreadyHaveAccountClick: () -> Unit
) {
    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Logo Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(AppColors.PrimaryGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "RentMate Logo",
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "RentMate",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Smart Rental Management",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )
            }

            // House Image Container with Pill Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFE2F1E7)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_welcome_house),
                    contentDescription = "Rental House Illustration",
                    modifier = Modifier.fillMaxSize()
                )

                // "A simpler way to rent" pill
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        tint = AppColors.PrimaryGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "A simpler way to rent",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.TextPrimary
                    )
                }
            }

            // Title & Subtitle
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Manage your rentals\nthe smart way",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary,
                    textAlign = TextAlign.Center,
                    lineHeight = 34.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Manage properties, tenants, payments and\nmaintenance in one place.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            }

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AppButton(
                    text = "Get Started",
                    onClick = onGetStartedClick
                )
                Spacer(modifier = Modifier.height(12.dp))
                AppOutlinedButton(
                    text = "I already have an account",
                    onClick = onAlreadyHaveAccountClick
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
