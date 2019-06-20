package com.example.ivolunteerapplication

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView

class VoluntarioActivity : DebugActivity() {

    var voluntario: Voluntario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voluntario)

        voluntario = intent.getSerializableExtra("voluntario") as Voluntario

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = voluntario?.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var texto = findViewById<TextView>(R.id.nomeVoluntario)
        texto.text = voluntario?.name

        var texto1 = findViewById<TextView>(R.id.dataVoluntario)
        texto1.text = voluntario?.birth

        var texto2 = findViewById<TextView>(R.id.emailVoluntario)
        texto2.text = voluntario?.email

        var texto3 = findViewById<TextView>(R.id.phoneVoluntario)
        texto3.text = voluntario?.phone

        var texto4 = findViewById<TextView>(R.id.addressVoluntario)
        texto4.text = voluntario?.address

        var texto5 = findViewById<TextView>(R.id.cityVoluntario)
        texto5.text = voluntario?.city

        var texto6 = findViewById<TextView>(R.id.stateVoluntario)
        texto6.text = voluntario?.state

    }
}