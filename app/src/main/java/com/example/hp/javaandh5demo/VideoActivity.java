package com.example.hp.javaandh5demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by guolonggang on 2018/11/28.
 *
 * H5页面调用Android 播放视频
 */

public class VideoActivity  extends AppCompatActivity{
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        webView=findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");
        webView.addJavascriptInterface(new JavaAndAndroidInterface(),"android");
    }
    class JavaAndAndroidInterface
    {
        @JavascriptInterface
        public void   playVideo(int id,String videoUrl,String title)
        {
            Intent intent=new Intent();
            intent.setDataAndType(Uri.parse(videoUrl),"video/*");
            startActivity(intent);
            Toast.makeText(VideoActivity.this,"videoUrl"+videoUrl,Toast.LENGTH_SHORT).show();
        }
    }
}
