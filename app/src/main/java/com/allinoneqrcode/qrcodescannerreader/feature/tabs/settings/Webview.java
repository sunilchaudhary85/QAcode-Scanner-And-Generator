package com.allinoneqrcode.qrcodescannerreader.feature.tabs.settings;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.allinoneqrcode.qrcodescannerreader.R;

public class Webview extends Activity{

    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);


        webView = (WebView) findViewById(R.id.webView);
        gotoPage();
      /*  Button button = (Button) findViewById (R.id.run);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage();
            }
        });*/

    }

    private void gotoPage(){

        //EditText text = (EditText) findViewById(R.id.url);
        String url = "https://www.freeprivacypolicy.com/live/2262533f-eee7-44ce-a0b6-ece398793955";

        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
        webView.loadUrl(url);

        System.out.println("I am here");
    }

    private class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }

}

