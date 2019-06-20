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



class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Prefs.getString("logado") == "sim") {
            setContentView(R.layout.activity_tela_inicial)
        } else {
            setContentView(R.layout.activity_tela_inicial_segunda)
        }

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title="IVolunteer APP"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        val botaoLogin2 = findViewById<Button>(R.id.button2)
        botaoLogin2.setOnClickListener {onClickOngs() }

        val botaoLogin3 = findViewById<Button>(R.id.button3)
        botaoLogin3.setOnClickListener {onClickVoluntarios() }

        val botaoLogin4 = findViewById<Button>(R.id.button5)
        botaoLogin4.setOnClickListener {onClickSaberOngs() }

        val botaoLogin5 = findViewById<Button>(R.id.button4)
        botaoLogin5.setOnClickListener {onClickSaberVoluntarios() }

    }


    fun onClickSaberOngs() {
        val intent = Intent(context, OngOffActivity::class.java)
        startActivity(intent)
    }

    fun onClickSaberVoluntarios() {
        val intent = Intent(context, VoluntarioOffActivity::class.java)
        startActivity(intent)
    }

    fun onClickOngs() {
        val intent = Intent(context, OActivity::class.java)
        startActivity(intent)
    }

    fun onClickVoluntarios() {
         if (Prefs.getString("tipo") == "ONG") {
            startActivity(Intent(context, VActivity::class.java))
        }  else {
            startActivity(Intent(context, VoluntarioOffActivity::class.java))
        }
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

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        val id = item?.itemId
//
//        if(id == R.id.action_logout){
//            Prefs.setString("logado", "")
//            Prefs.setString("token", "")
//            Prefs.setString("tipo", "")
//            Toast.makeText(this, "Você saiu!", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//
//        }
//        return super.onOptionsItemSelected(item)
//    }


//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_home -> {
//                Toast.makeText(this, "Clicou Home", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(context, TelaInicialActivity::class.java))
//            }
//            R.id.nav_ong -> {
//                Toast.makeText(this, "Clicou ONGs", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(context, OActivity::class.java))
//            }
//            R.id.nav_voluntario -> {
//                Toast.makeText(this, "Clicou Voluntarios", Toast.LENGTH_SHORT).show()
//                if (Prefs.getString("tipo") == "ONG") {
//                    startActivity(Intent(context, VActivity::class.java))
//                }  else {
//                    startActivity(Intent(context, VoluntarioOffActivity::class.java))
//                }
//            }
//            R.id.nav_about -> {
//                Toast.makeText(this, "Clicou Sobre", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(context, SobreActivity::class.java))
//                }
//        }
//
//        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
//        drawer.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        if (Prefs.getString("logado") == "sim") {
//            menuInflater.inflate(R.menu.menu_main, menu)
//        } else {
//            menuInflater.inflate(R.menu.menu_main_logado, menu)
//        }
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        val id = item?.itemId
//
//        if(id == R.id.action_logout){
//            Prefs.setString("logado", "")
//            Prefs.setString("token", "")
//            Prefs.setString("tipo", "")
//            Toast.makeText(this, "Você saiu!", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        } else {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }

}
