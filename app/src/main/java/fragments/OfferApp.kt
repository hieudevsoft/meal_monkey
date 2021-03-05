package fragments

import adapter.OfferFoodAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mealmonkey.databinding.FragmentOfferAppBinding


class OfferApp : Fragment() {
    private lateinit var _binding:FragmentOfferAppBinding
    private val binding get() = _binding
    private val TAG = "OfferApp" 
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferAppBinding.inflate(inflater)
        Log.d(TAG, "onCreateView: onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: onViewCreated")
        initAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter(){
        val layoutManager = LinearLayoutManager(context)
        binding.recycleViewOffer.layoutManager =layoutManager
        val adapter= OfferFoodAdapter()
        binding.recycleViewOffer.adapter= adapter
    }
}