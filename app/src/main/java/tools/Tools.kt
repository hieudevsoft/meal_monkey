package tools

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import com.project.mealmonkey.R
import screens.getting_started_screen
import screens.login
import screens.signup
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
        cls: Class<getting_started_screen>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
        }, time.toLong());
    }
    fun moveScreenToLogin(
        time: Int,
        context: Activity,
        cls: Class<login>
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
        cls: Class<signup>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, cls)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
        }, time.toLong());
    }

    fun killProcess() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    class FullScreenThread(_miliseconds: Long, activity: Activity, window: Window) {
        var miliseconds = _miliseconds
        var runnableFullScreen: ThreadFullScreen
        var thread: Thread

        init {
            runnableFullScreen = ThreadFullScreen(miliseconds, activity, window)
            thread = Thread(runnableFullScreen)
        }

        fun start() {
            thread.start()
        }

        fun stop() {
            runnableFullScreen.stop()
        }

    }
}