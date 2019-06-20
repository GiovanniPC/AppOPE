package com.example.ivolunteerapplication

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class OngActivity : DebugActivity() {

    var ong: Ong? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ong)

        ong = intent.getSerializableExtra("ong") as Ong

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = ong?.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var texto = findViewById<TextView>(R.id.nomeOng)
        texto.text = ong?.name

        var texto1 = findViewById<TextView>(R.id.responsavelOng)
        texto1.text = ong?.responsavel

        var texto2 = findViewById<TextView>(R.id.descricaoOng)
        texto2.text = ong?.descricao

        var texto7 = findViewById<TextView>(R.id.emailOng)
        texto7.text = ong?.email

        var texto3 = findViewById<TextView>(R.id.phoneOng)
        texto3.text = ong?.phone

        var texto4 = findViewById<TextView>(R.id.addressOng)
        texto4.text = ong?.address

        var texto5 = findViewById<TextView>(R.id.cityOng)
        texto5.text = ong?.city

        var texto6 = findViewById<TextView>(R.id.stateOng)
        texto6.text = ong?.state

    }
}