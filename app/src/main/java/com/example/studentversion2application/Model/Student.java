package com.example.studentversion2application.Model;

import android.net.Uri;

public class Student {

    Uri uriImage;
    String studlname, studfname, studcourse;

    //constructor
    public Student(Uri uriImage, String studlname, String studfname, String studcourse) {
        super();
        this.uriImage = uriImage;
        this.studlname = studlname;
        this.studfname = studfname;
        this.studcourse = studcourse;
    }

    //getters and setters
    public Uri getUriImage() {
        return uriImage;
    }

    public void setUriImage(Uri uriImage) {
        this.uriImage = uriImage;
    }

    public String getStudlname() {
        return studlname;
    }

    public void setStudlname(String studlname) {
        this.studlname = studlname;
    }

    public String getStudfname() {
        return studfname;
    }

    public void setStudfname(String studfname) {
        this.studfname = studfname;
    }

    public String getStudcourse() {
        return studcourse;
    }

    public void setStudcourse(String studcourse) {
        this.studcourse = studcourse;
    }
}
