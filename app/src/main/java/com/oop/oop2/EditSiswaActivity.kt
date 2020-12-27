package com.oop.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.oop.oop2.Database.AppRoomDB
import com.oop.oop2.Database.Constant
import com.oop.oop2.Database.Siswa
import com.oop.oop2.R
import kotlinx.android.synthetic.main.activity_edit_siswa.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditSiswaActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var siswaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_siswa)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveSiswa.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.siswaDao().addSiswa(
                    Siswa(0, txt_nama.text.toString(), Integer.parseInt(txt_nis.text.toString()), Integer.parseInt(txt_kelas.text.toString()) )
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                btn_saveSiswa.visibility = View.GONE
                getSiswa()
            }
        }
    }

    fun getSiswa() {
        siswaId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val siswas =  db.siswaDao().getSiswa( siswaId )[0]
            txt_nama.setText( siswas.merk )
            txt_nis.setText( siswas.stok.toString() )
            txt_kelas.setText( siswas.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}