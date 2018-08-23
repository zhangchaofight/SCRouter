package com.example.administrator.zcrouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scanno.annos.Router;

@Router(path = "/base/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
