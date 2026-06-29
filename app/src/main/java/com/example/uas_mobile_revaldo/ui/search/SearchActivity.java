package com.example.uas_mobile_revaldo.ui.search;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.uas_mobile_revaldo.BaseActivity;
import com.example.uas_mobile_revaldo.adapter.EndemikAdapter;
import com.example.uas_mobile_revaldo.databinding.ActivitySearchBinding;
import com.example.uas_mobile_revaldo.ui.detail.DetailActivity;
import com.example.uas_mobile_revaldo.viewmodel.HomeViewModel;

public class SearchActivity extends BaseActivity {

    private ActivitySearchBinding binding;
    private HomeViewModel viewModel;
    private EndemikAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        adapter = new EndemikAdapter(item -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ENDEMIK_ID", item.getId());
            startActivity(intent);
        });

        binding.rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvSearch.setAdapter(adapter);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });

        // Langsung fokus ke search bar
        binding.searchView.requestFocus();
    }

    private void performSearch(String query) {
        viewModel.search(query).observe(this, list -> adapter.submitList(list));
    }
}
