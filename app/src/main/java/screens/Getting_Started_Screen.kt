package screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.mealmonkey.databinding.ActivityGettingStartedScreenBinding
import firebase.FirebaseAuthManager
import tools.Tools

class Getting_Started_Screen : AppCompatActivity() {
    private val TAG = "Getting_Started_Screen"
    lateinit var binding: ActivityGettingStartedScreenBinding
    lateinit var fullScreen: Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingStartedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreen = Tools.FullScreenThread(1500, this, window)
        binding.loginGettingStarted.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            FirebaseAuthManager.instance.operationStateAuthFirebase(this)
            FirebaseAuthManager.instance.attachState()
        }
        binding.createAccountGettingStarted.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.moveScreenToSignUp(1000, this, SignUp::class.java)
        }
    }

    override fun onResume() {
        fullScreen.clearThread()
        fullScreen = Tools.FullScreenThread(1500, this, window)
        Log.d("Thread", "onResume: ${fullScreen.getAddressThread()}")
        fullScreen.start()
        super.onResume()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }

    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        Log.d("Thread", "onStop: onStop Getting Started")
        fullScreen.stop()
        fullScreen.clearThread()
        if(FirebaseAuthManager.instance.getState()!=null){
            FirebaseAuthManager.instance.getFirebaseAuth().removeAuthStateListener(
                FirebaseAuthManager.instance.getState()!!
            )
        }
        super.onStop()
    }
}