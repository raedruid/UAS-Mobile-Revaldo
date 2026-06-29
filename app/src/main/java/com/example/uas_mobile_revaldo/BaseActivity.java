package com.example.uas_mobile_revaldo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfig) {
        // Cegah Android override locale yang sudah kita set
        SharedPreferences prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean isEnglish = prefs.getBoolean("language_en", false);
        String langCode = isEnglish ? "en" : "in";

        Locale locale = new Locale(langCode);
        overrideConfig.setLocale(locale);

        super.applyOverrideConfiguration(overrideConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Paksa apply locale setiap kali Activity resume
        SharedPreferences prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean isEnglish = prefs.getBoolean("language_en", false);
        String langCode = isEnglish ? "en" : "in";

        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration(getResources().getConfiguration());
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}