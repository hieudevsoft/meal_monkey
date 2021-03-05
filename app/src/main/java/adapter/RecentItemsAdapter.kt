package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R
import holder_of_adapter.RecentItemsHolder

class RecentItemsAdapter(_context: Context) : RecyclerView.Adapter<RecentItemsHolder>() {
    var context = _context
    var images = listOf(R.drawable.image_offer,R.drawable.image_italian,R.drawable.image_indian)
    var nameFoods = listOf("Mulberry Pizza by Josh","Barita","Pizza Rush Hour")
    var averageRatings = listOf(4.9,2.0,3.5)
    var numOfRatings = listOf(125,20,130)
    var typeOfFoods = listOf("Salad","Cake","Coffee")
    var resOfFood = listOf("Western Food","Western Food","Western Food")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentItemsHolder {
        return RecentItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_recent_items,parent,false))
    }

    override fun onBindViewHolder(holder: RecentItemsHolder, position: Int) {
        holder.setData(images[position],nameFoods[position],averageRatings[position],numOfRatings[position],typeOfFoods[position],resOfFood[position])
    }

    override fun getItemCount(): Int {
        return 3
    }


}