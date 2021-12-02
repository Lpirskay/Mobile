package com.rodion.meditationapp.presentation.view.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rodion.meditationapp.R;
import com.rodion.meditationapp.databinding.StartActivityBinding;

public class StartActivity extends AppCompatActivity {
    private NavController navController;
    private StartActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StartActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);
        if(navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.popBackStack() || super.onSupportNavigateUp();
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, StartActivity.class));
    }
}