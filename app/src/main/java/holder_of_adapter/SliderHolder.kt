package holder_of_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.project.mealmonkey.R
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderHolder(_view: View): SliderViewAdapter.ViewHolder(_view) {
    val view = _view
    fun setData(image:Int,header:String,text:String){
        view.findViewById<ImageView>(R.id.imageSlide).setImageResource(image)
        view.findViewById<TextView>(R.id.tvHeaderSlide).text = header
        view.findViewById<TextView>(R.id.tvTextSlide).text = text
    }
}