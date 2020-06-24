package com.example.dahkelarblom.view.menuUser.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.Merchant;
import com.example.dahkelarblom.service.InternetService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<Merchant>> merchantList = new MutableLiveData<>();
    private MutableLiveData<List<String>> locationList = new MutableLiveData<>();

    private MutableLiveData<String> testMerchantList = new MutableLiveData<>();

    public HomeViewModel(Context context) {
        //still looking
        internetService = new InternetService(context);
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

    public LiveData<String> getTestMerchantList() {
        return testMerchantList;
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
                 "zenta.print@gmail.com",
                 "Zenta Print",
                 "Zenta123",
                "Jl. Anggrek Cakra No.16 RT.2/RW.9 (Binus Anggrek B Floor)",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Galaxy Admin",
                "(021) 37837837",
                "galaxy.print@gmail.com",
                "Galaxy Print",
                "Galaxy123",
                "Sebrang Gedung Sunib Angg**k",
                0);temp.add(merchant);
        merchant = new Merchant(
                "Makmur Admin",
                "(021) 12345678",
                "makmur.print@gmail.com",
                "Makmur Print",
                "Makmur123",
                "Jl. Sesama 123, Jakarta Selatan",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Feel Admin",
                "(021) 1001312",
                "makmur.print@gmail.com",
                "MyFeelPrint",
                "Myfeel123",
                "Jl. yang mana ya, Jakarta Selatan",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Snipi Admin",
                "(021) 44123414",
                "snipi.print@gmail.com",
                "Snipi",
                "Snipi123",
                "Jl. yang situ itu loh, Jakarta Selatan",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Herang Admin",
                "(021) 1243451",
                "herang.print@gmail.com",
                "Herang",
                "Herang123",
                "Jl. Palmerah Utara, Jakarta Barat",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Sampoerna Admin",
                "(021) 651422343",
                "sampoerna.print@gmail.com",
                "Sampoerna",
                "Sampoerna123",
                "Jl. Palmerah Utara 2, Jakarta Barat",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Putra Mandiri Admin",
                "081311585496",
                "putra.print@gmail.com",
                "Putra Mandiri",
                "Putra123",
                "Puri Harapan Blok B2/8, Bekasi",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Iwan Foto Admin",
                "085864966301",
                "iwan.print@gmail.com",
                "Iwan Foto",
                "Iwan123",
                "Pondok Ungu Permai Blok C 20/6, Bekasi",
                0);
        temp.add(merchant);
        merchant = new Merchant(
                "Andalas Admin",
                "085215776631",
                "andalas.print@gmail.com",
                "Andalas",
                "Andalas123",
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

    public void fetchMerchantList() {
        apiCall = InternetService.getServicesApi().getAllMerchant();
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String s = response.body();
                    testMerchantList.setValue(s);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}