package networks

import android.app.Activity
import android.content.IntentFilter

object NetworkHelper{
    @JvmStatic val connectionReceiver = ConnectionReceiver()
    fun checkInternet(activity: Activity,listener:ConnectionReceiver.ReceiverListener){
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        activity.registerReceiver(connectionReceiver,intentFilter)
        ConnectionReceiver.receiver = listener
    }
    fun unCheckInternet(activity: Activity){
        activity.unregisterReceiver(connectionReceiver)
    }
}