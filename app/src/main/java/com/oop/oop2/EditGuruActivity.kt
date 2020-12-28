package com.oop.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.oop.oop2.Database.AppRoomDB
import com.oop.oop2.Database.Constant
import  com.oop.oop2.Database.Guru
import com.oop.oop2.R
import kotlinx.android.synthetic.main.activity_edit_guru.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditGuruActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var guruId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_guru)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveGuru.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.guruDao().addGuru(
                    Guru(0, txt_nama.text.toString(), txt_pengampu.text.toString())
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
                btn_saveGuru.visibility = View.GONE
                getGuru()
            }
        }
    }

    fun getGuru() {
        guruId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val guru =  db.guruDao().getGuru( guruId )[0]
            txt_nama.setText( guru.nama )
            txt_pengampu.setText( guru.pengampu )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}