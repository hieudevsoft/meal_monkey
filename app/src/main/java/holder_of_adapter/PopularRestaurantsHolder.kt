package holder_of_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R


class PopularRestaurantsHolder(_view: View): RecyclerView.ViewHolder(_view) {
    val view = _view
    fun setData(image:Int,nameFood:String,aveRatingFood:Double,numOfRating:Int,typeFood:String,resOfFood:String){
        view.findViewById<ImageView>(R.id.imagePoRes).setImageResource(image)
        view.findViewById<TextView>(R.id.textNameFoodPoRes).text = nameFood
        view.findViewById<TextView>(R.id.textAveRatingPoRes).text = aveRatingFood.toString()
        view.findViewById<TextView>(R.id.textNumRatingPoRes).text = "($numOfRating ratings)"
        view.findViewById<TextView>(R.id.textTypeFoodPoRes).text = typeFood
        view.findViewById<TextView>(R.id.textResPoRes).text = resOfFood
    }
}