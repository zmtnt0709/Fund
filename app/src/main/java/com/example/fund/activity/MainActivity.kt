package com.example.fund.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.fund.fragment.FundListFragment
import com.example.fund.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val FRAGMENT_TAG_FUND_LIST = "fragment_tag_fund_list"
    }

    private var fundListFragment: Fragment? = null
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView(savedInstanceState)

    }

    private fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        initFragment(savedInstanceState)
    }

    @SuppressLint("CommitTransaction")
    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            fundListFragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_FUND_LIST)
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        currentFragment?.let {
            fragmentTransaction.hide(it)
        }

        if (fundListFragment == null) {
            fundListFragment = FundListFragment()
        }

        fundListFragment?.let {
            fragmentTransaction.add(
                R.id.fl_content, it,
                FRAGMENT_TAG_FUND_LIST
            )
            currentFragment = fundListFragment
            fragmentTransaction.show(it)
            fragmentTransaction.commit()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
