package fragments

import adapter.OrderFoodsAdapter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import classes.OrderFood
import com.project.mealmonkey.R
import com.project.mealmonkey.databinding.FragmentMyOrdersBinding


class MyOrders : Fragment() {
    private lateinit var _binding: FragmentMyOrdersBinding
    private val binding get() = _binding
    lateinit var orderFoodsAdapter: OrderFoodsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyOrdersBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycleViewOrder()
        super.onViewCreated(view, savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initRecycleViewOrder() {
        val list = listOf<OrderFood>(
            OrderFood(
                R.drawable.image_srilankan, "Burger King", "Burger", 4.9, 123, "Kim Giang", "Ha Noi",
                30.0, 2
            ),
            OrderFood(
                R.drawable.image_italian, "Burger King", "Burger", 4.9, 123, "Kim Giang", "Ha Noi",
                30.0, 2
            ),
            OrderFood(
                R.drawable.image_indian, "Burger King", "Burger", 4.9, 123, "Kim Giang", "Ha Noi",
                30.0, 2
            ),
        )
        orderFoodsAdapter = context?.let { OrderFoodsAdapter(it) }!!
        orderFoodsAdapter.setListOrderFood(list)
        binding.recyclerViewOrders.focusable = View.NOT_FOCUSABLE
        binding.recyclerViewOrders.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewOrders.adapter = orderFoodsAdapter
    }
}