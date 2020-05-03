package com.example.akhbariapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.akhbariapp.AppDatabase;
import com.example.akhbariapp.Dao.NationalCardsDao;
import com.example.akhbariapp.Entity.NationalCardsEntity;

import java.util.concurrent.ExecutionException;

public class NationalCardsRepository {

    private NationalCardsDao nationalCardsDao;
    public NationalCardsRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getinstance(application);
        nationalCardsDao = appDatabase.nationalCardsDao();
    }

    public NationalCardsEntity check_nat(String f,String l,String r,String nat_id) throws ExecutionException, InterruptedException {
        return new CheckNatId(nationalCardsDao).execute(f,l,r,nat_id).get();
    }
    static class CheckNatId extends AsyncTask<String,Void, NationalCardsEntity>{

        private NationalCardsDao nationalCardsDao;

        public CheckNatId(NationalCardsDao nationalCardsDao) {
            this.nationalCardsDao = nationalCardsDao;
        }

        @Override
        protected NationalCardsEntity doInBackground(String... strings) {
            return nationalCardsDao.check(strings[0],strings[1],strings[2],strings[3]);
        }
    }
}
