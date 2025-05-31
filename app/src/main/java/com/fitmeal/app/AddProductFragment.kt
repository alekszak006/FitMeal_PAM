package com.fitmeal.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fitmeal.app.R
import com.fitmeal.app.database.AppDatabase
import com.fitmeal.app.database.entites.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.fitmeal.app.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val calories = binding.etCalories.text.toString().toIntOrNull() ?: 0
            val protein = binding.etProtein.text.toString().toDoubleOrNull() ?: 0.0
            val carbs = binding.etCarbs.text.toString().toDoubleOrNull() ?: 0.0
            val fat = binding.etFat.text.toString().toDoubleOrNull() ?: 0.0

            val product = Product(
                name = name,
                calories = calories,
                protein = protein,
                carbs = carbs,
                fat = fat
            )

            lifecycleScope.launch(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).productDao().insert(product)
                launch(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Dodano produkt!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

