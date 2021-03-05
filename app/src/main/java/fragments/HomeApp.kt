package fragments

import adapter.MostPopularAdapter
import adapter.OptionsAdapter
import adapter.PopularRestaurantsAdapter
import adapter.RecentItemsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mealmonkey.databinding.FragmentHomeAppBinding


class HomeApp : Fragment() {
    private var _binding : FragmentHomeAppBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAppBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerViewOptions()
        initRecyclerViewPopularRestaurants()
        initRecyclerViewMostPopular()
        initRecyclerViewRecentItems()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerViewOptions(){
        val recycleView = binding.recycleViewOptions
        recycleView.adapter = context?.let { OptionsAdapter(it) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        recycleView.layoutManager = layoutManager
    }

    private fun initRecyclerViewPopularRestaurants(){
        val recycleView = binding.recycleViewPopularRestaurents
        recycleView.adapter = context?.let { PopularRestaurantsAdapter(it) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recycleView.layoutManager = layoutManager
    }

    private fun initRecyclerViewMostPopular(){
        val recycleView = binding.recycleViewMostPopular
        recycleView.adapter = context?.let { MostPopularAdapter(it) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        recycleView.layoutManager = layoutManager
    }

    private fun initRecyclerViewRecentItems(){
        val recycleView = binding.recycleRecentItems
        recycleView.adapter = context?.let { RecentItemsAdapter(it) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recycleView.layoutManager = layoutManager
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}