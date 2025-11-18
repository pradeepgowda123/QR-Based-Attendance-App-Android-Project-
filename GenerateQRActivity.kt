package com.example.qrattendance

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.util.UUID

class GenerateQRActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val sessionId = UUID.randomUUID().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_qr)

        val qrImage = findViewById<ImageView>(R.id.qrImage)

        val text = sessionId
        val barcodeEncoder = BarcodeEncoder()
        val bitmap: Bitmap = barcodeEncoder.encodeBitmap(text, com.google.zxing.BarcodeFormat.QR_CODE, 800, 800)
        qrImage.setImageBitmap(bitmap)

        val session = hashMapOf(
            "sessionId" to sessionId,
            "timestamp" to System.currentTimeMillis()
        )
        db.collection("attendance_sessions").document(sessionId).set(session)
    }
}
