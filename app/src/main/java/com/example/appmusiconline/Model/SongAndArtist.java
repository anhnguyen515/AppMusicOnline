package com.example.appmusiconline.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongAndArtist {

    @SerializedName("SongID")
    @Expose
    private String songID;
    @SerializedName("SongTitle")
    @Expose
    private String songTitle;
    @SerializedName("ArtistID")
    @Expose
    private String artistID;
    @SerializedName("AlbumID")
    @Expose
    private String albumID;
    @SerializedName("SongLink")
    @Expose
    private String songLink;
    @SerializedName("SongHinh")
    @Expose
    private String songHinh;
    @SerializedName("LuotThich")
    @Expose
    private String luotThich;
    @SerializedName("ArtistName")
    @Expose
    private String artistName;

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getSongHinh() {
        return songHinh;
    }

    public void setSongHinh(String songHinh) {
        this.songHinh = songHinh;
    }

    public String getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(String luotThich) {
        this.luotThich = luotThich;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}