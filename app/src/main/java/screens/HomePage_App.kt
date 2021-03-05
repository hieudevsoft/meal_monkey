package screens

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.mealmonkey.R
import com.project.mealmonkey.databinding.ActivityHomePageAppBinding
import tools.Tools

class HomePage_App : AppCompatActivity() {
    lateinit var _bingding : ActivityHomePageAppBinding
    private val binding get() = _bingding
    lateinit var fullScreen: Tools.FullScreenThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bingding = ActivityHomePageAppBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragmentHost)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.selectedItemId = R.id.placeholder

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.fabHome.setOnClickListener {
            Navigation.findNavController(this,R.id.fragmentHost).navigate(R.id.homeApp)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }
}