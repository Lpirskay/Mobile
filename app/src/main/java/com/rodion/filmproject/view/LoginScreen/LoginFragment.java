package com.rodion.filmproject.view.LoginScreen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rodion.filmproject.databinding.LoginFragmentBinding;
import com.rodion.filmproject.view.FilmsScreen.MainScreenFragment;
import com.rodion.filmproject.view.LoginScreen.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;
    private LoginFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = LoginFragmentBinding.inflate(inflater, container, false);

        viewModel.getUser();

        binding.signinBtn.setOnClickListener((v) -> {
            String email = binding.emailText.getText().toString();
            String pass = binding.passText.getText().toString();
            viewModel.login(email, pass);
        });

        viewModel.userInfo.observe(getViewLifecycleOwner(), (user) -> {
            binding.emailText.setText(user.email);
        });

        viewModel.userRegistered.observe(getViewLifecycleOwner(), (res) -> {
            if(res) {
                MainScreenFragment.open(binding.getRoot());
            }
        });

        viewModel.errorInfo.observe(getViewLifecycleOwner(), (res) -> {
            Toast.makeText(getContext(), res, Toast.LENGTH_LONG).show();
        });

        return binding.getRoot();
    }

}