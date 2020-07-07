package com.nramos.mimoflix.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ActivityMainBinding
import com.nramos.mimoflix.utils.StyleManager
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setStyle() {
        when(StyleManager(context = this).getMode())  {
            true -> setTheme(R.style.Mimoflix_Dark)
            false -> setTheme(R.style.Mimoflix_Light)
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnNavigationItemReselectedListener{}
    }

}
