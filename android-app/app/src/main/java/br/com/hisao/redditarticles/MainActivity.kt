package br.com.hisao.redditarticles

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.hisao.redditarticles.details.DetailsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, actionBar)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }



}