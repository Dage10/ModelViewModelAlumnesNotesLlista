package com.daviddam.modelviewmodelalumnesnotesllista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Alumne

class AlumneAdapter(
    private var alumnes: List<Alumne>,
    private val onItemClick: (Alumne) -> Unit
) : RecyclerView.Adapter<AlumneAdapter.AlumneViewHolder>() {

    fun updateList(newAlumnes: List<Alumne>) {
        alumnes = newAlumnes
        notifyDataSetChanged()
    }

    inner class AlumneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNom: TextView = itemView.findViewById(R.id.tvNomAlumne)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumne, parent, false)
        return AlumneViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumneViewHolder, position: Int) {
        val alumne = alumnes[position]
        holder.textNom.text = "${alumne.nom} (${alumne.grup})"
        holder.itemView.setOnClickListener { onItemClick(alumne) }
    }

    override fun getItemCount(): Int = alumnes.size
}
