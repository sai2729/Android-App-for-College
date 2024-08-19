package com.example.myksvit;

public class Eventzone {
    // define four String variables
    private String title, desc,username,url;
    // generate their respective constructors
    public Eventzone(String title, String desc, String username,String url) {
        this.title = title;
        this.desc = desc;
        this.username = username;
        this.url=url;
    }



    // create an empty constructor
    public Eventzone() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
