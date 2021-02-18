package request_clients
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import signin_options.SignInWithGoogle
import tools.AppExecutors
import values.Constants
import java.util.concurrent.TimeUnit

class RequestClientsSignInGoogle{
    private object Holder{
        val instance = RequestClientsSignInGoogle()
    }
    companion object{
        private val TAG = "RequestClients"
        @JvmStatic
        val instance: RequestClientsSignInGoogle by lazy { Holder.instance }
    }
    private val intentGoogle:MutableLiveData<Intent> = MutableLiveData()
    fun getIntentGoogle(): LiveData<Intent> = intentGoogle
    private val requestTimeOutSignInWithGoogle:MutableLiveData<Boolean> = MutableLiveData()
    fun getRequestTimeOutSignInWithGoogle() = requestTimeOutSignInWithGoogle
    private var retrieveIntentGoogle: RetrieveIntentGoogle? = null

    fun retrieveIntentSignInGoogle(activity: AppCompatActivity){
        if(retrieveIntentGoogle!=null) retrieveIntentGoogle = null
        retrieveIntentGoogle = RetrieveIntentGoogle(activity)
        requestTimeOutSignInWithGoogle.value = false
        val handler = AppExecutors.instance.getNetworkIO().submit(retrieveIntentGoogle)
        AppExecutors.instance.getNetworkIO().schedule({
            requestTimeOutSignInWithGoogle.postValue(true)
            handler.cancel(true)
        }, Constants.CONNECT_TIME_OUT,TimeUnit.MILLISECONDS)
    }

    private inner class RetrieveIntentGoogle(_activity:AppCompatActivity):Runnable{
        var activity = _activity
        private var cancelRequest = false
        override fun run() {
            if (cancelRequest) return;
            try {
                activity.runOnUiThread {
                    intentGoogle.postValue(getIntentSignInGoogle(activity))
                }
            }catch (e:Exception){
                Log.d(TAG, "run: Exception on Catch: ${e.message}")
                intentGoogle.postValue(null)
            }
        }
        fun getIntentSignInGoogle(activity: AppCompatActivity):Intent = SignInWithGoogle.instance.joinInGoogle(activity)
        fun cancelRequest(){
            Log.d(TAG, "cancelRequest: Cancelling retrieval Intent Google")
            cancelRequest = true
        }
    }

}