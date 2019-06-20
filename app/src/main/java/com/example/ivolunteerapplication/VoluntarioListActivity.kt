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
import android.widget.Toast



class VoluntarioListActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var voluntarios = listOf<Voluntario>()
    var recyclerVoluntario: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Prefs.getString("logado") == "sim") {
            setContentView(R.layout.acitivity_lista_voluntarios)
        } else {
            setContentView(R.layout.acitivity_lista_voluntarios_segunda)
        }

        val args:Bundle? = intent.extras

        var nome = args?.getString("nome")

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()


        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title="IVolunteer APP"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        recyclerVoluntario = findViewById<RecyclerView>(R.id.recyclerVoluntario)
        recyclerVoluntario?.layoutManager = LinearLayoutManager(context)
        recyclerVoluntario?.itemAnimator = DefaultItemAnimator()
        recyclerVoluntario?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()

        val args:Bundle? = intent.extras

        var nome = args?.getString("nome")

        nome?.let { taskVoluntarios(it) }
    }

    fun taskVoluntarios(nome: String) {
        Thread {
            if (nome == "saude") {
                this.voluntarios = VoluntarioService.getOngSaude(context)
            } else if (nome == "cultura") {
                this.voluntarios = VoluntarioService.getOngCultura(context)
            } else if (nome == "habitacao") {
                this.voluntarios = VoluntarioService.getOngHabitacao(context)
            } else if (nome == "assistenciasocial") {
                this.voluntarios = VoluntarioService.getOngAssistenciaSocial(context)
            } else if (nome == "educacao") {
                this.voluntarios = VoluntarioService.getOngEducacaoPesquisa(context)
            } else if (nome == "meioambiente") {
                this.voluntarios = VoluntarioService.getOngMeioAmbiente(context)
            } else if (nome == "defesadireito") {
                this.voluntarios = VoluntarioService.getOngDefesadDireito(context)
            }
            runOnUiThread {
                if(voluntarios.isNotEmpty()) {
                    recyclerVoluntario?.adapter = VoluntarioAdapter(voluntarios) { onClickVoluntario(it) }
                } else {
                    Prefs.setString("token", "")
                    Toast.makeText(this, "Sessão expirada, logar novamente!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    fun onClickVoluntario(voluntario: Voluntario) {
        Toast.makeText(context, "Clicou no voluntario ${voluntario.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, VoluntarioActivity::class.java)
        intent.putExtra("voluntario", voluntario)
        startActivity(intent)
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
