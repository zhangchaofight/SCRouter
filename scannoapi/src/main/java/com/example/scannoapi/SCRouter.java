package com.example.scannoapi;

import android.content.Context;

public class SCRouter {

    private static volatile SCRouter instance;

    private SCRouter() {

    }

    public SCRouter getInstance() {
        return instance;
    }

    public SCRouter build(String url) {
        return instance;
    }

    public void navigation() {

    }

    public void navigation(Context context) {

    }
}