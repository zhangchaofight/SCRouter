package com.example.administrator.zcrouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scanno.annos.Autowired;
import com.example.scanno.annos.Router;

@Router(path = "/base/main2")
public class Main2Activity extends AppCompatActivity {

    @Autowired(alias = "name")
    private String name;

    @Autowired(required = true)
    private int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
