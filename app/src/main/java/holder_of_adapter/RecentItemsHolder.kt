package holder_of_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R


class RecentItemsHolder(_view: View): RecyclerView.ViewHolder(_view) {
    val view = _view
    fun setData(image:Int,nameFood:String,aveRatingFood:Double,numOfRating:Int,typeFood:String,resOfFood:String){
        view.findViewById<ImageView>(R.id.imageRecentItem).setImageResource(image)
        view.findViewById<TextView>(R.id.textNameRecentItem).text = nameFood
        view.findViewById<TextView>(R.id.textAveRatingRecentItem).text = aveRatingFood.toString()
        view.findViewById<TextView>(R.id.textNumRatingRecentItem).text = "($numOfRating ratings)"
        view.findViewById<TextView>(R.id.textTypeFoodRecentItem).text = typeFood
        view.findViewById<TextView>(R.id.textResRecentItem).text = resOfFood
    }
}