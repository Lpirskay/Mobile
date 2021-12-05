package com.rodion.filmproject.view.FilmsScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rodion.filmproject.R;
import com.rodion.filmproject.databinding.FragmentMainScreenBinding;

public class MainScreenFragment extends Fragment {

    private FragmentMainScreenBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false);

        BottomNavigationView navView = binding.navView;

        NavHostFragment navHostFragment = (NavHostFragment)getChildFragmentManager().findFragmentById(R.id.main_nav_host);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        return binding.getRoot();
    }

    public static void open(View v) {
        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_mainScreenFragment);
    }
}
