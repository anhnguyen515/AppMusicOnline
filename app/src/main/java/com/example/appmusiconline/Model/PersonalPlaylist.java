package com.example.appmusiconline.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalPlaylist {

    @SerializedName("ImagePlaylist")
    @Expose
    private String imagePlaylist;
    @SerializedName("NamePlaylist")
    @Expose
    private String namePlaylist;
    @SerializedName("SumPlayList")
    @Expose
    private String sumPlayList;
    @SerializedName("TimePlaylist")
    @Expose
    private String timePlaylist;

    public String getImagePlaylist() {
        return imagePlaylist;
    }

    public void setImagePlaylist(String imagePlaylist) {
        this.imagePlaylist = imagePlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public String getSumPlayList() {
        return sumPlayList;
    }

    public void setSumPlayList(String sumPlayList) {
        this.sumPlayList = sumPlayList;
    }

    public String getTimePlaylist() {
        return timePlaylist;
    }

    public void setTimePlaylist(String timePlaylist) {
        this.timePlaylist = timePlaylist;
    }

}
