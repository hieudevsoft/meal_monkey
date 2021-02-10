package tools

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation

object Animation {
    fun animationSplashScreen(view: View){
        val animation = AlphaAnimation(0f,1f);
        animation.duration = 2000
        animation.fillAfter = true
        animation.startOffset = 1000
        animation.interpolator = AccelerateDecelerateInterpolator()
        view.startAnimation(animation)
    }
}