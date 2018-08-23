package com.example.administrator.zcrouter;

import android.util.Log;

import com.example.scanno.annos.Router;

import static com.example.administrator.zcrouter.Constants.TAG;

public class LogClass {

    @Router(path = "/base/log")
    private void log() {
        Log.d(TAG, "log: executed");
    }

    @Router(path = "/base/log")
    private void log(String msg) {
        Log.d(TAG, "log msg : " + msg);
    }
}
