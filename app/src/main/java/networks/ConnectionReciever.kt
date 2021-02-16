package networks

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build


class ConnectionReceiver : BroadcastReceiver() {
    companion object{
       @JvmStatic lateinit var receiver:ReceiverListener
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        val connectActivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfor = connectActivityManager.activeNetworkInfo
        if(receiver!=null){
            val isConnected = networkInfor!=null && networkInfor.isConnectedOrConnecting
            receiver.onNetWorkChange(isConnected)
        }
    }

    interface ReceiverListener{
        fun onNetWorkChange(network:Boolean)
    }
}
