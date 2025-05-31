package com.fitmeal.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MainMenuFragment extends Fragment {

    public MainMenuFragment() {
        // Wymagany pusty konstruktor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Button buttonAdd = view.findViewById(R.id.button_add_product);
        Button buttonList = view.findViewById(R.id.button_product_list);

        buttonAdd.setOnClickListener(v -> {
            // Przejdź do AddProductFragment (może to być ten sam fragment, więc może nic nie robić albo odświeżyć)
            Fragment fragment = new AddProductFragment();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) // podmień na ID Twojego kontenera w activity
                    .addToBackStack(null)
                    .commit();
        });

        buttonList.setOnClickListener(v -> {
            Fragment fragment = new ProductListFragment();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) // podmień na ID Twojego kontenera w activity
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
}
