# QR Attendance Android App (Firebase-ready)

This Android Studio project is a complete QR-based attendance app skeleton.
It includes:
- LoginActivity (Firebase Authentication expected)
- TeacherDashboard -> GenerateQRActivity (creates QR and stores session in Firestore)
- StudentScannerActivity (scans QR and writes attendance to Firestore)

IMPORTANT: You must add your **google-services.json** file to `app/` directory (from your Firebase console)
before opening the project in Android Studio.

## How to run
1. Unzip the project folder.
2. Open Android Studio -> Choose "Open an existing project" -> select the unzipped folder.
3. Add `google-services.json` (Firebase) into `app/`.
   - Create a Firebase project -> Add Android app with package name `com.example.qrattendance`
   - Download `google-services.json` and copy to `app/`
   - Enable Firebase Authentication (Email/Password) and Cloud Firestore in the Firebase console.
4. Let Gradle sync. If plugins ask to update, accept recommended versions.
5. Run on an emulator or real device.

## Notes
- This project uses zxing-android-embedded for QR generation and scanning.
- Login role detection is naive: any email containing 'teacher' goes to teacher dashboard; others go to student scanner.
- Replace student id logic in StudentScannerActivity with actual FirebaseAuth UID for production.

## If you want
Reply: "Bro add google-services instructions and fill code for testing" or ask me to fill anything else.
