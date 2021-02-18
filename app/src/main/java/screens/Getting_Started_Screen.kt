package screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.mealmonkey.databinding.ActivityGettingStartedScreenBinding
import tools.Tools

class Getting_Started_Screen : AppCompatActivity() {
    lateinit var binding:ActivityGettingStartedScreenBinding
    lateinit var fullScreen:Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingStartedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreen = Tools.FullScreenThread(1500,this,window)
        binding.loginGettingStarted.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.moveScreenToLogin(1000,this,Login::class.java)
        }
        binding.createAccountGettingStarted.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.moveScreenToSignUp(1000,this,SignUp::class.java)
        }
    }
    override fun onResume() {
        fullScreen.clearThread()
        fullScreen = Tools.FullScreenThread(1500,this,window)
        Log.d("Thread", "onResume: ${fullScreen.getAddressThread()}")
        fullScreen.start()
        super.onResume()
    }
    override fun onBackPressed() {
        Tools.killProcess()
    }

    override fun onStop() {
        Log.d("Thread", "onStop: onStop Getting Started")
        fullScreen.stop()
        fullScreen.clearThread()
        super.onStop()
    }
}