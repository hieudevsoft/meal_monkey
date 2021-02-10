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
    lateinit var runnableFullScreen:ThreadFullScreen
    lateinit var thread: Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingStartedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runnableFullScreen = ThreadFullScreen(1500,this,window)
        thread = Thread(runnableFullScreen)
        binding.loginGettingStarted.setOnClickListener {
            runnableFullScreen.stop()
        }

    }
    override fun onResume() {
        thread.start()
        Tools.fullScreen(window)
        super.onResume()
    }
    override fun onBackPressed() {
        Tools.killProcess()
    }
}