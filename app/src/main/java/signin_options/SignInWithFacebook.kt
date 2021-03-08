package signin_options

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.facebook.*
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import screens.Login
import firebase.FirebaseAuthManager
import tools.Tools

class SignInWithFacebook private constructor(){
    lateinit var mCallBackManager:CallbackManager
    private object Holder{
        val instance: SignInWithFacebook = SignInWithFacebook()
    }
    companion object{
        private val TAG = "SignInWithFaceBook"
        val instance:SignInWithFacebook by lazy { Holder.instance }
    }

    fun logInWithFaceBook(context: Activity,loginButton: Button){
        FacebookSdk.sdkInitialize(context)
        mCallBackManager = CallbackManager.Factory.create()
        loginButton.setOnClickListener {
            LoginManager.getInstance().loginBehavior = LoginBehavior.WEB_VIEW_ONLY
            LoginManager.getInstance().registerCallback(mCallBackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.d(TAG, "onSuccess: OK Login Facebook")
                    handlerFacebookToken(context, result?.accessToken, loginButton)
                }

                override fun onCancel() {
                    Tools.makeSnackbar(loginButton, "Cancel Login With Facebook", false)
                }

                override fun onError(error: FacebookException?) {
                    Tools.makeSnackbar(
                        loginButton,
                        "Error Login With Facebook: ${error?.message}",
                        false
                    )
                }
            })
            LoginManager.getInstance().logInWithReadPermissions(context, listOf("email","public_profile","user_friends"))
        }
    }

    private fun handlerFacebookToken(context: Activity,accessToken: AccessToken?,loginButton: Button) {
        val credential = FacebookAuthProvider.getCredential(accessToken?.token!!)
        FirebaseAuthManager.instance.getFirebaseAuth().signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                Tools.makeToast(context,"Login With FaceBook Successfully")
                Tools.updateLoginUI(context, FirebaseAuthManager.instance.getCurrentUser())
            }else{
                Tools.makeSnackbar(loginButton,"Failure Login With Facebook",false)
                Tools.updateLoginUI(context,null)
            }
        }
    }

    fun logOutWithFacebook(context: Activity,view:View){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Question ?");
        dialog.setMessage("Do you want Sign Out?")
        dialog.setPositiveButton("Yes"
        ) { _, _ ->
            FirebaseAuthManager.instance.getFirebaseAuth().signOut()
            LoginManager.getInstance().let {
                LoginManager.getInstance().logOut()
            }
            Tools.moveScreenToLogin(1000,context = context, Login::class.java)
            Tools.makeToast(context,"Sign Out Successfully~")
            }
        dialog.setNegativeButton("No"){
                _,_->
        }
        dialog.show()
    }
}