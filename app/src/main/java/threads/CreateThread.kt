package threads

import android.app.Activity
import android.util.Log
import android.view.Window
import tools.Tools

class ThreadFullScreen(_miliSeconds: Long, private val activity: Activity, _window: Window) :
    Runnable {
    var miliSeconds = _miliSeconds
    private val window = _window

    @Volatile
    var cancelThread = true
    override fun run() {
        while (cancelThread) {
            Log.d("Thread", "run: running $this")
            activity.runOnUiThread {
                Tools.fullScreen(window)
            }
            Thread.sleep(miliSeconds)
        }
    }

    fun stop() {
        cancelThread = false
    }
    fun start(){
        cancelThread = true
    }
}