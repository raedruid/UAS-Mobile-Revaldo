package com.example.uas_mobile_revaldo.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.uas_mobile_revaldo.data.model.Endemik;
import com.example.uas_mobile_revaldo.data.model.Favorit;
import com.example.uas_mobile_revaldo.data.repository.EndemikRepository;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final EndemikRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new EndemikRepository(application);
    }

    // Dipanggil dari SplashActivity untuk fetch & simpan data
    public void fetchData(Runnable onDone) {
        repository.fetchAndSave(onDone);
    }

    public LiveData<List<Endemik>> getHewan() {
        return repository.getByTipe("Hewan");
    }

    public LiveData<List<Endemik>> getTumbuhan() {
        return repository.getByTipe("Tumbuhan");
    }

    public LiveData<List<Endemik>> search(String query) {
        return repository.search(query);
    }

    public LiveData<Endemik> getById(String id) {
        return repository.getById(id);
    }

    public void addFavorit(Favorit favorit) {
        repository.addFavorit(favorit);
    }

    public void removeFavorit(String endemikId) {
        repository.removeFavorit(endemikId);
    }

    public LiveData<List<Favorit>> getAllFavorit() {
        return repository.getAllFavorit();
    }

    public void checkIsFavorit(String endemikId, EndemikRepository.Consumer<Boolean> callback) {
        repository.checkIsFavorit(endemikId, callback);
    }
}