package com.example.scroutercompiler;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ParamsData implements Serializable{

    private String originName;
    private String aliasName;
    private int type;
    private boolean isRequired;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequire(boolean require) {
        isRequired = require;
    }
}
