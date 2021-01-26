package com.oop.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_jadwal.view.*


class JadwalAdapter(val jadwal : ArrayList<Jadwal>, val onClick : OnClick) : RecyclerView.Adapter<JadwalAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = jadwal.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(jadwal.get(position))
        holder.itemView.btDeleteJadwal.setOnClickListener {
            onClick.delete(jadwal.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(jadwal.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jadwal: Jadwal) {
            itemView.tvJadwalName.text = jadwal.nama
            itemView.tvJadwalDescription.text = jadwal.deskripsi
        }
    }

    interface OnClick {
        fun delete(key: String?)
        fun edit(jadwal: Jadwal?)
    }

}