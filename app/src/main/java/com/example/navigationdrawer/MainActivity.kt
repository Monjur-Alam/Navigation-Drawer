package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var buttonAdd: Button
    lateinit var menuEditText: EditText
    lateinit var buttonRemove: Button
    lateinit var menuIdText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        buttonAdd = findViewById(R.id.buttonAdd)
        menuEditText = findViewById(R.id.menuEditText)
        menuIdText = findViewById(R.id.menuIdText)
        buttonRemove = findViewById(R.id.buttonRemove)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        buttonAdd.setOnClickListener {
            val mMenu = navView.menu
            val menuSize = mMenu.size()
            mMenu.add(1, menuSize, menuSize, menuEditText.text.toString())
            mMenu.getItem(menuSize).setIcon(R.drawable.ic_launcher_foreground)

            mMenu.getItem(menuSize).setOnMenuItemClickListener {
                toast(it.title.toString() + " menu item is selected")
                false
            }

            drawerLayout.openDrawer(Gravity.LEFT)
            longToast(menuEditText.text.toString() + "is added on the "+ menuSize +"th order")
        }

        buttonRemove.setOnClickListener {
            val mMenu = navView.menu
            mMenu.removeItem(menuIdText.text.toString().toInt())

            drawerLayout.openDrawer(Gravity.LEFT)
            longToast(menuIdText.toString() + "th menu is deleted")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                toast("nav_home menu item is selected")
            }
            R.id.nav_gallery -> {
                toast("nav_gallery menu item is selected")
            }
            R.id.nav_slideshow -> {
                toast("nav_slideshow menu item is selected")
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}