package com.example.appmusiconline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data ;
@Data
public class PersonalSong implements Serializable {

    @SerializedName("ImageSong")
    @Expose
    private String imageSong;
    @SerializedName("NameSong")
    @Expose
    private String nameSong;
    @SerializedName("ArtistSong")
    @Expose
    private String artistSong;
    @SerializedName("TimeSong")
    @Expose
    private String timeSong;
    @SerializedName("linkSong")
    @Expose
    private String linkSong;

    public PersonalSong(String nameSong, String artistSong, String imageSong, String timeSong, String linkSong)
    {
        this.nameSong = nameSong;
        this.artistSong = artistSong;
        this.imageSong = imageSong;
        this.timeSong = timeSong;
        this.linkSong = linkSong;
    }
}
//public class PersonalSong implements Serializable {
//
//    @SerializedName("ImageSong")
//    @Expose
//    private String imageSong;
//    @SerializedName("NameSong")
//    @Expose
//    private String nameSong;
//    @SerializedName("ArtistSong")
//    @Expose
//    private String artistSong;
//    @SerializedName("TimeSong")
//    @Expose
//    private String timeSong;
//    @SerializedName("linkSong")
//    @Expose
//    private String linkSong;
//
//    public String getImageSong() {
//        return imageSong;
//    }
//
//    public void setImageSong(String imageSong) {
//        this.imageSong = imageSong;
//    }
//
//    public String getNameSong() {
//        return nameSong;
//    }
//
//    public void setNameSong(String nameSong) {
//        this.nameSong = nameSong;
//    }
//
//    public String getArtistSong() {
//        return artistSong;
//    }
//
//    public void setArtistSong(String artistSong) {
//        this.artistSong = artistSong;
//    }
//
//    public String getTimeSong() {
//        return timeSong;
//    }
//
//    public void setTimeSong(String timeSong) {
//        this.timeSong = timeSong;
//    }
//
//    public String getLinkSong() {
//        return linkSong;
//    }
//
//    public void setLinkSong(String linkSong) {
//        this.linkSong = linkSong;
//    }
//
//}