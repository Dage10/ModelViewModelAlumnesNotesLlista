package com.daviddam.modelviewmodelalumnesnotesllista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Nota

class NotaAdapter : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    private var notes: List<Nota> = emptyList()

    fun submitList(newNotes: List<Nota>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textModul: TextView = itemView.findViewById(R.id.tvModul)
        val textNota: TextView = itemView.findViewById(R.id.tvNota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notes[position]
        holder.textModul.text = nota.modul
        holder.textNota.text = nota.valor.toString()
    }

    override fun getItemCount(): Int = notes.size
}
