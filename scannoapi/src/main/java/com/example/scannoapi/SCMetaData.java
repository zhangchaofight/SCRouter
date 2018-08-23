package com.example.scannoapi;

import java.util.List;

@SuppressWarnings("unused")
public class SCMetaData {
    private String path;
    private String className;
    private int type;
    private List<SCParams> params;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SCParams> getParams() {
        return params;
    }

    public void setParams(List<SCParams> params) {
        this.params = params;
    }
}
