package uz.gita.book_application.aaa

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class WifiReceiver : BroadcastReceiver() {
    fun setCheckInternetListener(block: (Boolean) -> Unit) {
        checkInternetListener = block
    }

    private var checkInternetListener: ((Boolean) -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {
        checkInternetListener?.invoke(checkInternet(context))
    }
    private fun checkInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnected) {
            when (netInfo.type) {
                ConnectivityManager.TYPE_WIFI -> true
                else -> false
            }
        } else false
    }

}