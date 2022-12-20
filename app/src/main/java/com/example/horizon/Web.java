package com.example.horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class Web extends AppCompatActivity {
    private WebView webView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_web);
        back = findViewById(R.id.back);
        webView = (WebView) findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        Intent info = getIntent();
        String str = info.getStringExtra("key");
        String game = info.getStringExtra("title");
        webView.loadUrl("https://www.metacritic.com/search/all/"+game+"/results");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Web.this, AvailableGames.class);
                i.putExtra("key", str);
                startActivity(i);
            }
        });
    }
}