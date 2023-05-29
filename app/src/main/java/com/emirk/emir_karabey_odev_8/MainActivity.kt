package com.emirk.emir_karabey_odev_8

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.emirk.emir_karabey_odev_8.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_hepsi -> {
                    navigateToHomeFragmentWithParameter("Hepsi")
                    true
                }
                R.id.nav_aile -> {
                    navigateToHomeFragmentWithParameter("Aile")
                    true
                }
                R.id.nav_okul -> {
                    navigateToHomeFragmentWithParameter("Okul")
                    true
                }
                R.id.nav_arkadaslar -> {
                    navigateToHomeFragmentWithParameter("Arkadaşlar")
                    true
                }
                R.id.nav_hali_saha -> {
                    navigateToHomeFragmentWithParameter("Halı Saha")
                    true
                }
                R.id.nav_is -> {
                    navigateToHomeFragmentWithParameter("İş")
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToHomeFragmentWithParameter(parameter: String) {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val bundle = bundleOf("parameter" to parameter)
        navController.navigate(R.id.nav_home, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}