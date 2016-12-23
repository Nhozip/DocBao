package com.it.nhozip.docbao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by Nhozip on 6/14/2016.
 */
public class GotoContent extends AppCompatActivity {
    private WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contten);

        browser = (WebView) findViewById(R.id.wV);
        browser.getSettings().setJavaScriptEnabled(false);

        Bundle bundle=getIntent().getBundleExtra("L");
        String link= bundle.getString("link");
//        String title=bundle.getString("title");
        setTitle(MainActivity.title);
        browser.loadUrl(link);

       Toolbar  mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




    }
}
