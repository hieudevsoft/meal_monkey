package repositories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import request_clients.RequestClientsSignInGoogle


class RepositoryGoogle private constructor() {
    lateinit var requestClientsSignInGoogle: RequestClientsSignInGoogle
    init {
        requestClientsSignInGoogle = RequestClientsSignInGoogle.instance
    }
    private object Holder { val instance = RepositoryGoogle()}
    companion object {
        @JvmStatic
        val instance : RepositoryGoogle by lazy { Holder.instance }
    }

    fun getRequestTimeOutSignInWithGoogle():LiveData<Boolean> = requestClientsSignInGoogle.getRequestTimeOutSignInWithGoogle()
    fun retrieveIntentSignInWithGoogle(activity:AppCompatActivity) = requestClientsSignInGoogle.retrieveIntentSignInGoogle(activity)
    fun getIntentSignInWithGoogle():LiveData<Intent> = requestClientsSignInGoogle.getIntentGoogle()

}