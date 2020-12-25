package com.baitap.doan.loadRecyclerView;

public class Baiviet {

    int id;
    String title;
    String mota;
    String content;
    String image;
    String luotlike;

    public Baiviet(int id, String title, String mota, String content, String image, String luotlike) {
        this.id = id;
        this.title = title;
        this.mota = mota;
        this.content = content;
        this.image = image;
        this.luotlike = luotlike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLuotlike() {
        return luotlike;
    }

    public void setLuotlike(String luotlike) {
        this.luotlike = luotlike;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
