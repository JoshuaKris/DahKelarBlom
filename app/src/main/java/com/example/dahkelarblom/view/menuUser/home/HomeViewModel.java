package com.example.dahkelarblom.view.menuUser.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.Merchant;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<Merchant>> merchantList = new MutableLiveData<>();
    private MutableLiveData<List<String>> locationList = new MutableLiveData<>();

    public HomeViewModel() {
        //still looking
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Merchant>> getMerchantList() {
        return merchantList;
    }

    public LiveData<List<String>> getLocationList() {
        return locationList;
    }

    public void fetchText(String text) {
        mText.setValue(text);
    }

    public void fetchMerchantData() {
        List<Merchant> temp = new ArrayList<>();
        Merchant merchant;
         merchant = new Merchant(
                "Zenta Admin",
                "(021) 53660671",
                 "Zenta Print",
                 "Zenta123",
                 "zenta.print@gmail.com",
                "Jl. Anggrek Cakra No.16 RT.2/RW.9 (Binus Anggrek B Floor)",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Galaxy Admin",
                "(021) 37837837",
                "Galaxy Print",
                "Galaxy123",
                "galaxy.print@gmail.com",
                "Sebrang Gedung Sunib Angg**k",
                0);temp.add(merchant);
        merchant = new Merchant(
                "Makmur Admin",
                "(021) 12345678",
                "Makmur Print",
                "Makmur123",
                "makmur.print@gmail.com",
                "Jl. Sesama 123, Jakarta Selatan",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Feel Admin",
                "(021) 1001312",
                "MyFeelPrint",
                "Myfeel123",
                "makmur.print@gmail.com",
                "Jl. yang mana ya, Jakarta Selatan",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Snipi Admin",
                "(021) 44123414",
                "Snipi",
                "Snipi123",
                "snipi.print@gmail.com",
                "Jl. yang situ itu loh, Jakarta Selatan",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Herang Admin",
                "(021) 1243451",
                "Herang",
                "Herang123",
                "herang.print@gmail.com",
                "Jl. Palmerah Utara, Jakarta Barat",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Sampoerna Admin",
                "(021) 651422343",
                "Sampoerna",
                "Sampoerna123",
                "sampoerna.print@gmail.com",
                "Jl. Palmerah Utara 2, Jakarta Barat",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Putra Mandiri Admin",
                "081311585496",
                "Putra Mandiri",
                "Putra123",
                "putra.print@gmail.com",
                "Puri Harapan Blok B2/8, Bekasi",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Iwan Foto Admin",
                "085864966301",
                "Iwan Foto",
                "Iwan123",
                "iwan.print@gmail.com",
                "Pondok Ungu Permai Blok C 20/6, Bekasi",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Andalas Admin",
                "085215776631",
                "Andalas",
                "Andalas123",
                "andalas.print@gmail.com",
                "Taman Harapan Baru Blok O 1/1, Bekasi",
                0);
        temp.add(merchant);

        merchantList.setValue(temp);
    }

    public void fetchLocationData() {
        List<String> temp = new ArrayList<>();
        temp.add("Jakarta Barat");
        temp.add("Jakarta Selatan");
        temp.add("Bekasi");
        locationList.setValue(temp);
    }
}