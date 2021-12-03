package com.rodion.meditationapp.view.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.rodion.meditationapp.R;
import com.rodion.meditationapp.databinding.FragmentRegisterBinding;
import com.rodion.meditationapp.view.login.viewmodel.LoginViewModel;

public class RegisterFrament extends Fragment {
    FragmentRegisterBinding binding;
    LoginViewModel viewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        viewModel.userInfo.observe(getViewLifecycleOwner(), (response) -> {
            if(response != null) {
                Toast.makeText(getContext(),"User registered: "+response.email, Toast.LENGTH_LONG).show();
            }
        });

        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.RegisterBtn.setOnClickListener((v) -> {
            String email = binding.editTextEmail.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            viewModel.registerUser(email, password);
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static void open(View v) {
        Navigation.findNavController(v).navigate(R.id.toRegisterFragment);
    }
}
