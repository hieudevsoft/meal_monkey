package screens

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import classes.Profile
import com.project.mealmonkey.R
import com.project.mealmonkey.databinding.ActivitySignupBinding
import networks.ConnectionReceiver
import networks.NetworkHelper
import firebase.FirebaseAuthManager
import firebase.FirebaseDatabaseManager
import tools.Tools
import viewmodels.ViewModelsLogic
import viewmodels.ViewModelsRegister

class SignUp : AppCompatActivity(), ConnectionReceiver.ReceiverListener {
    lateinit var binding: ActivitySignupBinding
    lateinit var threadFullScreen: Tools.FullScreenThread
    lateinit var logicModel: ViewModelsLogic
    lateinit var viewModelRegister: ViewModelsRegister
    private val dialogLoading = Tools.LoadingDialog(this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        threadFullScreen = Tools.FullScreenThread(1000, this, window)
        logicModel = ViewModelProvider(this).get(ViewModelsLogic::class.java)
        viewModelRegister = ViewModelProvider(this).get(ViewModelsRegister::class.java)
        setContentView(binding.root)
        subscribeObservers()
        checkLogic()
        binding.btnSignUp.setOnClickListener {
            threadFullScreen.stop()
            threadFullScreen.clearThread()
            dialogLoading.startLoadingDialog()
            NetworkHelper.checkInternet(this, this)
            viewModelRegister.register(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())
        }
        binding.tvLogin.setOnClickListener {
            threadFullScreen.stop()
            threadFullScreen.clearThread()
            Tools.moveScreenToLogin(500,this,Login::class.java)
        }
    }

    fun checkFormEmpty(): Boolean = binding.edtName.text.toString().isBlank() ||
            binding.edtEmail.text.toString().isBlank() ||
            binding.edtAddress.text.toString().isBlank() ||
            binding.edtMobile.text.toString().isBlank() ||
            binding.edtPassword.text.toString().isBlank() ||
            binding.edtCfPassword.text.toString().isBlank()

    private fun checkLogic() {
        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logicModel.validName(s.toString())
                checkAccountValid()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logicModel.validEmail(s.toString())
                checkAccountValid()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.edtMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logicModel.validPhoneNumber(s.toString())
                checkAccountValid()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.edtAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logicModel.validAddress(s.toString())
                checkAccountValid()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logicModel.validPassword(s.toString())
                checkAccountValid()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.edtCfPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logicModel.validConfirmPassword(binding.edtPassword.text.toString(), s.toString())
                checkAccountValid()

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

    fun checkAccountValid() {
        logicModel.validAccount(
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtMobile.text.toString(),
            binding.edtAddress.text.toString(),
            binding.edtPassword.text.toString(),
            binding.edtCfPassword.text.toString()
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun subscribeObservers() {

        viewModelRegister.getResultRegister().observe(this, {
            if (it && !viewModelRegister.getRequestTimeOutRegister().value!!) {
                dialogLoading.dismissDialog()
                viewModelRegister.didRegisterSuccess = true
                FirebaseDatabaseManager.instance.writeProfile(
                    "users",
                    Profile(
                        FirebaseAuthManager.instance.getCurrentUser()?.uid,
                        binding.edtEmail.text.toString(),
                        binding.edtPassword.text.toString(),
                        binding.edtName.text.toString(),
                        binding.edtAddress.text.toString(),
                        binding.edtMobile.text.toString(),
                        "", 0, ""
                    )
                )
                Tools.moveScreenToSliderApp(500,this,Slider_App::class.java)
                Tools.makeToast(this, "Register Successfully")
            } else {
                dialogLoading.dismissDialog()
                Tools.makeSnackbar(
                    binding.btnSignUp,
                    viewModelRegister.getResultException().value!!,
                    false
                )
            }
        })

        viewModelRegister.getRequestTimeOutRegister().observe(this,{
            if(it && !viewModelRegister.didRegisterSuccess){
                dialogLoading.dismissDialog()
                Tools.makeSnackbar(binding.btnSignUp,"Request Time out ~ Register failure ! ",false)
            }
        })

        logicModel.getValidName().observe(this, {
            if (!it && it != null) {
                binding.edtName.error = "Name is not Valid"
            }
        })
        logicModel.getValidEmail().observe(this) {
            if (!it && it != null) {
                binding.edtEmail.error = "Email is not Valid"
            }

        }
        logicModel.getValidPhone().observe(this) {
            if (!it && it != null) {
                binding.edtMobile.error = "Phone Number is not Valid"
            }

        }
        logicModel.getValidAddress().observe(this) {
            if (!it && it != null) {
                binding.edtAddress.error = "Address is not Valid"
            }

        }
        logicModel.getValidPassword().observe(this) {
            if (!it && it != null) {
                binding.edtPassword.error = "Password is minimum character 7"
            }

        }

        logicModel.getValidConfirmPassword().observe(this) {
            if (!it && it != null) {
                binding.edtCfPassword.error = "Confirm Password is not equal Password"
            }

        }

        logicModel.getValidAccount().observe(this) {
            if (checkFormEmpty()) {
                binding.btnSignUp.isEnabled = false
                binding.btnSignUp.background.setTint(
                    resources.getColor(
                        R.color.gray_shadow,
                        null
                    )
                )
            } else {
                Log.d("TAG", "subscribeObservers: NotEmpty $it")
                binding.btnSignUp.isEnabled = it
                if (!it) binding.btnSignUp.background.setTint(
                    resources.getColor(
                        R.color.gray_shadow,
                        null
                    )
                )
                else binding.btnSignUp.background.setTint(
                    resources.getColor(
                        R.color.main_color,
                        null
                    )
                )
            }
        }
    }

    override fun onPause() {
        threadFullScreen.stop()
        threadFullScreen.clearThread()
        NetworkHelper.checkInternet(this, this)
        super.onPause()
    }

    override fun onResume() {
        Log.d("Thread", "onResume: resume")
        Handler(Looper.getMainLooper()).postDelayed({
            threadFullScreen.stop()
            threadFullScreen.let {
                threadFullScreen.clearThread()
            }
            threadFullScreen = Tools.FullScreenThread(3000, this, window)
            threadFullScreen.start()
        }, 3000)
        super.onResume()
    }

    override fun onBackPressed() {
        Tools.killProcess()
    }

    override fun onStop() {
        NetworkHelper.unCheckInternet(this)
        threadFullScreen.stop()
        threadFullScreen.clearThread()
        super.onStop()
    }

    override fun onNetWorkChange(network: Boolean) {
        if (!network)
            Tools.showSnackbar(binding.btnSignUp, network)
    }

}