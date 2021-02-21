package screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.mealmonkey.databinding.ActivitySliderAppBinding
import signin_options.SignInWithFacebook
import signin_options.SignInWithGoogle
import tools.Tools


class Slider_App : AppCompatActivity() {
    lateinit var binding:ActivitySliderAppBinding
    lateinit var fullScreen: Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreen = Tools.FullScreenThread(window = window,_miliseconds = 1500,activity = this)
        binding.logout.setOnClickListener {
            if(SignInWithGoogle.instance.checkLoginWithGoogle(this)) SignInWithGoogle.instance.logOutGoogle(this,binding.logout)
            else SignInWithFacebook.instance.logOutWithFacebook(this,binding.logout)
        }
        binding.checkScreenOTP.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.moveScreenToOtpScreen(500,this,OTP_Screen::class.java)
        }
    }

    override fun onResume() {
        Handler(Looper.getMainLooper()).postDelayed({
            fullScreen.stop()
            fullScreen.let {
                fullScreen.clearThread()
            }
            fullScreen = Tools.FullScreenThread(3000,this,window)
            fullScreen.start()
        },1000)
        super.onResume()
    }

    override fun onPause() {
        Log.d("Thread", "onPause: Pause Login")
        fullScreen.stop()
        fullScreen.clearThread()
        super.onPause()
    }

    override fun onBackPressed() {
        fullScreen.stop()
        fullScreen.clearThread()
        finish()
    }
}