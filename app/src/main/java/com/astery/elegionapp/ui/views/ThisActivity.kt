package com.astery.elegionapp.ui.views

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.ui.AppBarConfiguration
import com.astery.elegionapp.R
import com.astery.elegionapp.databinding.ActivityMainBinding
import com.astery.elegionapp.ui.navigation.FragmentNavController
import com.astery.elegionapp.ui.navigation.ParentActivity

class ThisActivity : AppCompatActivity(), ParentActivity {

    /** configuration */
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /** navigation */
    private lateinit var navController: FragmentNavController
    private lateinit var currentFragment: XFragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        pushFragment()
    }

    /** add first fragment */
    private fun pushFragment(){
        navController = FragmentNavController.CALENDAR
        currentFragment = navController.thisFragment

        fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.add(R.id.hostFragment, currentFragment)
        ft.commit()
    }

    /** change fragments*/
    override fun move(action: FragmentNavController, bundle: Bundle?) {
        navController = action
        val newFragment = navController.thisFragment
        newFragment.arguments = bundle

        currentFragment.setTransition(navController.transition)
        newFragment.setTransition(navController.transition.reverseCopy())

        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.hostFragment, newFragment, newFragment.javaClass.simpleName)

        ft.addToBackStack(newFragment.javaClass.simpleName)
        ft.commit()

        currentFragment = newFragment
    }


    override fun changeTitle(title: String) {
        supportActionBar?.title = title;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

    }
}