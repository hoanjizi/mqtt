package home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import chinh.pham.mqtt.R
import home.databases.HistoryViewModel
import home.fragments.FragmentLogin
import home.fragments.FragmentReceiveData
import kotlinx.android.synthetic.main.activity_home.*
import utils.ClickInterface
import utils.FragmentUtil
import utils.dataLocalRoom

class HomeActivity : AppCompatActivity() {
    var xmlClick: ClickInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        dataLocalRoom = ViewModelProviders.of(this as FragmentActivity).get(HistoryViewModel::class.java)
        setContentView(R.layout.activity_home)
        loadFragment(FragmentLogin(), FragmentUtil.fragmentLogin)

    }

    fun loadFragment(fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, fragment, tag)
        FragmentUtil.fragmentStack = tag
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun onReplaceFragmentWithHistoryEvent( fragmentCurrentStr: String, newFragmentStr: String, newFragment: Fragment) {
        val newFrag: Fragment? = supportFragmentManager.findFragmentByTag(newFragmentStr)
        val currentFrag: Fragment? = supportFragmentManager.findFragmentByTag(fragmentCurrentStr)
        val ft = supportFragmentManager.beginTransaction()
        if (newFrag == null && currentFrag != null) {
            ft.add(R.id.fragmentMain, newFragment, newFragmentStr)
            ft.hide(currentFrag)

        } else {
            ft.show(newFrag!!)
            ft.hide(currentFrag!!)
        }
        FragmentUtil.fragmentStack = newFragmentStr
        ft.commit()
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
    fun onClickRBtn(view: View) {
        xmlClick?.let {
            it.onClickRbtn(view)
        }
    }

    fun closeDrawer() {
        drawerPaneParent.closeDrawer(drawerPane)
    }

    override fun onBackPressed() {
        when(FragmentUtil.fragmentStack) {
            FragmentUtil.fragmentHistory -> {
                onReplaceFragmentWithHistoryEvent(FragmentUtil.fragmentHistory,FragmentUtil.fragmentReceive,FragmentReceiveData())
            }
            FragmentUtil.fragmentSendData ->{
                onReplaceFragmentWithHistoryEvent(FragmentUtil.fragmentSendData,FragmentUtil.fragmentReceive,FragmentReceiveData())
            }
            else ->{
                finish()
            }
        }
    }
}
