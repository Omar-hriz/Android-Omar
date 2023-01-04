package com.example.myclass

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.myclass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val Personne1 = Personne("Omar", "Hriz", 21)
        val Personne2 = Personne("Mattiheu", "Pelissier", 21)
        val Personne3 = Personne("Mathys", "Tests", 21)

        val PeopleTaille: MutableList<Personne> = mutableListOf(Personne1, Personne2, Personne3)
        val PeopleAlpha: MutableList<Personne> = mutableListOf(Personne1, Personne2, Personne3)

        for (i in 0 until  PeopleTaille.size - 1) {
            var PlaceHolder = PeopleTaille[0]
            for (j in i until PeopleTaille.size -1) {
                if (PeopleTaille[j].Nom.length > PeopleTaille[i].Nom.length) {
                    PlaceHolder = PeopleTaille[j]
                    PeopleTaille[j] = PeopleTaille[i]
                    PeopleTaille[i] = PlaceHolder
                }
            }
        }
        for (personne in PeopleTaille) {
            Log.d("Taille", personne.Nom)
        }
        for (i in 0 until  PeopleAlpha.size - 1) {
            var PlaceHolder = PeopleTaille[0]
            for (j in i until PeopleAlpha.size -1) {
                if (Compart(PeopleAlpha[i].Nom,PeopleAlpha[j].Nom)) {
                    PlaceHolder = PeopleAlpha[j]
                    PeopleAlpha[j] = PeopleAlpha[i]
                    PeopleAlpha[i] = PlaceHolder
                }
            }
        }
        for (personne in PeopleAlpha) {
            Log.d("Alpha", personne.Nom)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    fun Compart(a:String ,b:String):Boolean{
        val cond = false
        for(i in 0 until minOf(a.length, b.length) -1) {
            if (a[i].compareTo(b[i]) == 0) {
                val cond = true
                break
            }
        }
        return cond
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}