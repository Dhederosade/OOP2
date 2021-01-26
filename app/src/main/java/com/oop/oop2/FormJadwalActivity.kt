package com.oop.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_jadwal.*


class FormJadwalActivity : AppCompatActivity() {
    var jadwal: Jadwal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_jadwal)

        val data = intent.getSerializableExtra("jadwal")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("items")

        if (data != null) {
            jadwal = data as Jadwal
            etJadwalNameEdit.setText(jadwal?.nama)
            etJadwalDescriptionEdit.setText(jadwal?.deskripsi)

            btActForm.setText("Edit")
        } else {
            btActForm.setText("Tambah")
            edit = false
        }

        btActForm.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("nama", etJadwalNameEdit.text.toString())
                changeData.put("deskripsi", etJadwalDescriptionEdit.text.toString())

                myRef.child(jadwal?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, JadwalActivity::class.java))
            } else {
                val key = myRef.push().key

                val newJadwal = Jadwal()
                newJadwal.nama = etJadwalNameEdit.text.toString()
                newJadwal.deskripsi = etJadwalDescriptionEdit.text.toString()

                myRef.child(key.toString()).setValue(newJadwal)
                finish()
                startActivity(Intent(this, JadwalActivity::class.java))
            }
        }
    }
}