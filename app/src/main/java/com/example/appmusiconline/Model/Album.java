package com.example.appmusiconline.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Album {

        @SerializedName("AlbumID")
        @Expose
        private String albumID;
        @SerializedName("AlbumTitle")
        @Expose
        private String albumTitle;
        @SerializedName("ArtistAlbum")
        @Expose
        private String artistAlbum;
        @SerializedName("AlbumHinh")
        @Expose
        private String albumHinh;

        public String getAlbumID() {
            return albumID;
        }

        public void setAlbumID(String albumID) {
            this.albumID = albumID;
        }

        public String getAlbumTitle() {
            return albumTitle;
        }

        public void setAlbumTitle(String albumTitle) {
            this.albumTitle = albumTitle;
        }

        public String getArtistAlbum() {
            return artistAlbum;
        }

        public void setArtistAlbum(String artistAlbum) {
            this.artistAlbum = artistAlbum;
        }

        public String getAlbumHinh() {
            return albumHinh;
        }

        public void setAlbumHinh(String albumHinh) {
            this.albumHinh = albumHinh;
        }

    }

