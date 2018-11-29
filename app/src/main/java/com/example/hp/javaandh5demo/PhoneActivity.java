package com.example.hp.javaandh5demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hp on 2018/11/28.
 */

public class PhoneActivity extends AppCompatActivity {
    private WebView webView;
    private String json;
    private static final String TAG = "PhoneActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_activity);

        webView = findViewById(R.id.webView1);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new JavaAndAndroidInterface(), "Android");
        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
    }

    class JavaAndAndroidInterface {
        @JavascriptInterface
        public void call(String phone) {

            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(PhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
        @JavascriptInterface
        public void showcontacts() {
            json = "[{\"name\":\"阿福\", \"phone\":\"18600012345\"}]";
            //在主线程中  更新UI

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:show( '"+json+"')");
                }
            });
            // 调用JS中的方法

           // Log.e(TAG, "showcontacts: " + json);

       }





}
}
