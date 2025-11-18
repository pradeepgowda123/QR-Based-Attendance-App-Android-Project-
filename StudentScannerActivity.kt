package com.example.qrattendance

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlin.random.Random

class StudentScannerActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val qrLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            markAttendance(result.contents)
        } else {
            Toast.makeText(this, "No QR found", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        // nothing special
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_scanner)

        val scanBtn = findViewById<Button>(R.id.scanBtn)
        scanBtn.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            val options = ScanOptions()
            options.setPrompt("Scan session QR")
            options.setBeepEnabled(true)
            qrLauncher.launch(options)
        }
    }

    private fun markAttendance(sessionId: String) {
        // using random student id placeholder; in real app use FirebaseAuth.getCurrentUser().uid
        val stuId = "student_" + Random.nextInt(1000,9999)
        val data = hashMapOf(
            "studentId" to stuId,
            "sessionId" to sessionId,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("attendance").add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Attendance marked!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error marking attendance", Toast.LENGTH_SHORT).show()
            }
    }
}
