package tools

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import screens.Login

class FirebaseAuthManager private constructor() {
    private lateinit var firebaseAuthState:FirebaseAuth.AuthStateListener
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private object Holder{
        val instance = FirebaseAuthManager()
    }
    companion object{
        val instance: FirebaseAuthManager by lazy { Holder.instance }
    }
    fun getCurrentUser() = firebaseAuth.currentUser
    fun getFirebaseAuth() = firebaseAuth
    fun getState():FirebaseAuth.AuthStateListener = firebaseAuthState
    fun attachState(){
        firebaseAuth.addAuthStateListener(firebaseAuthState)
    }
    fun operationStateAuthFirebase(context: Activity){
        firebaseAuthState = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if(user!=null) Tools.updateLoginUI(context = context,getCurrentUser()) else
            Tools.moveScreenToLogin(1000,context,Login::class.java)
        }

    }
}