package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R
import holder_of_adapter.MostPopularHolder

class MostPopularAdapter(_context: Context) : RecyclerView.Adapter<MostPopularHolder>() {
    var context = _context
    var images = listOf(R.drawable.image_indian,R.drawable.image_offer,R.drawable.image_indian)
    var nameFoods = listOf("Café De Bambaa","Burger by Bella","Bakes by Tella")
    var averageRatings = listOf(4.9,2.0,3.5)
    var typeOfFoods = listOf("Salad","Cake","Coffee")
    var resOfFood = listOf("Western Food","Western Food","Western Food")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularHolder {
        return MostPopularHolder(LayoutInflater.from(context).inflate(R.layout.item_most_popular_food,parent,false))
    }

    override fun onBindViewHolder(holder: MostPopularHolder, position: Int) {
        holder.setData(images[position],nameFoods[position],averageRatings[position],typeOfFoods[position],resOfFood[position])
    }

    override fun getItemCount(): Int {
        return 3
    }


}