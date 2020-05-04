package com.example.akhbariapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.akhbariapp.Entity.NationalCardsEntity;
import com.example.akhbariapp.Repository.NationalCardsRepository;

import java.util.concurrent.ExecutionException;

public class NationalCardsViewModel extends AndroidViewModel {

    private NationalCardsRepository nationalCardsRepository;
    public NationalCardsViewModel(@NonNull Application application) {
        super(application);
        nationalCardsRepository = new NationalCardsRepository(application);
    }

    public NationalCardsEntity check_nat (String f,String l,String r,String nat) throws ExecutionException, InterruptedException {
        return nationalCardsRepository.check_nat(f,l,r,nat);
    }
}
