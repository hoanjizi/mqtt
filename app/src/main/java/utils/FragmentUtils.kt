package utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

object FragmentUtil {
    const val fragmentLogin: String = "FRAGMENT_LOGIN"
    const val fragmentReceive: String = "FRAGMENT_REVEIVE"
}

fun hideKeyBoard(activity: Activity) {
    val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.currentFocus.windowToken,0)
}