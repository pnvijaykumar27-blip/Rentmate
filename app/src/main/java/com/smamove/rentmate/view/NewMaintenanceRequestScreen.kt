package com.smamove.rentmate.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.smamove.rentmate.components.AppTopHeader
import com.smamove.rentmate.utils.AppColors

@Composable
fun NewMaintenanceRequestScreen(
    onSubmitClick: (String, String, String, String) -> Unit,
    onBackClick: () -> Unit
) {
    var issueTitle by remember { mutableStateOf("Bedroom Light Flickering") }
    var category by remember { mutableStateOf("Electrical") }
    var description by remember {
        mutableStateOf("The bedroom ceiling light flickers when switched on. Please check the wiring and light fitting.")
    }
    var priority by remember { mutableStateOf("Medium") }

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            AppTopHeader(onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "New Request",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tell us what needs a little attention.",
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Property Pill Tag
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFEFFDF5),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            tint = AppColors.PrimaryGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Green Residency · Flat 204",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                AppTextField(
                    value = issueTitle,
                    onValueChange = { issueTitle = it },
                    label = "Issue title",
                    placeholder = "e.g. Leaking Tap"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = "Category",
                    placeholder = "Select category",
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                            tint = AppColors.TextSecondary
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = "Description",
                    placeholder = "Describe the issue...",
                    singleLine = false,
                    maxLines = 4,
                    modifier = Modifier.heightIn(min = 100.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Priority",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val priorities = listOf("Low", "Medium", "High")
                    priorities.forEach { p ->
                        val isSelected = priority == p
                        Surface(
                            onClick = { priority = p },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) Color(0xFFEFFDF5) else Color.White,
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) AppColors.PrimaryGreen else AppColors.BorderLight
                            )
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Outlined.Check,
                                        contentDescription = null,
                                        tint = AppColors.PrimaryGreen,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text(
                                    text = p,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) AppColors.PrimaryGreen else AppColors.TextSecondary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Upload Image",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.TextPrimary
                    )
                    Text(
                        text = "Optional",
                        fontSize = 12.sp,
                        color = AppColors.TextMuted
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Upload Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppColors.InputBackground)
                        .border(1.dp, AppColors.BorderLight, RoundedCornerShape(16.dp))
                        .clickable { /* upload */ },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Outlined.AddAPhoto,
                            contentDescription = null,
                            tint = AppColors.PrimaryGreen,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tap to upload a photo",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.PrimaryGreen
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "JPG or PNG · Up to 5 MB",
                            fontSize = 11.sp,
                            color = AppColors.TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                AppButton(
                    text = "Submit Request",
                    onClick = { onSubmitClick(issueTitle, category, description, priority) }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
