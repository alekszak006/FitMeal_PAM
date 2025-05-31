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
    private lateinit var textTotalProtein: TextView
    private lateinit var textTotalFat: TextView
    private lateinit var textTotalCarbs: TextView

    private val selectedProducts = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_plan, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewMealPlan)
        textTotalCalories = view.findViewById(R.id.textTotalCalories)
        textTotalProtein = view.findViewById(R.id.textTotalProtein)
        textTotalFat = view.findViewById(R.id.textTotalFat)
        textTotalCarbs = view.findViewById(R.id.textTotalCarbs)

        adapter = MealPlanAdapter { product ->
            selectedProducts.add(product)
            updateSummary()
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

    private fun updateSummary() {
        val totalCalories = selectedProducts.sumOf { it.calories }
        val totalProtein = selectedProducts.sumOf { it.protein }
        val totalFat = selectedProducts.sumOf { it.fat }
        val totalCarbs = selectedProducts.sumOf { it.carbs }

        textTotalCalories.text = "Łącznie kcal: $totalCalories"
        textTotalProtein.text = "Białko: %.1fg".format(totalProtein)
        textTotalFat.text = "Tłuszcze: %.1fg".format(totalFat)
        textTotalCarbs.text = "Węglowodany: %.1fg".format(totalCarbs)
    }
}
