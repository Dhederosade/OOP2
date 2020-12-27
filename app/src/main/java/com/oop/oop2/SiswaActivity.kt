package com.oop.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.oop.oop2.Database.AppRoomDB
import com.oop.oop2.Database.Constant
import com.oop.oop2.Database.Siswa
import kotlinx.android.synthetic.main.activity_siswa.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SiswaActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var siswaAdapter: SiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siswa)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadSiswa()
    }

    fun loadSiswa(){
        CoroutineScope(Dispatchers.IO).launch {
            val allSiswa = db.siswaDao().getAllSiswa()
            Log.d("SiswaActivity", "dbResponse: $allSiswa")
            withContext(Dispatchers.Main) {
                siswaAdapter.setData(allSiswa)
            }
        }
    }

    fun setupListener() {
        btn_createSiswa.setOnClickListener {
           intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        siswaAdapter = SiswaAdapter(arrayListOf(), object: SiswaAdapter.OnAdapterListener {
            override fun onClick(siswa: Siswa) {
                intentEdit(siswa.id, Constant.TYPE_READ)
            }

            override fun onDelete(siswa: Siswa) {
                deleteDialog(siswa)
            }

        })
        list_siswa.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = siswaAdapter
        }
    }

    fun intentEdit(siswaId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditSiswaActivity::class.java)
                .putExtra("intent_id", siswaId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(siswa: Siswa) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.siswaDao().deleteSiswa(siswa)
                    loadSiswa()
                }
            }
        }
        alertDialog.show()
    }
}