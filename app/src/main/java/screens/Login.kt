package screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.project.mealmonkey.databinding.ActivityLoginBinding
import networks.ConnectionReceiver
import networks.NetworkHelper
import signin_options.SignInWithFacebook
import signin_options.SignInWithGoogle
import tools.Tools
import viewmodels.ViewModelsLogin
import viewmodels.ViewModelsSignInGoogle

class Login : AppCompatActivity(), ConnectionReceiver.ReceiverListener {
    lateinit var binding: ActivityLoginBinding
    lateinit var fullScreen: Tools.FullScreenThread
    lateinit var viewModelSignInGoogle: ViewModelsSignInGoogle
    lateinit var viewModelLoginEmail: ViewModelsLogin
    lateinit var firebaseAuth: FirebaseAuth
    private val dialogLoading = Tools.LoadingDialog(this)
    val SIGN_IN_GOOGLE_REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NetworkHelper.checkInternet(this, this)
        fullScreen = Tools.FullScreenThread(100, this, window)
        firebaseAuth = FirebaseAuth.getInstance()
        viewModelSignInGoogle = ViewModelProvider(this).get(ViewModelsSignInGoogle::class.java)
        viewModelLoginEmail = ViewModelProvider(this).get(ViewModelsLogin::class.java)
        subscribeObservers()
        binding.btnLoginWithGoogle.setOnClickListener {
            viewModelSignInGoogle.retrieveIntentSignInGoogle(this)
        }
        binding.btnLogin.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            viewModelLoginEmail.login(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())
            dialogLoading.startLoadingDialog()
        }
        SignInWithFacebook.instance.logInWithFaceBook(this,binding.btnLoginWithFacebook)
        binding.tvSignUp.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.moveScreenToSignUp(500,this,SignUp::class.java)
        }
        binding.tvForgotPass.setOnClickListener {
            fullScreen.stop()
            fullScreen.clearThread()
            Tools.moveScreenToResetPass(500,this,ResetPassword::class.java)
        }
    }

    private fun subscribeObservers() {

        viewModelLoginEmail.getResultLogin().observe(this, {
            if (it && !viewModelLoginEmail.getRequestTimeOutLogin().value!!) {
                dialogLoading.dismissDialog()
                viewModelLoginEmail.didLoginSuccess = true
                Tools.makeToast(this, "Login Successfully")
                Tools.moveScreenToSliderApp(500,this,Slider_App::class.java)
            } else {
                dialogLoading.dismissDialog()
                Tools.makeSnackbar(
                    binding.btnLogin,
                    viewModelLoginEmail.getResultException().value!!,
                    false
                )
            }
        })

        viewModelLoginEmail.getRequestTimeOutLogin().observe(this,{
            if(it && !viewModelLoginEmail.didLoginSuccess){
                dialogLoading.dismissDialog()
                Tools.makeSnackbar(binding.btnLogin,"Request Time out ~ Login failure ! ",false)
            }
        })
        viewModelSignInGoogle.getIntentSignInWithGoogle().observe(this, {
            it?.let {
                viewModelSignInGoogle.didRetrieveIntentGoogle = true
                if (viewModelSignInGoogle.didRetrieveIntentGoogle && !viewModelSignInGoogle.getRequestTimeOutSignInWithGoogle().value!!)
                    if (!SignInWithGoogle.instance.checkLoginWithGoogle(this)){
                        startActivityForResult(it, SIGN_IN_GOOGLE_REQUEST_CODE)
                        viewModelSignInGoogle.didRetrieveIntentGoogle = false
                    }

            }
        })
        viewModelSignInGoogle.getRequestTimeOutSignInWithGoogle().observe(this, {
            it?.let {
                if (it && !viewModelSignInGoogle.didRetrieveIntentGoogle)
                    Toast.makeText(this, "Request Time Out", Toast.LENGTH_LONG).show()
            }
        })
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
        Log.d("Thread", "onResume: onResume Login ${fullScreen.getAddressThread()}")
        NetworkHelper.checkInternet(this, this)
        super.onResume()
    }

    override fun onBackPressed() {
        fullScreen.stop()
        fullScreen.clearThread()
        Tools.killProcess()
    }

    override fun onPause() {
        Log.d("Thread", "onPause: Pause Login")
        fullScreen.stop()
        fullScreen.clearThread()
        NetworkHelper.checkInternet(this, this)
        super.onPause()
    }

    override fun onNetWorkChange(network: Boolean) {
        Tools.showSnackbar(binding.btnLogin, network)
    }

    override fun onStop() {
        fullScreen.stop()
        fullScreen.clearThread()
        NetworkHelper.unCheckInternet(this)
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SIGN_IN_GOOGLE_REQUEST_CODE) {
            SignInWithGoogle.instance.loginGoogleWithFirebase(
                data,
                context = this,
                firebaseAuth,
                fullScreen
            )
        }
        super.onActivityResult(requestCode, resultCode, data)
        SignInWithFacebook.instance.mCallBackManager.onActivityResult(requestCode,resultCode,data)
    }
}