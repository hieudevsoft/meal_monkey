package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import classes.OrderFood
import com.project.mealmonkey.R
import com.ramotion.foldingcell.FoldingCell
import holder_of_adapter.OrderFoodHolder

class OrderFoodsAdapter(_context: Context) : RecyclerView.Adapter<OrderFoodHolder>() {
    var context = _context
    private lateinit var list:List<OrderFood>
    fun setListOrderFood(listOrderFood:List<OrderFood>){
        list = listOrderFood
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderFoodHolder {
        return OrderFoodHolder(LayoutInflater.from(context).inflate(R.layout.item_order_food,parent,false))
    }

    override fun onBindViewHolder(holder: OrderFoodHolder, position: Int) {
        holder.setData(list[position])
        val fc = holder.view.findViewById<FoldingCell>(R.id.folding_cell)
        fc.setOnClickListener {
            fc.toggle(false)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}