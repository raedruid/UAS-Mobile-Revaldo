package com.example.uas_mobile_revaldo.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.uas_mobile_revaldo.data.model.Favorit;
import java.util.List;

@Dao
public interface FavoritDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favorit favorit);

    @Query("DELETE FROM favorit WHERE endemikId = :endemikId")
    void deleteByEndemikId(String endemikId);

    @Query("SELECT * FROM favorit")
    LiveData<List<Favorit>> getAll();

    // Cek apakah item sudah difavoritkan
    @Query("SELECT COUNT(*) FROM favorit WHERE endemikId = :endemikId")
    int isFavorit(String endemikId);
}
