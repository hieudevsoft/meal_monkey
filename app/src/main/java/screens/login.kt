package screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.mealmonkey.databinding.ActivityLoginBinding
import networks.ConnectionReceiver
import networks.NetworkHelper
import tools.Tools

class login : AppCompatActivity(),ConnectionReceiver.ReceiverListener {
    lateinit var binding:ActivityLoginBinding
    lateinit var fullScreen: Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen = Tools.FullScreenThread(1500,this,window)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NetworkHelper.checkInternet(this,this)

    }

    override fun onResume() {
        fullScreen.start()
        NetworkHelper.checkInternet(this,this)
        super.onResume()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }

    override fun onPause() {
        NetworkHelper.checkInternet(this,this)
        super.onPause()
    }

    override fun onNetWorkChange(network: Boolean) {
        Tools.showSnackbar(binding.btnLogin,network)
    }

    override fun onStop() {
        NetworkHelper.unCheckInternet(this)
        super.onStop()
    }
}