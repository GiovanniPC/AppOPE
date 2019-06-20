package com.example.ivolunteerapplication

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class VoluntarioAdapter (
    val voluntarios: List<Voluntario>,
    val onClick: (Voluntario) -> Unit):
    RecyclerView.Adapter<VoluntarioAdapter.VoluntariosViewHolder>() {

    class VoluntariosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardView = view.findViewById<CardView>(R.id.card_voluntarios)
        }

    }

    override fun getItemCount() = this.voluntarios.size

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): VoluntariosViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_voluntario, parent, false)

        val holder = VoluntariosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: VoluntariosViewHolder, position: Int) {
        val context = holder.itemView.context

        val voluntario = voluntarios[position]

        holder.cardNome.text = voluntario.name

        holder.itemView.setOnClickListener {onClick(voluntario)}
    }
}