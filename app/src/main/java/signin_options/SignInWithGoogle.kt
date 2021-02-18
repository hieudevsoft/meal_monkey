package signin_options

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import values.Constants

class SignInWithGoogle private constructor() {
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
}