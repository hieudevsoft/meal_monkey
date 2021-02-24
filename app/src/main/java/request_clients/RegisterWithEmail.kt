package request_clients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tools.AppExecutors
import tools.FirebaseAuthManager
import values.Constants
import java.util.concurrent.TimeUnit

class RegisterWithEmail private constructor() {
    private val firebaseAuth = FirebaseAuthManager.instance.getFirebaseAuth()
    private object Holder{
        val instance = RegisterWithEmail()
    }
    companion object{
        private val TAG = "RegisterWithEmail"
        @JvmStatic
        val instance : RegisterWithEmail by lazy { Holder.instance }
    }

    private val resultRegister:MutableLiveData<Boolean> = MutableLiveData()
    fun getResultRegister():LiveData<Boolean> = resultRegister

    private val resultException:MutableLiveData<String> = MutableLiveData()
    fun getResultException():LiveData<String> = resultException


    private val requestTimeOutRegister:MutableLiveData<Boolean> = MutableLiveData()
     fun getRequestTimeOutRegister():LiveData<Boolean> = requestTimeOutRegister

    private var retrieveRegisterResult : RetrieveResultRegister? = null
    fun register(email: String,password:String){
        if(retrieveRegisterResult!=null) retrieveRegisterResult = null
        retrieveRegisterResult = RetrieveResultRegister(email,password)
        val handler = AppExecutors.instance.getNetworkIO().submit(retrieveRegisterResult)
        requestTimeOutRegister.value = false
        AppExecutors.instance.getNetworkIO().schedule({
            requestTimeOutRegister.postValue(true)
            handler.cancel(true)
        }, Constants.CONNECT_TIME_OUT,TimeUnit.MILLISECONDS)
    }

    private inner class RetrieveResultRegister(_email:String,_password:String):Runnable{
        var email = _email
        var password = _password
        private var cancelRequest = false
        override fun run() {
            if (cancelRequest) return
            try {
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        task->
                        if(task.isSuccessful){
                            Log.d(TAG, "run: Register successfully")
                            resultRegister.postValue(true)
                        }
                        else{
                            Log.d(TAG, "run: Create account Failure ${task.exception}")
                            resultException.postValue(task.exception?.message)
                            resultRegister.postValue(false)
                        }
                    }
            } catch (e: Exception) {
                Log.d(TAG, "run: Exception on Catch: ${e.message}")
                resultException.postValue(e.message)
                resultRegister.postValue(false)
            }
        }
         fun cancelRequest(){
            Log.d(TAG, "cancelRequest: Cancel Request Register Email")
            cancelRequest = true
        }
    }
}