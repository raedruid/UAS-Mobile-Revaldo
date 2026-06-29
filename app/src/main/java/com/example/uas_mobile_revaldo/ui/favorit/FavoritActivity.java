package com.example.uas_mobile_revaldo.ui.favorit;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.uas_mobile_revaldo.BaseActivity;
import com.example.uas_mobile_revaldo.adapter.FavoritAdapter;
import com.example.uas_mobile_revaldo.databinding.ActivityFavoritBinding;
import com.example.uas_mobile_revaldo.viewmodel.HomeViewModel;

public class FavoritActivity extends BaseActivity {

    private ActivityFavoritBinding binding;
    private HomeViewModel viewModel;
    private FavoritAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new FavoritAdapter();

        binding.rvFavorit.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvFavorit.setAdapter(adapter);

        viewModel.getAllFavorit().observe(this, list -> {
            adapter.submitList(list);
        });
    }
}
