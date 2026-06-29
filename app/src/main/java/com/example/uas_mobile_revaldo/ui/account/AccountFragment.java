package com.example.uas_mobile_revaldo.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.uas_mobile_revaldo.LocaleHelper;
import com.example.uas_mobile_revaldo.R;
import com.example.uas_mobile_revaldo.databinding.FragmentAccountBinding;
import com.example.uas_mobile_revaldo.ui.splash.SplashActivity;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private SharedPreferences prefs;
    private static final String PREF_NAME = "settings";
    private static final String KEY_DARK_MODE = "dark_mode";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Load foto profil
        Glide.with(this)
                .load(R.drawable.foto_profil)
                .centerCrop()
                .into(binding.ivFotoProfil);

        // Set posisi switch sesuai preferensi tersimpan
        boolean isDarkMode = prefs.getBoolean(KEY_DARK_MODE, false);
        binding.switchDarkMode.setChecked(isDarkMode);

        // Bahasa
        boolean isEnglish = LocaleHelper.isEnglish(requireContext());
        Log.d("LANGUAGE_DEBUG", "isEnglish = " + isEnglish);
        Log.d("LANGUAGE_DEBUG", "locale saat ini = " +
                getResources().getConfiguration().getLocales().get(0).toString());
        binding.switchLanguage.setChecked(isEnglish);
        binding.tvLangLabel.setText(isEnglish ? "EN" : "ID");

        binding.switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Simpan dulu, baru restart
            LocaleHelper.saveLanguage(requireContext(), isChecked);
            binding.tvLangLabel.setText(isChecked ? "EN" : "ID");

            // Delay sedikit agar SharedPreferences sempat tersimpan
            binding.switchLanguage.postDelayed(() -> {
                Intent intent = new Intent(requireActivity(), SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                requireActivity().startActivity(intent);
                requireActivity().finish();
            }, 300); // tunggu 300ms
        });

        // Listener toggle dark/light mode
        binding.switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Simpan preferensi
            prefs.edit().putBoolean(KEY_DARK_MODE, isChecked).apply();

            // Terapkan tema
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });


    }

}