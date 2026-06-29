package com.example.uas_mobile_revaldo.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import com.example.uas_mobile_revaldo.BaseActivity;
import com.example.uas_mobile_revaldo.R;
import com.example.uas_mobile_revaldo.databinding.ActivitySplashBinding;
import com.example.uas_mobile_revaldo.ui.home.HomeActivity;
import com.example.uas_mobile_revaldo.viewmodel.HomeViewModel;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        // Terapkan dark/light mode dari preferensi tersimpan
        SharedPreferences prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Animasi fade in untuk logo
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(1000);
        binding.ivSplashLogo.startAnimation(fadeIn);

        // Fetch data dari API, simpan ke ROOM
        viewModel.fetchData(() -> runOnUiThread(() -> {
            binding.progressBar.setVisibility(android.view.View.GONE);
            binding.btnLanjut.setVisibility(android.view.View.VISIBLE);
            binding.btnLanjut.setEnabled(true);
            binding.btnLanjut.setText(getString(R.string.mulai));;
        }));

        binding.btnLanjut.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
}