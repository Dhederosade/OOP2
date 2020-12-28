package com.oop.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oop.oop2.Database.Guru

import com.oop.oop2.R
import kotlinx.android.synthetic.main.adapter_guru.view.*


class GuruAdapter (private val AllGuru: ArrayList<Guru>, private val listener: OnAdapterListener) : RecyclerView.Adapter<GuruAdapter.GuruViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuruViewHolder {
        return GuruViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_guru, parent, false)
        )
    }

    override fun getItemCount() = AllGuru.size

    override fun onBindViewHolder(holder: GuruViewHolder, position: Int) {
        val guru = AllGuru[position]
        holder.view.text_nama.text =guru.nama
        holder.view.text_nama.setOnClickListener {
            listener.onClick(guru)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(guru)
        }
    }

    class GuruViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Guru>) {
        AllGuru.clear()
        AllGuru.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(guru: Guru)
        fun onDelete(guru: Guru)
    }

}