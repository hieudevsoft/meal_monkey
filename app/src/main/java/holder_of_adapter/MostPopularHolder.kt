package holder_of_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R


class MostPopularHolder(_view: View): RecyclerView.ViewHolder(_view) {
    val view = _view
    fun setData(image:Int,nameFood:String,aveRatingFood:Double,typeFood:String,resOfFood:String){
        view.findViewById<ImageView>(R.id.imageMostPopular).setImageResource(image)
        view.findViewById<TextView>(R.id.textNameFoodMostPopular).text = nameFood
        view.findViewById<TextView>(R.id.textAveRatingMostPopular).text = aveRatingFood.toString()
        view.findViewById<TextView>(R.id.textTypeFoodMostPopular).text = typeFood
        view.findViewById<TextView>(R.id.textResMostPopular).text = resOfFood
    }
}