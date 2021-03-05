package holder_of_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mealmonkey.R


class OptionsHolder(_view: View): RecyclerView.ViewHolder(_view) {
    val view = _view
    fun setData(image:Int,text:String){
        view.findViewById<ImageView>(R.id.imageOptionsItem).setImageResource(image)
        view.findViewById<TextView>(R.id.textOptionsItem).text = text
    }
}