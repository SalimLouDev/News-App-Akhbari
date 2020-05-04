package com.example.akhbariapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.akhbariapp.Dao.NationalCardsDao;
import com.example.akhbariapp.Dao.PostDao;
import com.example.akhbariapp.Dao.UserDao;
import com.example.akhbariapp.Entity.EntityUser;
import com.example.akhbariapp.Entity.NationalCardsEntity;
import com.example.akhbariapp.Entity.PostsEntity;

import java.util.concurrent.Executors;

@Database(entities = {EntityUser.class, NationalCardsEntity.class, PostsEntity.class},version = 5)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract UserDao userDao();
    public abstract PostDao postDao();
    public abstract NationalCardsDao nationalCardsDao();
    public static synchronized AppDatabase getinstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context,AppDatabase.class,"Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCalback)
                    .build();
            return instance;
        }

        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCalback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadScheduledExecutor().execute(() -> {

                NationalCardsDao dao = instance.nationalCardsDao();
                NationalCardsEntity nationalCardsEntity = new NationalCardsEntity("OUCIF","MOHAMED","ORAN",1000);
                dao.insert(nationalCardsEntity);
                NationalCardsEntity nationalCardsEntity1 = new NationalCardsEntity("SALIM","LOUCIF","ORAN",2000);
                dao.insert(nationalCardsEntity1);
                NationalCardsEntity nationalCardsEntity3 = new NationalCardsEntity("TOUIL","HOUARI","ORAN",3000);
                dao.insert(nationalCardsEntity3);
                NationalCardsEntity nationalCardsEntity4 = new NationalCardsEntity("CHAOUCH","YACINE","ORAN",4000);
                dao.insert(nationalCardsEntity4);
                NationalCardsEntity nationalCardsEntity5 = new NationalCardsEntity("Benhamadouch","Walid","ORAN",6000);
                dao.insert(nationalCardsEntity5);

            });
        }

    };
}
