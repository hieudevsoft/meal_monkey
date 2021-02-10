package screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mealmonkey.R
import com.project.mealmonkey.databinding.ActivityGettingStartedScreenBinding
import threads.ThreadFullScreen
import tools.Tools
import kotlin.system.exitProcess

class getting_started_screen : AppCompatActivity() {
    lateinit var binding:ActivityGettingStartedScreenBinding
    lateinit var fullScreen:Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingStartedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreen = Tools.FullScreenThread(1500,this,window)
        binding.loginGettingStarted.setOnClickListener {
            fullScreen.stop()
            Tools.moveScreenToLogin(500,this,login::class.java)
        }
        binding.createAccountGettingStarted.setOnClickListener {
            fullScreen.stop()
            Tools.moveScreenToSignUp(500,this,signup::class.java)
        }

    }
    override fun onResume() {
        fullScreen.start()
        super.onResume()
    }
    override fun onBackPressed() {
        Tools.killProcess()
    }
}