package com.example.appmusiconline.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalAlbum {

    @SerializedName("ImageAlbum")
    @Expose
    private String imageAlbum;
    @SerializedName("NameAlbum")
    @Expose
    private String nameAlbum;
    @SerializedName("ArtistAlbum")
    @Expose
    private String artistAlbum;
    @SerializedName("TimeAlbum")
    @Expose
    private String timeAlbum;

    public String getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(String imageAlbum) {
        this.imageAlbum = imageAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getArtistAlbum() {
        return artistAlbum;
    }

    public void setArtistAlbum(String artistAlbum) {
        this.artistAlbum = artistAlbum;
    }

    public String getTimeAlbum() {
        return timeAlbum;
    }

    public void setTimeAlbum(String timeAlbum) {
        this.timeAlbum = timeAlbum;
    }

}