package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R
import holder_of_adapter.OptionsHolder

class OptionsAdapter(_context: Context) : RecyclerView.Adapter<OptionsHolder>() {
    var context = _context
    var images = listOf<Int>(R.drawable.image_offer,R.drawable.image_indian,R.drawable.image_italian,R.drawable.image_srilankan,R.drawable.image_offer)
    var texts = listOf<String>("Offers","Indian","Italian","Sri Lankan","American")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsHolder {
        return OptionsHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_options,parent,false))
    }

    override fun onBindViewHolder(holder: OptionsHolder, position: Int) {
        holder.setData(image = images[position],text = texts[position])
    }

    override fun getItemCount(): Int {
        return 5
    }


}