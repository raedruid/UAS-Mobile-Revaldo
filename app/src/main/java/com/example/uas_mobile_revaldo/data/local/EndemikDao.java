package com.example.uas_mobile_revaldo.data.local;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.uas_mobile_revaldo.data.model.Endemik;
import java.util.List;

@Dao
public interface EndemikDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Endemik> endemikList);

    // Ambil data berdasarkan tipe (Hewan / Tumbuhan)
    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    LiveData<List<Endemik>> getByTipe(String tipe);

    // Cari berdasarkan nama atau nama latin
    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :query || '%' " +
            "OR nama_latin LIKE '%' || :query || '%'")
    LiveData<List<Endemik>> search(String query);

    // Ambil satu data berdasarkan id
    @Query("SELECT * FROM endemik WHERE id = :id LIMIT 1")
    LiveData<Endemik> getById(String id);

    // Cek apakah data sudah ada (untuk skip fetch)
    @Query("SELECT COUNT(*) FROM endemik")
    int count();
}
