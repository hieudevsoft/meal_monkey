package screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mealmonkey.databinding.ActivityLoginBinding
import tools.Tools

class login : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var fullScreen: Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen = Tools.FullScreenThread(1500,this,window)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        fullScreen.start()
        super.onResume()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }
}