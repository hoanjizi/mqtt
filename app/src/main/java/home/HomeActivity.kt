package home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import chinh.pham.mqtt.R
import home.fragments.FragmentLogin
import kotlinx.android.synthetic.main.activity_home.*
import utils.FragmentUtil

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_home)
        loadFragment(FragmentLogin(), FragmentUtil.fragmentLogin)

    }

    fun loadFragment(fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, fragment, tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun lockDrawer() {
        drawerPaneParent.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun unLockDrawer() {
        drawerPaneParent.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }


    fun openDrawer() {
        drawerPaneParent.openDrawer(Gravity.START)
    }

    fun closeDrawer() {
        drawerPaneParent.closeDrawer(drawerPane)
    }

    override fun onBackPressed() {

    }
}
