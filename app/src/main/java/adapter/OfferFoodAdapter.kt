package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R

class OfferFoodAdapter: RecyclerView.Adapter<OfferFoodAdapter.ViewHolder>() {
    private var images = arrayOf(R.drawable.image_offer,R.drawable.image_indian,R.drawable.image_srilankan,R.drawable.image_italian)
    private var title = arrayOf("French Apple Pie","Dark Chocolate Cake","Street Shake","Fudgy Chewy Brownies")
    private var ratting = arrayOf("4.9","4.7","4.8","4.6")
    private var sold = arrayOf("Minute by tuk tuk","Cakes by Tella","café Racer","Minute by tuk tuk")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferFoodAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_offer_app,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: OfferFoodAdapter.ViewHolder, position: Int) {
        holder.itemImageFood.setImageResource(images[position])
        holder.itemTitle.text=title[position]
        holder.itemRating.text=ratting[position]
        holder.itemSold.text=sold[position]
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemImageFood: ImageView = itemView.findViewById(R.id.image_food1)
        val itemTitle: TextView = itemView.findViewById(R.id.text_title)
        val itemRating: TextView = itemView.findViewById(R.id.text_rating)
        val itemSold: TextView = itemView.findViewById(R.id.text_sold)

    }
}