package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.mealmonkey.R
import com.smarteist.autoimageslider.SliderViewAdapter
import holder_of_adapter.SliderHolder

class SliderAdapter(_context: Context) : SliderViewAdapter<SliderHolder>() {
    var context = _context
    var images = listOf<Int>(R.drawable.image_findfoodlove,R.drawable.image_fastdelivery,R.drawable.image_livetracking)
    var texts = listOf<String>(context.getString(R.string.text_on_boarding_1),context.getString(R.string.text_on_boarding_2),context.getString(R.string.text_on_boarding_3))
    var headers = listOf<String>(context.getString(R.string.header_on_boarding_1),context.getString(R.string.header_on_boarding_2),context.getString(R.string.header_on_boarding_3))
    override fun getCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_slider,parent,false)
        return SliderHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderHolder?, position: Int) {
        viewHolder?.setData(images[position],headers[position],texts[position])
    }


}