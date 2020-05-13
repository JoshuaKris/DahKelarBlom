package com.example.dahkelarblom.utils;

import android.view.View;
import android.webkit.WebView;

public class Loading {
    private final WebView progress;

    public Loading(WebView progressBar) {
        this.progress = progressBar;

    }

    public void start() {
        progress.setVisibility(View.VISIBLE);
        progress.loadUrl("file:///android_asset/loading.html");
    }

    public void close() {
        progress.setVisibility(View.INVISIBLE);
    }
}
