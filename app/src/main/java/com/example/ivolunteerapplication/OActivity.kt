package com.example.ivolunteerapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast



class OActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Prefs.getString("logado") == "sim") {
            setContentView(R.layout.ongs)
        } else {
            setContentView(R.layout.ongs_segunda)
        }



        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title="Ongs"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()


        val botaoLogin3 = findViewById<Button>(R.id.saude)
        botaoLogin3.setOnClickListener {onClicktogo("saude") }

        val botaoLogin4 = findViewById<Button>(R.id.cultura)
        botaoLogin4.setOnClickListener {onClicktogo("cultura") }

        val botaoLogin5 = findViewById<Button>(R.id.habitacao)
        botaoLogin5.setOnClickListener {onClicktogo("habitacao") }

        val botaoLogin6 = findViewById<Button>(R.id.assistenciasocial)
        botaoLogin6.setOnClickListener {onClicktogo("assistenciasocial") }

        val botaoLogin7 = findViewById<Button>(R.id.educacao)
        botaoLogin7.setOnClickListener {onClicktogo("educacao") }

        val botaoLogin8 = findViewById<Button>(R.id.meioambiente)
        botaoLogin8.setOnClickListener {onClicktogo("meioambiente") }

        val botaoLogin9 = findViewById<Button>(R.id.defesadireito)
        botaoLogin9.setOnClickListener {onClicktogo("defesadireito") }


    }

    fun onClicktogo(num: String) {
        val intent = Intent(context, OngListActivity::class.java)

        val params = Bundle()
        params.putString("nome", num)
        intent.putExtras(params)

        startActivityForResult(intent, 1)
    }

    private fun configuraMenuLateral () {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layourMenuLateral)

        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Clicou Home", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, TelaInicialActivity::class.java))

            }
            R.id.nav_ong -> {
                Toast.makeText(this, "Clicou ONGs", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, OActivity::class.java))

            }
            R.id.nav_voluntario -> {
                Toast.makeText(this, "Clicou Voluntarios", Toast.LENGTH_SHORT).show()
                if (Prefs.getString("tipo") == "Voluntário") {
                    startActivity(Intent(context, VoluntarioOffActivity::class.java))
                } else if (Prefs.getString("tipo") == "ONG") {
                    startActivity(Intent(context, VActivity::class.java))
                }
            }
            R.id.nav_about -> {
                Toast.makeText(this, "Clicou Sobre", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, SobreActivity::class.java))
            }
            R.id.action_logout -> {
                Prefs.setString("logado", "")
                Prefs.setString("token", "")
                Prefs.setString("tipo", "")
                Toast.makeText(this, "Você saiu!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_login -> {
                Toast.makeText(this, "Faça o seu login!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, MainActivity::class.java))
            }
        }

        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


}
