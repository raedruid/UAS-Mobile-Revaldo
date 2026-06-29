package com.example.uas_mobile_revaldo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

public class LocaleHelper {

    private static final String PREF_NAME = "settings";
    private static final String KEY_LANGUAGE = "language_en";

    public static Context onAttach(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean isEnglish = prefs.getBoolean(KEY_LANGUAGE, false);
        String langCode = isEnglish ? "en" : "in";
        android.util.Log.d("LOCALE_DEBUG", "onAttach → isEnglish=" + isEnglish + " lang=" + langCode);
        return wrap(context, langCode);
    }

    public static Context wrap(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }

        return context;
    }

    public static void saveLanguage(Context context, boolean isEnglish) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_LANGUAGE, isEnglish)
                .apply();
    }

    public static boolean isEnglish(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(KEY_LANGUAGE, false);
    }
}