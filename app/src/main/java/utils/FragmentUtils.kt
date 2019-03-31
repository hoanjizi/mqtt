package utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import home.databases.HistoryViewModel

lateinit var dataLocalRoom: HistoryViewModel
object FragmentUtil {
    var fragmentStack: String = ""
    const val fragmentLogin: String = "FRAGMENT_LOGIN"
    const val fragmentReceive: String = "FRAGMENT_REVEIVE"
    const val fragmentHistory: String = "FRAGMENT_HISTORY"
}

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun hideKeyBoard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    activity.currentFocus?.let {
        imm.hideSoftInputFromWindow(activity.currentFocus.windowToken,0)
    }
}