package networks

import android.app.Activity
import android.content.IntentFilter

object NetworkHelper{
    fun checkInternet(activity: Activity,listener:ConnectionReceiver.ReceiverListener){
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        activity.registerReceiver(ConnectionReceiver(),intentFilter)
        ConnectionReceiver.receiver = listener
    }
    fun unCheckInternet(activity: Activity){
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        activity.unregisterReceiver(ConnectionReceiver())
    }
}