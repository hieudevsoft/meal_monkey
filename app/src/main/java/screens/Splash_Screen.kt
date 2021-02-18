package screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mealmonkey.databinding.ActivitySplashScreenBinding
import tools.Animation
import tools.Tools


class Splash_Screen : AppCompatActivity() {
    lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Animation.animationSplashScreen(binding.mainLayoutSplash)
        Tools.moveScreenToGettingStarted(3500,this,Getting_Started_Screen::class.java)
    }

    override fun onResume() {
        super.onResume()
        Tools.fullScreen(window)
    }
    override fun hasWindowFocus(): Boolean {
        if(hasWindowFocus()){
         Tools.fullScreen(window)
        }
        return super.hasWindowFocus()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }
}