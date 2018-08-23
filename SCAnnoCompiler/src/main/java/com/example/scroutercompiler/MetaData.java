package com.example.scroutercompiler;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class MetaData implements Serializable{
    private String path;
    private String className;
    private int type;
    private List<ParamsData> params;

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

    public List getParams() {
        return params;
    }

    public void setParams(List<ParamsData> params) {
        this.params = params;
    }
}
