package screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mealmonkey.databinding.ActivitySignupBinding
import networks.ConnectionReceiver
import networks.NetworkHelper
import tools.Tools

class signup : AppCompatActivity(),ConnectionReceiver.ReceiverListener {
    lateinit var binding: ActivitySignupBinding
    lateinit var threadFullScreen:Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        threadFullScreen = Tools.FullScreenThread(1500, this, window)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener {
            NetworkHelper.checkInternet(this,this)
        }
    }

    override fun onPause() {
        NetworkHelper.checkInternet(this,this)
        super.onPause()
    }
    override fun onResume() {
        threadFullScreen.start()
        super.onResume()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }

    override fun onStop() {
        NetworkHelper.unCheckInternet(this)
        super.onStop()
    }

    override fun onNetWorkChange(network: Boolean) {
        Tools.showSnackbar(binding.btnSignUp,network)
    }

}