package com.rodion.filmproject.view.FilmsScreen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodion.filmproject.databinding.FilmsFragmentBinding;
import com.rodion.filmproject.view.FilmsScreen.adapter.BigItemAdapter;
import com.rodion.filmproject.view.FilmsScreen.viewmodel.FilmsViewModel;

public class FilmsFragment extends Fragment {

    FilmsViewModel viewModel;
    FilmsFragmentBinding binding;
    BigItemAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FilmsFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(FilmsViewModel.class);

        viewModel.getFilms();

        adapter = new BigItemAdapter(getContext(), viewModel.films);
        RecyclerView r = binding.recyclerView;
        r.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        r.setAdapter(adapter);

        viewModel.films.observe(getViewLifecycleOwner(), (res) -> {
            adapter.notifyDataSetChanged();
//            Observable.interval(2, TimeUnit.SECONDS)
//                    .take(5)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new DisposableObserver<Long>() {
//                        @Override
//                        public void onNext(@NonNull Long aLong) {
//                            int a = Math.toIntExact(aLong);
//                            r.smoothScrollToPosition(a);
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });

        });

        return binding.getRoot();
    }
}