package com.rodion.meditationapp.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.rodion.meditationapp.R;
import com.rodion.meditationapp.databinding.StartActivityBinding;
import com.rodion.meditationapp.view.dashboard.DashboardActivity;
import com.rodion.meditationapp.view.login.viewmodel.LoginViewModel;

public class StartActivity extends AppCompatActivity {
    private NavController navController;
    private StartActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MeditationApp);

        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.getUser();
        viewModel.userInfo.observe(this, (user) -> {
            if(user != null) {
                DashboardActivity.open(this);
            }
        });

        binding = StartActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
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