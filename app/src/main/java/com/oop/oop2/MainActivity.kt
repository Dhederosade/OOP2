package com.oop.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oop.oop2.SiswaActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_siswa.setOnClickListener{
            val intent = Intent(this, SiswaActivity::class.java)
            startActivity(intent)
        }

        btn_guru.setOnClickListener{
            val intent = Intent(this, GuruActivity::class.java)
            startActivity(intent)
        }
    }
}