package com.example.appmusiconline.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusiconline.Activity.MainActivity;
import com.example.appmusiconline.Activity.MusicActivity;
import com.example.appmusiconline.DataBase.PlayListOperations;
import com.example.appmusiconline.Model.PersonalAlbum;
import com.example.appmusiconline.Model.PersonalPlaylist;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.provider.Settings.System.getString;

public class PersonalPlaylistAdapter extends BaseAdapter {
    PlayListOperations playListOperations;
    Context context;
    int layout ;
    ArrayList<PersonalSong> arr_personal_playlist ;

    public PersonalPlaylistAdapter(Context context, int layout, ArrayList<PersonalSong> arr_personal_playlist) {
        this.context = context;
        this.layout = layout;
        this.arr_personal_playlist = arr_personal_playlist;
    }

    @Override
    public int getCount() {
        return arr_personal_playlist.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_personal_playlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        ImageView imgSong ;
        TextView txtName , txtSumSong ,txtTime  ;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        holder = new ViewHolder() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // convertView sẽ chứa layout nào sẽ hiển thị mỗi dòng
        convertView = inflater.inflate(layout,null);

        ImageView imgSong  = (ImageView) convertView.findViewById(R.id.imgPersonalPlaylist) ;
        TextView txtName = (TextView) convertView.findViewById(R.id.txtPersonalPlaylistName) ;
        TextView txtSumSong = (TextView) convertView.findViewById(R.id.txtPersonalPlaylistSum) ;
        TextView txtTime = (TextView) convertView.findViewById(R.id.txtPersonalPlaylistTime);

        PersonalSong object = arr_personal_playlist.get(position);


        Picasso.with(context).load(object.getImageSong()).into(imgSong);
        txtName.setText(object.getNameSong());
        txtSumSong.setText(object.getArtistSong());
        txtTime.setText(object.getTimeSong());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mediaPlayer.release();
                MainActivity.initMediaPlayer();

                // man hinh play nhac
                Intent intent = new Intent(context, MusicActivity.class) ;
                Bundle bundle = new Bundle();
                //dong goi lan luot tung song
                for(int i=0; i<arr_personal_playlist.size();i++)
                {
                    PersonalSong song = arr_personal_playlist.get(i);
                    bundle.putSerializable("song"+i, song);
                }
                //dong goi size
                bundle.putInt("darkwa1",arr_personal_playlist.size());
                //dong goi potition
                bundle.putInt("darkwa2",position);

                //  bundle.put
                intent.putExtra("darkwa", bundle);
                context.startActivity(intent);
            }
        });

//        convertView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                showDialog(arr_personal_playlist.get(position).getLinkSong(), position);
//                return true;
//            }
//
//            private void showDialog(final String index, int position) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Delete")
//                        .setMessage("Are you sure you want to delete")
//                        .setCancelable(true)
//                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        })
//                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                playListOperations.removeSong(index);
//
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });


        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_personal_song);
        convertView.startAnimation(animation);

        return convertView;
    }
}
