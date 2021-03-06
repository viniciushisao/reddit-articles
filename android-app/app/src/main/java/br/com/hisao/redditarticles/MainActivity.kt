package br.com.hisao.redditarticles

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, actionBar)

        val mainActivityViewModel = ViewModelProvider(this).get(MainActivitySharedViewModel::class.java)

        mainActivityViewModel.actionBarTitleMutableLiveData.observe(this, Observer {

            supportActionBar?.title = it
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}