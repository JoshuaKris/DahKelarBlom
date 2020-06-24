package com.example.dahkelarblom;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BaseVMF<VM extends ViewModel> implements ViewModelProvider.Factory {

    private final VM viewModel;

    public BaseVMF(VM viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModel;
    }

}
