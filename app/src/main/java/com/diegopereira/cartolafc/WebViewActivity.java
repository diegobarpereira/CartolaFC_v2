package com.diegopereira.cartolafc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import okhttp3.Cookie;

public class WebViewActivity extends AppCompatActivity {

    public static final String SHARED_PREF_NAME = "SHARED";
    public static final String SHARED_TOKEN = "TOKEN";
    private static final String TAG = "TAG";
    WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_main);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button


        String url_ = "https://login.globo.com/login/438";

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //Required functionality here.
                //return true;
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.setWebViewClient(new WebViewClient());
        //webView.setWebViewClient(new myBrowser());



        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);

        webView.loadUrl(url_);

        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage)
            {
                String message = consoleMessage.message();
                if (message.startsWith("Login: Logado"))
                {
                    String token = message.substring(13); // removing 'captchatoken:' part from console message
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.setAcceptCookie(true);
                    CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
                    String cookies = cookieManager.getCookie(url_);
                    String CookieValue = null;
                    if(cookies != null){
                        String[] temp=cookies.split(";");
                        for (String ar1 : temp ){
                            if(ar1.contains("GLBID")){
                                String[] temp1=ar1.split("=");
                                CookieValue = temp1[1];

                                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear().apply();

                                editor.putString(SHARED_TOKEN, CookieValue);
                                editor.apply();
                                System.out.println(CookieValue);
                                Toast.makeText (WebViewActivity.this, token, Toast.LENGTH_SHORT).show();
                                System.out.println("Logado!!!");


                            }
                        }
                    }
                    //return CookieValue;


                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));


                }
                if (message.contains("Logado!!!")) {
                    webView.loadUrl("https://cartolafc.globo.com/#!/logout");
                    
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });
        //webView.loadUrl(url_);


        /*
        webView.addJavascriptInterface(new Object()
        {
            public void performClick()
            {
                System.out.println("Click");
                Toast.makeText (WebViewActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        }, "ng-scope");

         */


        /*
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                WebView.HitTestResult hr = ((WebView)view).getHitTestResult();

                Log.i(TAG, "getExtra = "+ hr.getExtra() + "\t\t Type=" + hr.getType());
                Toast.makeText (WebViewActivity.this, hr.getExtra(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

         */


        /*
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        String cookies = cookieManager.getCookie(url_);
        String CookieValue = null;
        if(cookies != null){
            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains("GLBID")){
                    String[] temp1=ar1.split("=");
                    CookieValue = temp1[1];



                }
            }
        }
        //return CookieValue;

        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();

        editor.putString(SHARED_TOKEN, CookieValue);
        editor.apply();
        System.out.println(CookieValue);
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));

        //webView.destroy();

         */
    }




    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                    //Required functionality here.
                    //return true;
                    return super.onJsAlert(view, url, message, result);
                }
            });
            webView.setWebViewClient(new WebViewClient());
            //webView.setWebViewClient(new myBrowser());



            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setDomStorageEnabled(true);
            webView.loadUrl("https://cartolafc.globo.com/#!/logout");
            return true;
        }

         
        Toast.makeText (WebViewActivity.this, item.toString(), Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);


    }

}
