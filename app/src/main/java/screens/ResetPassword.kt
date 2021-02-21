package screens


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.mealmonkey.databinding.ActivityResetPasswordBinding
import tools.Tools

class ResetPassword : AppCompatActivity() {
    lateinit var binding:ActivityResetPasswordBinding
    lateinit var fullScreen: Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        fullScreen = Tools.FullScreenThread(100, this, window)
        setContentView(binding.root)
    }

    override fun onResume() {
        Handler(Looper.getMainLooper()).postDelayed({
            fullScreen.stop()
            fullScreen.let {
                fullScreen.clearThread()
            }
            fullScreen = Tools.FullScreenThread(3000, this, window)
            fullScreen.start()
        }, 1000)
        Log.d("Thread", "onResume: onResume ResetPass ${fullScreen.getAddressThread()}")
        super.onResume()
    }

    override fun onStop() {
        fullScreen.stop()
        fullScreen.clearThread()
        super.onStop()
    }

    override fun onBackPressed() {
        fullScreen.stop()
        fullScreen.clearThread()
        Tools.killProcess()
        super.onBackPressed()
    }
}