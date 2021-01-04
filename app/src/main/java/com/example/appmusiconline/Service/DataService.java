package com.example.appmusiconline.Service;

import com.example.appmusiconline.Model.Album;
import com.example.appmusiconline.Model.AlbumAndArtist;
import com.example.appmusiconline.Model.PersonalAlbum;
import com.example.appmusiconline.Model.PersonalPlaylist;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.Model.SongAndArtistSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET ("album_hottrend.php")
    Call<List<AlbumAndArtist>> getDataAlbum();
    @GET("song_hottrend.php")
    Call<List<SongAndArtist>> getSong();
    @FormUrlEncoded
    @POST("getsongXX.php")
    Call<List<SongAndArtist>> getSongXX (@Field("tukhoa") String tukhoa) ;
    @GET("personal_song.php")
    Call<List<PersonalSong>> getPersonalSong();

    @GET("personal_album.php")
    Call<List<PersonalAlbum>> getPersonalAlbum();

    @GET ("personal_playlist.php")
    Call<List<PersonalPlaylist>> getPersonalPlaylist();

    @GET("explorer_playlist.php")
    Call<List<PersonalPlaylist>> getExplorerPlaylist();


    @GET("explorer_song.php")
    Call<List<PersonalSong>> getExplorerSong();

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<SongAndArtistSearch>> getSearchBaiHat(@Field("tukhoa") String tukhoa) ;


}
