package com.example.uas_mobile_revaldo.data.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.uas_mobile_revaldo.data.model.Endemik;
import com.example.uas_mobile_revaldo.data.model.Favorit;

@Database(
        entities = {Endemik.class, Favorit.class},
        version = 1,
        exportSchema = false
)
public abstract class EndemikDatabase extends RoomDatabase {

    private static volatile EndemikDatabase INSTANCE;

    public abstract EndemikDao endemikDao();
    public abstract FavoritDao favoritDao();

    public static EndemikDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EndemikDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EndemikDatabase.class,
                            "endemik_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
