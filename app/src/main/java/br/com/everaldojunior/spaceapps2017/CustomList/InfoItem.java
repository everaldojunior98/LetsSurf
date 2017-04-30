package br.com.everaldojunior.spaceapps2017.CustomList;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class InfoItem {
    private Drawable icon;
    private String name;
    private String info;

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getinfo() {
        return info;
    }

    public void setinfo(String info) {
        this.info = info;
    }

    public Drawable geticon() {
        return icon;
    }

    public void seticon(Drawable icon) {
        this.icon = icon;
    }

}