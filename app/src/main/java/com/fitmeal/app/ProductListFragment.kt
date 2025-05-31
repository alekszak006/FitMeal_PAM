package com.fitmeal.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitmeal.app.database.AppDatabase
import com.fitmeal.app.database.entites.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter

        loadProductsFromDatabase()
    }

    private fun loadProductsFromDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val products = db.productDao().getAll()

            lifecycleScope.launch(Dispatchers.Main) {
                productList.clear()
                productList.addAll(products)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
