package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R
import holder_of_adapter.PopularRestaurantsHolder

class PopularRestaurantsAdapter(_context: Context) : RecyclerView.Adapter<PopularRestaurantsHolder>() {
    var context = _context
    var images = listOf(R.drawable.image_indian,R.drawable.image_srilankan,R.drawable.image_offer)
    var nameFoods = listOf("Minute by tuk tuk","Café de Noir","Bakes by Tella")
    var averageRatings = listOf(4.9,2.0,3.5)
    var numOfRatings = listOf(125,20,130)
    var typeOfFoods = listOf("Salad","Cake","Coffee")
    var resOfFood = listOf("Western Food","Western Food","Western Food")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularRestaurantsHolder {
        return PopularRestaurantsHolder(LayoutInflater.from(context).inflate(R.layout.item_popular_restaurants,parent,false))
    }

    override fun onBindViewHolder(holder: PopularRestaurantsHolder, position: Int) {
        holder.setData(images[position],nameFoods[position],averageRatings[position],numOfRatings[position],typeOfFoods[position],resOfFood[position])
    }

    override fun getItemCount(): Int {
        return 3
    }


}