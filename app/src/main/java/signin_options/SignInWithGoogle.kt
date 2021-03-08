package signin_options

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import screens.Login
import firebase.FirebaseAuthManager
import tools.Tools
import values.Constants

class SignInWithGoogle private constructor() {
    private var firebaseAuth:FirebaseAuth = FirebaseAuthManager.instance.getFirebaseAuth()
    private var TAG = "SignInWithGoogle"
    private lateinit var gso:GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private object Holder{
        val instance = SignInWithGoogle()
    }

    companion object{
        @JvmStatic
        val instance:SignInWithGoogle by lazy { Holder.instance }
    }

    fun joinInGoogle(activity:AppCompatActivity): Intent {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.getClientIdGoogle(activity))
            .requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(activity, gso)
        return googleSignInClient.signInIntent
    }

    fun logOutGoogle(context: Activity,view: View){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Question ?");
        dialog.setMessage("Do you want Sign Out?")
        dialog.setPositiveButton("Yes"
        ) { _, _ ->
            googleSignInClient = GoogleSignIn.getClient(context,GoogleSignInOptions.DEFAULT_SIGN_IN)
            googleSignInClient.signOut().addOnCompleteListener {
                    task->
                if(task.isSuccessful){
                    Tools.makeToast(context,"Sign Out Successfully~")
                    FirebaseAuthManager.instance.getFirebaseAuth().signOut()
                    Tools.moveScreenToLogin(1000,context = context, Login::class.java)
                }
                else{
                    Tools.makeSnackbar(view,"Sign Out Failure",false)
                }
            }
        }
        dialog.setNegativeButton("No"){
                _,_->
        }
        dialog.show()
    }

    fun checkLoginWithGoogle(context: Context):Boolean = GoogleSignIn.getLastSignedInAccount(context)!=null

    fun loginGoogleWithFirebase(data:Intent?,context: Activity,firebaseAuth: FirebaseAuth,fullScreen:Tools.FullScreenThread){
        val signInGoogleTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        if(signInGoogleTask.isSuccessful){
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.makeToast(context,"Sign In With Google Successfully~")
            val googleSingInAccount = signInGoogleTask.getResult(ApiException::class.java)
            googleSingInAccount.let {
                val authCredential = GoogleAuthProvider.getCredential(googleSingInAccount?.idToken,null)
                firebaseAuth.signInWithCredential(authCredential)
                    .addOnCompleteListener(context
                    ) { p0 ->
                        if (p0.isSuccessful) {
                            fullScreen.stop()
                            fullScreen.clearThread()
                            Tools.updateLoginUI(context,firebaseAuth.currentUser)
                        }else{
                            fullScreen.stop()
                            fullScreen.clearThread()
                            Tools.updateLoginUI(context,null)
                        }
                    }
            }
        }
    }

}