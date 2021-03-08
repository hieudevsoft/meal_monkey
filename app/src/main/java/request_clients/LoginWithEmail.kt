package request_clients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tools.AppExecutors
import firebase.FirebaseAuthManager
import values.Constants
import java.util.concurrent.TimeUnit

class LoginWithEmail private constructor() {
    private val firebaseAuth = FirebaseAuthManager.instance.getFirebaseAuth()
    private object Holder{
        val instance = LoginWithEmail()
    }
    companion object{
        private val TAG = "LoginWithEmail"
        @JvmStatic
        val instance : LoginWithEmail by lazy { Holder.instance }
    }

    private val resultLogin:MutableLiveData<Boolean> = MutableLiveData()
    fun getResultLogin():LiveData<Boolean> = resultLogin

    private val resultException:MutableLiveData<String> = MutableLiveData()
    fun getResultException():LiveData<String> = resultException


    private val requestTimeOutLogin:MutableLiveData<Boolean> = MutableLiveData()
     fun getRequestTimeOutLogin():LiveData<Boolean> = requestTimeOutLogin

    private var retrieveLoginResult : RetrieveResultLogin? = null
    fun login(email: String,password:String){
        if(retrieveLoginResult!=null) retrieveLoginResult = null
        retrieveLoginResult = RetrieveResultLogin(email,password)
        val handler = AppExecutors.instance.getNetworkIO().submit(retrieveLoginResult)
        requestTimeOutLogin.value = false
        AppExecutors.instance.getNetworkIO().schedule({
            requestTimeOutLogin.postValue(true)
            handler.cancel(true)
        }, Constants.CONNECT_TIME_OUT,TimeUnit.MILLISECONDS)
    }

    private inner class RetrieveResultLogin(_email:String,_password:String):Runnable{
        var email = _email
        var password = _password
        private var cancelRequest = false
        override fun run() {
            if (cancelRequest) return
            try {
                firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        task->
                        if(task.isSuccessful){
                            Log.d(TAG, "run: Login successfully")
                            resultLogin.postValue(true)
                        }
                        else{
                            Log.d(TAG, "run: Create account Failure ${task.exception}")
                            resultException.postValue(task.exception?.message)
                            resultLogin.postValue(false)
                        }
                    }
            } catch (e: Exception) {
                Log.d(TAG, "run: Exception on Catch: ${e.message}")
                resultException.postValue(e.message)
                resultLogin.postValue(false)
            }
        }
         fun cancelRequest(){
            Log.d(TAG, "cancelRequest: Cancel Request Login Email")
            cancelRequest = true
        }
    }
}