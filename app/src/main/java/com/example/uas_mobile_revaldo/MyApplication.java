package com.example.uas_mobile_revaldo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Paksa locale kita saat sistem coba override
        SharedPreferences prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean isEnglish = prefs.getBoolean("language_en", false);
        String langCode = isEnglish ? "en" : "in";

        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            newConfig.setLocales(localeList);
        } else {
            newConfig.locale = locale;
        }
        getResources().updateConfiguration(newConfig, getResources().getDisplayMetrics());
    }
}
