package com.smamove.rentmate.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smamove.rentmate.model.UserRole
import com.smamove.rentmate.utils.AppColors

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    backgroundColor: Color = AppColors.PrimaryGreen,
    contentColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun AppOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, AppColors.BorderLight),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = AppColors.TextPrimary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = AppColors.TextMuted,
                    fontSize = 14.sp
                )
            },
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = AppColors.TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            trailingIcon = trailingIcon,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppColors.InputBackground,
                unfocusedContainerColor = AppColors.InputBackground,
                focusedBorderColor = AppColors.PrimaryGreen,
                unfocusedBorderColor = AppColors.BorderLight,
                focusedTextColor = AppColors.TextPrimary,
                unfocusedTextColor = AppColors.TextPrimary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AppTopHeader(
    onBackClick: (() -> Unit)? = null,
    showNotificationBell: Boolean = true,
    onNotificationClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
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
        } else {
            Spacer(modifier = Modifier.width(44.dp))
        }

        // RentMate Logo
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppColors.PrimaryGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "RentMate Logo",
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

        if (showNotificationBell) {
            IconButton(
                onClick = { onNotificationClick?.invoke() },
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, AppColors.BorderLight, RoundedCornerShape(12.dp))
            ) {
                Box {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = AppColors.TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    // Dot
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(AppColors.PrimaryGreen, CircleShape)
                            .align(Alignment.TopEnd)
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.width(44.dp))
        }
    }
}

@Composable
fun StatusBadge(
    status: String,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor) = when (status.lowercase()) {
        "paid", "occupied", "in progress" -> Pair(AppColors.PaidBackground, AppColors.PaidText)
        "overdue", "submitted" -> Pair(AppColors.OverdueBackground, AppColors.OverdueText)
        "vacant", "completed" -> Pair(AppColors.VacantBackground, AppColors.VacantText)
        else -> Pair(AppColors.PaidBackground, AppColors.PaidText)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(textColor, CircleShape)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = status,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

enum class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    HOME("dashboard", "Home", Icons.Outlined.Home),
    PROPERTIES("properties", "Properties", Icons.Outlined.Apartment),
    TENANTS("tenants", "Tenants", Icons.Outlined.People),
    PAYMENTS("payments", "Payments", Icons.Outlined.AccountBalanceWallet),
    MAINTENANCE("maintenance", "Maintenance", Icons.Outlined.Build),
    MESSAGES("messages", "Messages", Icons.Outlined.ChatBubbleOutline),
    PROFILE("profile", "Profile", Icons.Outlined.PersonOutline)
}

@Composable
fun AppBottomNavigation(
    currentRoute: String,
    userRole: UserRole,
    onNavigate: (String) -> Unit
) {
    val items = if (userRole == UserRole.LANDLORD) {
        listOf(
            BottomNavItem.HOME,
            BottomNavItem.PROPERTIES,
            BottomNavItem.TENANTS,
            BottomNavItem.PAYMENTS,
            BottomNavItem.MAINTENANCE
        )
    } else {
        listOf(
            BottomNavItem.HOME,
            BottomNavItem.PAYMENTS,
            BottomNavItem.MAINTENANCE,
            BottomNavItem.MESSAGES,
            BottomNavItem.PROFILE
        )
    }

    Surface(
        shadowElevation = 8.dp,
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                val tint = if (isSelected) AppColors.PrimaryGreen else AppColors.TextSecondary

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onNavigate(item.route) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = tint,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = tint
                    )
                }
            }
        }
    }
}
