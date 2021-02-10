package screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mealmonkey.databinding.ActivitySignupBinding
import tools.Tools

class signup : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var threadFullScreen:Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        threadFullScreen = Tools.FullScreenThread(1500, this, window)
        setContentView(binding.root)
    }

    override fun onResume() {
        threadFullScreen.start()
        super.onResume()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }
}