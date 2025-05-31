package com.fitmeal.app

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.*
import com.fitmeal.app.database.AppDatabase
import com.fitmeal.app.database.entites.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealPlanFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealPlanAdapter
    private lateinit var textTotalCalories: TextView
    private val selectedProducts = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_plan, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewMealPlan)
        textTotalCalories = view.findViewById(R.id.textTotalCalories)

        adapter = MealPlanAdapter { product ->
            selectedProducts.add(product)
            updateTotalCalories()
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadProducts()

        return view
    }

    private fun loadProducts() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val products = db.productDao().getAll()
            launch(Dispatchers.Main) {
                adapter.submitList(products)
            }
        }
    }

    private fun updateTotalCalories() {
        val total = selectedProducts.sumOf { it.calories }
        textTotalCalories.text = "Łącznie kcal: $total"
    }
}
