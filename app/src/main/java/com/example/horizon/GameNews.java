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
import android.widget.ImageView;

public class GameNews extends AppCompatActivity {

    private WebView webView;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game_news);

        back = findViewById(R.id.back);
        webView = (WebView) findViewById(R.id.newss);
        webView.setWebViewClient(new WebViewClient());
        Intent info = getIntent();
        String game = info.getStringExtra("title");
        String str = info.getStringExtra("key");

        webView.loadUrl("https://n4g.com");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameNews.this, Homepage.class);
                i.putExtra("key", str);
                startActivity(i);
            }
        });
    }
}