# RentMate AI — House Rental Management Application

[![Android Studio](https://img.shields.io/badge/Android%20Studio-2026.1.4-green.svg)](https://developer.android.com/studio)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-blue.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2026.02.01-brightgreen.svg)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Material%203-Latest-purple.svg)](https://m3.material.io/)
[![Architecture](https://img.shields.io/badge/Architecture-MVVM-orange.svg)](https://developer.android.com/topic/architecture)

**RentMate AI** is an Android house rental management application built with modern **Kotlin** and **Jetpack Compose**. It reproduces precise Figma designs with high visual fidelity, seamless navigation, and clean **MVVM architecture**.

---

## Key Features

### Dual Role Support
- **Tenant Experience:** Personalized dashboard displaying monthly rent, due date countdown, rent status, maintenance requests glance, agreement active status, and quick action circular buttons.
- **Landlord Experience:** Portfolio overview with 2x2 statistics grid (Total Properties, Occupied, Vacant, Pending Rent), properties list preview, rent collection progress bar, and maintenance request tracker.

### Rent Collection & Payments
- **Payment Gateway UI:** Support for multiple payment methods including UPI (Google Pay, PhonePe, Paytm), Debit/Credit Cards, and Net Banking.
- **Instant Payment Receipts:** Digital rent receipt generation with payment verification badges, transaction IDs, and downloadable receipt cards.
- **Rent Collection Analytics:** Breakdown of paid, pending, and overdue rent with status pills and progress bars.

### Maintenance & Service Requests
- **Interactive Request Flow:** Form creation with category selection (Plumbing, Electrical, Carpentry), priority selector (Low, Medium, High), and photo upload capability.
- **4-Step Status Timeline:** Real-time visual tracking from *Submitted* $\rightarrow$ *Acknowledged* $\rightarrow$ *Repair Scheduled* $\rightarrow$ *Visit & Resolution*.

### In-App Messaging & Communication
- **Direct Landlord Chat:** Threaded messaging linked directly to specific rental properties and maintenance tickets.
- **Unread Counters & Search:** Message search bar with category badges and unread indicator bubbles.

### Account & Settings Management
- **Profile & Lease Details:** Overview of tenant contact information, active lease agreement end dates, monthly rent, and landlord details.
- **Customization Settings:** Preferences for dark mode, notification toggles (Rent Reminders, Maintenance Updates, Message Notifications), and security preferences (Biometric Login).

---

## 🎨 Figma Design System & Visual Highlights

- **Brand Color Palette:** Emerald Green (`#16A34A`), Dark Charcoal Text (`#111827`), Light Green Tint Cards (`#EFFDF5`), and Status Badges (Paid, Overdue, Occupied, Vacant).
- **Typography & Components:** Standardized `AppButton`, `AppOutlinedButton`, `AppTextField`, `StatusBadge`, and `AppBottomNavigation`.
- **Pixel-Perfect Icons:** Integrated Compose Material Vector Icons replacing approximate emojis for a crisp, professional UI.

---

## 🏗️ Architecture & Tech Stack

RentMate AI is designed following official Android Architecture Guidelines:

```
                  +------------------------+
                  |    UI Layer (Views)    |
                  |    Jetpack Compose     |
                  +-----------+------------+
                              |
                              v
                  +------------------------+
                  |       ViewModel        |
                  | StateFlow / Coroutines |
                  +-----------+------------+
                              |
                              v
                  +------------------------+
                  |   Repository Layer     |
                  |    Data & Models       |
                  +------------------------+
```

- **UI Framework:** 100% Jetpack Compose (Zero XML layout files)
- **Design Components:** Material 3
- **Navigation:** Navigation Compose (`NavHost` & `NavGraph`)
- **State Management:** `StateFlow` and Kotlin Coroutines
- **Language:** Kotlin 2.2.10
- **Min SDK:** 24 (Android 7.0) | **Target SDK:** 37

---

## 📱 Screen Inventory & Mapping

| Figma Screen Name | Kotlin Composable File | Route | Description |
| :--- | :--- | :--- | :--- |
| **Welcome / Splash** | [`WelcomeScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/WelcomeScreen.kt) | `welcome` | Splash screen with house illustration & onboarding actions |
| **Role Selection** | [`RoleSelectionScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/RoleSelectionScreen.kt) | `role_selection` | Choice card selector for Tenant vs Landlord |
| **Login** | [`LoginScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/LoginScreen.kt) | `login` | Login form with social sign-in options |
| **Sign Up** | [`SignUpScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/SignUpScreen.kt) | `signup` | Account registration with role toggle & terms checkbox |
| **Tenant Dashboard** | [`TenantDashboardScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/TenantDashboardScreen.kt) | `dashboard` | Tenant main hub with rent due banner & glance cards |
| **Landlord Dashboard** | [`LandlordDashboardScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/LandlordDashboardScreen.kt) | `dashboard` | Landlord portfolio overview & stats grid |
| **Properties** | [`PropertyListScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/PropertyListScreen.kt) | `properties` | Property portfolio cards list with status pills |
| **Tenants** | [`TenantListScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/TenantListScreen.kt) | `tenants` | Active tenant list with lease details & contact info |
| **Rent Collection** | [`PaymentScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/PaymentScreen.kt) | `payments` | Rent payment status list with filter tabs |
| **Pay Rent** | [`PayRentScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/PayRentScreen.kt) | `pay_rent` | Payment gateway screen with method selection |
| **Payment Successful** | [`PaymentSuccessfulScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/PaymentSuccessfulScreen.kt) | `payment_successful` | Success confirmation card with receipt download |
| **Rent Receipt** | [`ReceiptScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/ReceiptScreen.kt) | `receipt` | Digital receipt card for payment record |
| **Maintenance** | [`MaintenanceScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/MaintenanceScreen.kt) | `maintenance` | Requests list with priority & category tags |
| **New Request** | [`NewMaintenanceRequestScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/NewMaintenanceRequestScreen.kt) | `new_maintenance` | Form to submit maintenance issues with photo upload |
| **Request Status** | [`MaintenanceStatusScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/MaintenanceStatusScreen.kt) | `maintenance_status` | Detailed 4-step repair timeline & messaging link |
| **Request Submitted** | [`RequestSubmittedScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/RequestSubmittedScreen.kt) | `request_submitted` | Confirmation screen post submission |
| **Messages** | [`MessagesScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/MessagesScreen.kt) | `messages` | Conversation list with search & unread badges |
| **Chat** | [`ChatScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/ChatScreen.kt) | `chat` | Threaded chat view with issue banner header |
| **My Profile** | [`ProfileScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/ProfileScreen.kt) | `profile` | Profile & lease details with navigation links |
| **Settings** | [`SettingsScreen.kt`](file:///C:/Users/SCAD LAB/AndroidStudioProjects/RentMate/app/src/main/java/com/smamove/rentmate/view/SettingsScreen.kt) | `settings` | Preferences, notifications, and security settings |

---

## 📂 Project Directory Structure

```
app/src/main/java/com/smamove/rentmate/
├── MainActivity.kt                      # Main activity & Compose root
├── navigation/
│   ├── Screen.kt                        # Sealed navigation routes
│   └── AppNavigation.kt                  # App navigation graph
├── model/
│   └── Models.kt                        # Data models
├── repository/
│   └── AppRepositories.kt               # Data repositories & mock data sources
├── viewmodel/
│   └── AppViewModels.kt                 # ViewModels for presentation logic
├── utils/
│   └── AppColors.kt                     # Application color palette
├── components/
│   └── AppComponents.kt                 # Shared UI components
└── view/                                # Separate Composable screen files
    ├── WelcomeScreen.kt
    ├── RoleSelectionScreen.kt
    ├── LoginScreen.kt
    ├── SignUpScreen.kt
    ├── TenantDashboardScreen.kt
    ├── LandlordDashboardScreen.kt
    ├── PropertyListScreen.kt
    ├── TenantListScreen.kt
    ├── PaymentScreen.kt
    ├── PayRentScreen.kt
    ├── PaymentSuccessfulScreen.kt
    ├── ReceiptScreen.kt
    ├── MaintenanceScreen.kt
    ├── NewMaintenanceRequestScreen.kt
    ├── MaintenanceStatusScreen.kt
    ├── RequestSubmittedScreen.kt
    ├── MessagesScreen.kt
    ├── ChatScreen.kt
    ├── ProfileScreen.kt
    └── SettingsScreen.kt
```

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio:** 2026.1.4 or higher
- **JDK:** Java 11 or higher
- **Android Device / Emulator:** API Level 24+ (Android 7.0+)

### Building and Running
1. Clone the repository:
   ```bash
   git clone https://github.com/pnvijaykumar27-blip/Rentmate.git
   cd Rentmate
   ```
2. Open the project in **Android Studio**.
3. Allow Gradle to sync dependencies.
4. Select your connected Android device or emulator.
5. Click **Run (`Shift + F10`)** or build via terminal:
   ```bash
   ./gradlew assembleDebug
   ```

---

## 📄 License
This project is licensed under the MIT License.
