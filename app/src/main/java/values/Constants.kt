package values

import androidx.appcompat.app.AppCompatActivity
import com.project.mealmonkey.R

object Constants {
    fun getClientIdGoogle(activity:AppCompatActivity):String{
         return activity.getString(R.string.default_web_client_id)
    }

    val CONNECT_TIME_OUT = 10000L
    val READ_TIME_OUT = 4000L
    val WRITE_TIME_OUT = 3000L
}