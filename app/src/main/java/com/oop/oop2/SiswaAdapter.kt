package com.oop.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.oop.oop2.Database.Siswa

import kotlinx.android.synthetic.main.adapter_siswa.view.*

class SiswaAdapter (private val AllSiswa: ArrayList<Siswa>, private val listener: OnAdapterListener) : RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaViewHolder {
        return SiswaViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_siswa, parent, false)
        )
    }

    override fun getItemCount() = AllSiswa.size

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
        val siswa = AllSiswa[position]
        holder.view.text_merk.text = siswa.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(siswa)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(siswa)
        }
    }

    class SiswaViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Siswa>) {
        AllSiswa.clear()
        AllSiswa.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(siswa: Siswa)
        fun onDelete(siswa: Siswa)
    }
}