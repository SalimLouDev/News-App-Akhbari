package com.example.akhbariapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.akhbariapp.Dao.MessageDAO;
import com.example.akhbariapp.Dao.NationalCardsDao;
import com.example.akhbariapp.Dao.PostDao;
import com.example.akhbariapp.Dao.UserDao;
import com.example.akhbariapp.Entity.EntityUser;
import com.example.akhbariapp.Entity.MessageEntity;
import com.example.akhbariapp.Entity.NationalCardsEntity;
import com.example.akhbariapp.Entity.PostsEntity;

import java.util.concurrent.Executors;

@TypeConverters({DateConverter.class})
@Database(entities = {EntityUser.class, NationalCardsEntity.class, PostsEntity.class , MessageEntity.class},version = 6)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract UserDao userDao();
    public abstract PostDao postDao();
    public abstract MessageDAO messageDAO();
    public abstract NationalCardsDao nationalCardsDao();
    public static synchronized AppDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCalback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCalback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new fillMessagesDBAsyncTask(instance).execute();
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

    private static class fillMessagesDBAsyncTask extends AsyncTask<Void,Void,Void>{

        private MessageDAO messageDAO;
        private fillMessagesDBAsyncTask(AppDatabase appDatabase){
            messageDAO = appDatabase.messageDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            messageDAO.insert(new MessageEntity("Hello world 1","this example 1","2020-10-15","23:22"));
            messageDAO.insert(new MessageEntity("Hello world 2","this example 2","2020-10-16","20:22"));
            messageDAO.insert(new MessageEntity("Hello world 3","this example 3","2020-10-17","19:22"));
            messageDAO.insert(new MessageEntity("Hello world 4","this example 4","2020-10-18"," 5:22"));
            messageDAO.insert(new MessageEntity("Hello world 5","this example 5","2020-10-19"," 8:22"));
            messageDAO.insert(new MessageEntity("Hello world 6","this example 6","2020-10-20","10:22"));
            messageDAO.insert(new MessageEntity("Hello world 7","this example 7","2020-01-20","12:23"));
            return null;
        }
    }
}
