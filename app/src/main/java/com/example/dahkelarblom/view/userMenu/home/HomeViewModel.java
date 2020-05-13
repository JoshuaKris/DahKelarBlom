package com.example.dahkelarblom.view.userMenu.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.Merchant;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<Merchant>> merchantList = new MutableLiveData<>();

    public HomeViewModel() {
        //still looking
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Merchant>> getMerchantList() {
        return merchantList;
    }

    public void fetchText(String text) {
        mText.setValue(text);
    }

    public void fetchMerchantList() {
        List<Merchant> temp = new ArrayList<>();
        Merchant merchant;
        merchant = new Merchant("Zenta Print","Jl. Anggrek Cakra No.16 RT.2/RW.9 (Binus Anggrek B Floor)","(021) 53660671",0);
        temp.add(merchant);
        merchant = new Merchant("Galaxy Print","Sebrang Gedung Sunib Angg**k","(021) 37837837",0);
        temp.add(merchant);
        merchant = new Merchant("Makmur Print","Jl. Sesama 123","",0);
        temp.add(merchant);
        merchant = new Merchant("MyFeelPrint","Jl. yang mana ya","",0);
        temp.add(merchant);
        merchant = new Merchant("Snipi","Jl. yang situ itu loh","",0);
        temp.add(merchant);

        merchantList.setValue(temp);
    }
}