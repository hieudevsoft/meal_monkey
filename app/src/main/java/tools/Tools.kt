package tools

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.project.mealmonkey.R
import screens.*
import threads.ThreadFullScreen


object Tools {
    fun fullScreen(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            controller?.hide(WindowInsets.Type.statusBars())
            controller?.hide(WindowInsets.Type.navigationBars())
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            val decorView: View = window.decorView

            @Suppress("DEPRECATION")
            val uiOptions: Int = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
            @Suppress("DEPRECATION")
            decorView.systemUiVisibility = uiOptions
        }
    }

    fun moveScreenToGettingStarted(
        time: Int,
        context: Activity,
        cls: Class<Getting_Started_Screen>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
            context.finish()
        }, time.toLong());
    }

    fun moveScreenToLogin(
        time: Int,
        context: Activity,
        cls: Class<Login>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
        }, time.toLong());
    }

    fun moveScreenToSignUp(
        time: Int,
        context: Activity,
        cls: Class<SignUp>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
        }, time.toLong());
    }

    fun moveScreenToSliderApp(
        time: Int,
        context: Activity,
        cls: Class<Slider_App>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
            context.finish()
        }, time.toLong());
    }

    fun moveScreenToResetPass(
        time: Int,
        context: Activity,
        cls: Class<ResetPassword>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
            context.finish()
        }, time.toLong());
    }

    fun moveScreenToOtpScreen(
        time: Int,
        context: Activity,
        cls: Class<OTP_Screen>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
            context.finish()
        }, time.toLong());
    }


    fun killProcess() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    fun showSnackbar(view: View, isConnection: Boolean) {
        val color: Int?
        val message: String
        if (isConnection) {
            message = "Connected to Internet"
            color = Color.WHITE
        } else {
            message = "Not Connected to Internet"
            color = Color.RED
        }
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val viewSnackbar = snackbar.view
        viewSnackbar.findViewById<TextView>(R.id.snackbar_text).setTextColor(color)
        snackbar.show()
    }

    fun makeSnackbar(view: View, message:String,isFailure: Boolean) {
        val color = if (isFailure) {
            Color.WHITE
        } else {
            Color.RED
        }
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val viewSnackbar = snackbar.view
        viewSnackbar.findViewById<TextView>(R.id.snackbar_text).setTextColor(color)
        snackbar.show()
    }

    fun makeToast(context: Context,message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    class FullScreenThread(_miliseconds: Long, activity: Activity, window: Window) {
        private var miliseconds = _miliseconds
        object onThread{
            @SuppressLint("StaticFieldLeak")
            @JvmStatic var runnableFullScreen: ThreadFullScreen? = null
            @JvmStatic lateinit var thread: Thread
        }


        init {
            onThread.runnableFullScreen = ThreadFullScreen(miliseconds, activity, window)
            onThread.thread = Thread(onThread.runnableFullScreen)
        }

        fun start() {
            onThread.runnableFullScreen?.start()
            onThread.thread.start()
        }

        fun stop() {
            Log.d("Thread", "stop: ${onThread.runnableFullScreen}")
            onThread.runnableFullScreen?.stop()
        }

        fun clearThread(){
            Log.d("Thread", "clearThread: ${onThread.runnableFullScreen}")
            onThread.runnableFullScreen = null
        }

        fun getAddressThread() = onThread.runnableFullScreen
    }
     fun updateLoginUI(context: Activity,user: FirebaseUser?){
        user.let {
            Tools.moveScreenToSliderApp(
                500,
                context,
                Slider_App::class.java
            )
        }
    }

}