package holder_of_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import classes.OrderFood
import com.project.mealmonkey.R


class OrderFoodHolder(_view: View): RecyclerView.ViewHolder(_view) {
    val view = _view
    fun setData(food: OrderFood){
        view.findViewById<ImageView>(R.id.imageFoodOrder).setImageResource(food.urlImage)
        view.findViewById<TextView>(R.id.textNameFoodOrder).text = food.nameFood
        view.findViewById<TextView>(R.id.textRatingFoodOrder).text = food.averageRating.toString()
        view.findViewById<TextView>(R.id.textNumRatingFoodOrder).text = "("+food.nameFood+")"
        view.findViewById<TextView>(R.id.textTypeFoodOrder).text = food.typeFood
        view.findViewById<TextView>(R.id.textNameResFoodOrder).text = food.nameRestaurants
        view.findViewById<TextView>(R.id.textAddressResFoodOrder).text = food.restaurantAddress
        view.findViewById<TextView>(R.id.textNameFoodOrderTitle).text = food.nameFood
        view.findViewById<TextView>(R.id.textCountFoodOrder).text = "x "+food.count.toString()
        view.findViewById<TextView>(R.id.textSumPriceFoodOrder).text = "$"+(food.count*food.priceFood)
    }
}