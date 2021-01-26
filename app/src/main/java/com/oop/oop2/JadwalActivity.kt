package com.oop.oop2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_jadwal.*


class JadwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("items")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                val dataArray = ArrayList<Jadwal>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Jadwal::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListJadwal.adapter = JadwalAdapter(dataArray, object : JadwalAdapter.OnClick {
                    override fun edit(jadwal: Jadwal?) {
                        val intent = Intent(this@JadwalActivity, FormJadwalActivity::class.java)
                        intent.putExtra("jadwal", jadwal)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@JadwalActivity).apply {
                            setTitle("Hapus ?")
                            setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                                myRef?.child(key.toString())?.removeValue()
//                                Toast.makeText(this@MainActivity, key, Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
                        }.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })

        btAddJadwal.setOnClickListener {
            startActivity(Intent(this@JadwalActivity, FormJadwalActivity::class.java))
        }
    }
}