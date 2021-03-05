package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.mealmonkey.R
import ir.sinadalvand.libs.triangleimageview.TriangleImageView


class MenuApp : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getView()?.findViewById<TriangleImageView>(R.id.imageTriangle)?.radius = 26F
        getView()?.findViewById<TriangleImageView>(R.id.imageTriangle)?.morph = 0F
        super.onViewCreated(view, savedInstanceState)
    }

}