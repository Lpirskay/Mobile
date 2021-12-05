package com.rodion.meditationapp.view.profile;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.rodion.meditationapp.R;
import com.rodion.meditationapp.databinding.FragmentProfileBinding;
import com.rodion.meditationapp.view.profile.viewmodel.ProfileViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetMultipleContents(),
                (imageUris) -> {
                    profileViewModel.saveImages(imageUris);
                });

        binding.galleryBtn.setOnClickListener((v) -> {
            launcher.launch("image/*");
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
