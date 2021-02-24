package screens

import adapter.SliderAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.project.mealmonkey.databinding.ActivitySliderAppBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repositories.DataStoreRepository
import tools.Tools


class Slider_App : AppCompatActivity() {
    lateinit var binding:ActivitySliderAppBinding
    lateinit var fullScreen: Tools.FullScreenThread
    lateinit var sliderAdapter: SliderAdapter
    lateinit var dataStoreRepository: DataStoreRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreen = Tools.FullScreenThread(window = window,_miliseconds = 1500,activity = this)
        initSliderView()
        dataStoreRepository = DataStoreRepository(this)
        binding.btnNext.setOnClickListener {
            //SignInWithFacebook.instance.logOutWithFacebook(this,it)
            GlobalScope.launch {
                dataStoreRepository.saveStateSkip(false)
            }
        }
        subscribeObservers()
        binding.btnSkip.setOnClickListener {
            GlobalScope.launch {
                dataStoreRepository.saveStateSkip(true)
            }
        }
    }

    private fun subscribeObservers(){
        dataStoreRepository.readStateKip()
            .asLiveData().observe(this){
                if(it){
                    Tools.makeToast(this,"Already Skip")
                }else{
                    Tools.makeToast(this,"Not Skip")
                }
            }
    }

    override fun onResume() {
        Handler(Looper.getMainLooper()).postDelayed({
            fullScreen.stop()
            fullScreen.let {
                fullScreen.clearThread()
            }
            fullScreen = Tools.FullScreenThread(3000,this,window)
            fullScreen.start()
        },1000)
        super.onResume()
    }

    override fun onPause() {
        Log.d("Thread", "onPause: Pause Login")
        fullScreen.stop()
        fullScreen.clearThread()
        super.onPause()
    }

    override fun onBackPressed() {
        fullScreen.stop()
        fullScreen.clearThread()
        finish()
    }

    private fun initSliderView(){
        sliderAdapter = SliderAdapter(this)
        binding.sliderView.setSliderAdapter(sliderAdapter)
        binding.sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINSCALINGTRANSFORMATION)
        binding.sliderView.startAutoCycle()
    }
}