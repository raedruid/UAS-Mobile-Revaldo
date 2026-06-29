package com.example.uas_mobile_revaldo.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.uas_mobile_revaldo.data.local.EndemikDatabase;
import com.example.uas_mobile_revaldo.data.local.EndemikDao;
import com.example.uas_mobile_revaldo.data.local.FavoritDao;
import com.example.uas_mobile_revaldo.data.model.Endemik;
import com.example.uas_mobile_revaldo.data.model.Favorit;
import com.example.uas_mobile_revaldo.data.network.ApiConfig;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndemikRepository {

    private final EndemikDao endemikDao;
    private final FavoritDao favoritDao;
    // Thread pool untuk operasi database (tidak boleh di main thread)
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public EndemikRepository(Application application) {
        EndemikDatabase db = EndemikDatabase.getInstance(application);
        endemikDao = db.endemikDao();
        favoritDao = db.favoritDao();
    }

    /**
     * Ambil data dari API lalu simpan ke ROOM.
     * Jika data sudah ada di ROOM, langsung panggil onDone.
     */
    public void fetchAndSave(Runnable onDone) {
        executor.execute(() -> {
            if (endemikDao.count() > 0) {
                // Data sudah ada, tidak perlu fetch lagi
                if (onDone != null) onDone.run();
                return;
            }
            // Belum ada data → ambil dari API
            ApiConfig.getApiService().getEndemik().enqueue(new Callback<List<Endemik>>() {
                @Override
                public void onResponse(Call<List<Endemik>> call,
                                       Response<List<Endemik>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        executor.execute(() -> {
                            endemikDao.insertAll(response.body());
                            if (onDone != null) onDone.run();
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<Endemik>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        });
    }

    public LiveData<List<Endemik>> getByTipe(String tipe) {
        return endemikDao.getByTipe(tipe);
    }

    public LiveData<List<Endemik>> search(String query) {
        return endemikDao.search(query);
    }

    public LiveData<Endemik> getById(String id) {
        return endemikDao.getById(id);
    }

    public void addFavorit(Favorit favorit) {
        executor.execute(() -> favoritDao.insert(favorit));
    }

    public void removeFavorit(String endemikId) {
        executor.execute(() -> favoritDao.deleteByEndemikId(endemikId));
    }

    public LiveData<List<Favorit>> getAllFavorit() {
        return favoritDao.getAll();
    }

    public void checkIsFavorit(String endemikId, Consumer<Boolean> callback) {
        executor.execute(() -> {
            boolean result = favoritDao.isFavorit(endemikId) > 0;
            callback.accept(result);
        });
    }

    // Interface sederhana untuk callback boolean
    public interface Consumer<T> {
        void accept(T value);
    }
}
