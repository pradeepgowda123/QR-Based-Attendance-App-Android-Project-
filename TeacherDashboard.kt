package com.example.qrattendance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TeacherDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_dashboard)

        val genBtn = findViewById<Button>(R.id.generateQRbtn)
        genBtn.setOnClickListener {
            startActivity(Intent(this, GenerateQRActivity::class.java))
        }
    }
}
