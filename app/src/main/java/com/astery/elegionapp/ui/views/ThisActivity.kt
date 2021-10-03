package com.astery.elegionapp.ui.views

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.ui.AppBarConfiguration
import com.astery.elegionapp.R
import com.astery.elegionapp.databinding.ActivityMainBinding
import com.astery.elegionapp.ui.navigation.FragmentNavController
import com.astery.elegionapp.ui.navigation.ParentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        binding.bottomNavigation.setOnNavigationItemSelectedListener(object:BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId){
                    R.id.move_development-> move(FragmentNavController.DEVELOPMENT, null)
                    R.id.move_calendar-> move(FragmentNavController.DEVELOPMENT, null)
                    R.id.move_third-> move(FragmentNavController.DEVELOPMENT, null)
                    R.id.move_fourth-> move(FragmentNavController.REQUESTS, null)
                    R.id.move_fifth-> move(FragmentNavController.DEVELOPMENT, null)
                }
                return true
            }
        })

        pushFragment()
    }

    /** add first fragment */
    private fun pushFragment(){
        navController = FragmentNavController.DEVELOPMENT
        currentFragment = navController.thisFragment!!

        fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.add(R.id.hostFragment, currentFragment)
        ft.commit()
    }

    /** change fragments*/
    override fun move(action: FragmentNavController, bundle: Bundle?) {
        navController = action
        val newFragment = navController.thisFragment
        newFragment!!.arguments = bundle

        currentFragment.setTransition(navController.transition!!)
        newFragment.setTransition(navController.transition!!.reverseCopy()!!)

        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.hostFragment, newFragment, newFragment.javaClass.simpleName)

        ft.addToBackStack(newFragment.javaClass.simpleName)
        ft.commit()

        currentFragment = newFragment
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun setTitle(title: String?) {
        if (title == null){
            binding.toolbar.visibility = GONE
        } else{
            binding.toolbar.visibility = VISIBLE
            supportActionBar?.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("main", "click")
        when (item.itemId){
            R.id.move_development-> move(FragmentNavController.DEVELOPMENT, null)
            R.id.move_calendar-> move(FragmentNavController.DEVELOPMENT, null)
            R.id.move_third-> move(FragmentNavController.DEVELOPMENT, null)
            R.id.move_fourth-> move(FragmentNavController.REQUESTS, null)
            R.id.move_fifth-> move(FragmentNavController.DEVELOPMENT, null)
        }
        return super.onOptionsItemSelected(item)
    }
}