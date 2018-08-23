package com.example.scannoapi;

public interface SCRouterListener {

    void onFound();

    void onError();

    void onMissed();

    void onTimeOut();
}
