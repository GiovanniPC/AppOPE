package com.example.ivolunteerapplication

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class OngAdapter (
    val ongs: List<Ong>,
    val onClick: (Ong) -> Unit):
    RecyclerView.Adapter<OngAdapter.OngsViewHolder>() {

    class OngsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardView = view.findViewById<CardView>(R.id.card_ongs)
        }

    }

    override fun getItemCount() = this.ongs.size

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): OngsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_ong, parent, false)

        val holder = OngsViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: OngsViewHolder, position: Int) {
        val context = holder.itemView.context

        val ong = ongs[position]

        holder.cardNome.text = ong.name

        holder.itemView.setOnClickListener {onClick(ong)}
    }
}