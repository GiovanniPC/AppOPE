package com.example.ivolunteerapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.view.View


class MainActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Prefs.getString("logado") == "sim") {
            setContentView(R.layout.activity_main)
        } else {
            setContentView(R.layout.activity_main_logado)
        }



        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title="Login"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome  = Prefs.getString("lembrarNome")
            var lembrarSenha  = Prefs.getString("lembrarSenha")
            editText.setText(lembrarNome)
            editText2.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }

        val botaoLogin = findViewById<Button>(R.id.button)
        botaoLogin.setOnClickListener {onClickLogin() }

        val colors = arrayOf("Voluntário","ONG")

        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item,
            colors
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinner.adapter = adapter;

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                var x = parent.getItemAtPosition(position).toString()
                Prefs.setString("tipo", x)

            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

    }

    fun onClickLogin(){

        val ong = OngLogin()
        val campoUsuario = findViewById<EditText>(R.id.editText)
        val campoSenha = findViewById<EditText>(R.id.editText2)

        ong.username = campoUsuario.text.toString()
        ong.password = campoSenha.text.toString()

        Prefs.setBoolean("lembrar", checkBoxLogin.isChecked)
        if (checkBoxLogin.isChecked) {
            Prefs.setString("lembrarNome", ong.username)
            Prefs.setString("lembrarSenha", ong.password)

        } else {
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        taskLogin(ong)

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

    private fun taskLogin(ong: OngLogin) {

        if (Prefs.getString("tipo") == "ONG") {
            if (AndroidUtils.isInternetDisponivel(IVoApplication.getInstance().applicationContext)) {
                Thread {
                    val token = LoginService.login(ong)
                    runOnUiThread {

                        if (token.contains("error")) {

                            Toast.makeText(this, "Usuario ou senhas invalidos.", Toast.LENGTH_SHORT).show()

                        } else {
                            Prefs.setString("logado", "sim")
                            val intent = Intent(context, TelaInicialActivity::class.java)
                            val params = Bundle()

                            val token1 = token.substring(1 until token.length-1)

                                Prefs.setString("token", token1)

                            intent.putExtras(params)

                            startActivityForResult(intent, 1)
                        }
                    }
                }.start()
            } else {

                Toast.makeText(this, "Mobile sem internet", Toast.LENGTH_SHORT).show()

            }
        } else if (Prefs.getString("tipo") == "Voluntário") {

            if (AndroidUtils.isInternetDisponivel(IVoApplication.getInstance().applicationContext)) {
                Thread {
                    val token = LoginService.login2(ong)
                    runOnUiThread {

                        if (token.contains("error")) {

                            Toast.makeText(this, "Usuario ou senhas invalidos.", Toast.LENGTH_SHORT).show()

                        } else {
                            Prefs.setString("logado", "sim")
                            val intent = Intent(context, TelaInicialActivity::class.java)

                            startActivityForResult(intent, 1)
                        }
                    }
                }.start()

            } else {

                Toast.makeText(this, "Mobile sem internet", Toast.LENGTH_SHORT).show()

            }
        }
    }

}
