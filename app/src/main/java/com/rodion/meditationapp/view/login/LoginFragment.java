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
import com.rodion.meditationapp.databinding.FragmentLoginBinding;
import com.rodion.meditationapp.view.dashboard.DashboardActivity;
import com.rodion.meditationapp.view.login.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        viewModel.userInfo.observe(getViewLifecycleOwner(), (res) -> {
            showLoading(false);
            if(res != null) {
                Toast.makeText(getContext(), "Got user:"+res.nickName, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.printQuotes();
        //Show any errors
        viewModel.errorInfo.observe(getViewLifecycleOwner(), (res)-> {
            showLoading(false);
            Toast.makeText(getContext(), res, Toast.LENGTH_LONG).show();
        });

        binding = FragmentLoginBinding.inflate(inflater, container, false);


        binding.SignInBtn.setOnClickListener((v) -> {
            String email  = binding.editTextEmail.getText().toString();
            String pass = binding.editTextPassword.getText().toString();
            showLoading(true);
            viewModel.loginUser(email, pass);
        });

        binding.RegisterText.setOnClickListener(RegisterFrament::open);


        binding.ProfileBtn.setOnClickListener((v) -> {
            DashboardActivity.open(requireContext());
        });

        return binding.getRoot();
    }

    void showLoading(boolean isLoad) {
        if(isLoad) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.editTextEmail.setEnabled(false);
            binding.editTextPassword.setEnabled(false);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.editTextEmail.setEnabled(true);
            binding.editTextPassword.setEnabled(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static void open(View v) {
        Navigation.findNavController(v).navigate(R.id.action_onboardFragment_to_loginFragment);
    }
}
