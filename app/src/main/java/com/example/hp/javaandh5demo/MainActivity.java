package com.example.hp.javaandh5demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button   button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.btn_java_and_js);
        button2=findViewById(R.id.btn_js_call_java);
        button3=findViewById(R.id.btn_js_call_phone);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_java_and_js:
                Intent it=new Intent(MainActivity.this,OtherActivity.class);
                startActivity(it);

                break;
            case  R.id.btn_js_call_java:
                Intent intent=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_js_call_phone:
                Intent intents=new Intent(MainActivity.this,PhoneActivity.class);
                startActivity(intents);
                break;
        }
    }
}
