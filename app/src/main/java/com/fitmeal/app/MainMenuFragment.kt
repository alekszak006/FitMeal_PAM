package com.fitmeal.app

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        val buttonAdd = view.findViewById<Button>(R.id.buttonAddProduct)
        val buttonList = view.findViewById<Button>(R.id.buttonProductList)
        val buttonPlanner = view.findViewById<Button>(R.id.buttonMealPlanner)

        buttonAdd.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddProductFragment())
                .addToBackStack(null)
                .commit()
        }

        buttonList.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProductListFragment())
                .addToBackStack(null)
                .commit()
        }

        buttonPlanner.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealPlanFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
