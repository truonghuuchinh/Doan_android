package com.baitap.doan.loadRecyclerView;

public class BinhLuan {
    String nguoiBinhLuan;
    String noiDungBinhLuan;

    public BinhLuan() {
        this.nguoiBinhLuan = "";
        this.noiDungBinhLuan = "";
    }
    public BinhLuan(String nguoiBinhLuan, String noiDungLuan) {
        this.nguoiBinhLuan = nguoiBinhLuan;
        this.noiDungBinhLuan = noiDungLuan;
    }

    public String getNguoiBinhLuan() {
        return nguoiBinhLuan;
    }

    public void setNguoiBinhLuan(String nguoiBinhLuan) {
        this.nguoiBinhLuan = nguoiBinhLuan;
    }

    public String getNoiDungBinhLuan() {
        return noiDungBinhLuan;
    }

    public void setNoiDungBinhLuan(String noiDungLuan) {
        this.noiDungBinhLuan = noiDungLuan;
    }
}
