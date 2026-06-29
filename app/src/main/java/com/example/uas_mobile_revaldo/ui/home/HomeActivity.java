package com.example.uas_mobile_revaldo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.example.uas_mobile_revaldo.BaseActivity;
import com.example.uas_mobile_revaldo.R;
import com.example.uas_mobile_revaldo.databinding.ActivityHomeBinding;
import com.example.uas_mobile_revaldo.ui.account.AccountFragment;
import com.example.uas_mobile_revaldo.ui.favorit.FavoritActivity;
import com.example.uas_mobile_revaldo.ui.search.SearchActivity;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load fragment default: Hewan
        if (savedInstanceState == null) {
            loadFragment(new HewanFragment());
        }

        // Bottom navigation listener
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_hewan) {
                loadFragment(new HewanFragment());
                return true;
            } else if (id == R.id.nav_tumbuhan) {
                loadFragment(new TumbuhanFragment());
                return true;
            } else if (id == R.id.nav_account) {      // ← tambahkan blok ini
                loadFragment(new AccountFragment());
                return true;
            }
            return false;
        });

        // Tombol search
        binding.ivSearch.setOnClickListener(v ->
                startActivity(new Intent(this, SearchActivity.class)));

        // Tombol favorit
        binding.ivFavorit.setOnClickListener(v ->
                startActivity(new Intent(this, FavoritActivity.class)));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
