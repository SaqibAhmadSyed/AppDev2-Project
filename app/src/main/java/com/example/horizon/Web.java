package com.example.horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Web extends AppCompatActivity {
    private WebView webView;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_web);

    webView = (WebView) findViewById(R.id.web);
    webView.setWebViewClient(new WebViewClient());
        Intent info = getIntent();
        String game = info.getStringExtra("title");


    webView.loadUrl("https://www.metacritic.com/search/all/"+game+"/results");

    }
}