package com.example.hp.javaandh5demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hp on 2018/11/28.
 */

public class OtherActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText number,pass;
    private Button butLogin;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);
        number=findViewById(R.id.et_number);
        pass=findViewById(R.id.et_password);
        butLogin=findViewById(R.id.btn_login);
        butLogin.setOnClickListener(this);
        initWebView();

    }

    @Override
    public void onClick(View v) {

        login();



    }

    /**
     * 登陆的方法
     */
    private void login() {
        String newNumber = number.getText().toString();
        String pwd = pass.getText().toString();
        if(TextUtils.isEmpty(newNumber)||TextUtils.isEmpty(pwd))
        {
            Toast.makeText(this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"登录",Toast.LENGTH_SHORT).show();
            //initWebView();
            loginIn(newNumber);
        }

    }

    private void loginIn(String newNumber) {
        //java 调用js
        webView.loadUrl("javascript:javaCallJs("+"'"+newNumber+"'"+")");
        setContentView(webView);
    }

    private void initWebView() {

        webView=new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //加载内置浏览器
        webView.setWebViewClient(new WebViewClient());
        //加载网页
      //  webView.loadUrl("http://www.baidu.com");
        //js调用android(js调用java)
         webView.addJavascriptInterface(new JavaAndAndroidInterface(),"Android");


        //加载本地网页
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
       // setContentView(webView);
    }
    class JavaAndAndroidInterface
    {
        @JavascriptInterface
        public void showToast()
        {
            Toast.makeText(OtherActivity.this,"调用js了",Toast.LENGTH_SHORT).show();
        }
    }



}
