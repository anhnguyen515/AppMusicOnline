package com.example.appmusiconline.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.example.appmusiconline.DataBase.PlayListOperations;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

public class MusicActivity extends AppCompatActivity {
    TextView txtTitle, txtArtist, txtElapsedTime, txtTotalTime;
    SeekBar progressBar, volumeBar;
    ImageView coverArt;
    ImageButton btnVolumeOn, btnTimer, btnLyrics;
    ImageButton btnPrevSong, btnNextSong, btnPrev10s, btnNext10s, btnPlay;
    ImageButton btnLike, btnShuffle, btnRepeat, btnShare;
    ImageButton btnClose;
    AudioManager audioManager;
    Handler handler = new Handler();
    ArrayList<PersonalSong> arrayList = new ArrayList<>();
    int sizeArray;
    int position;
    int flagRepeat = 1;
    Intent intent;
    Bundle bundle;
    PersonalSong arraySong;
    boolean repeatList = false, shuffle = false, checkFav = false;
    PlayListOperations playListOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mapping();
        progressBar.setMax(100);
        txtTitle.setSelected(true);
        txtArtist.setSelected(true);

        intent = getIntent();
        bundle = intent.getBundleExtra("darkwa");
        assert bundle != null;
        sizeArray = bundle.getInt("darkwa1");
        for (int i = 0; i < sizeArray; i++) {
            PersonalSong song = (PersonalSong) bundle.getSerializable("song" + i);
            arrayList.add(song);
        }

        position = bundle.getInt("darkwa2");

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        prepareMediaPlayer(position);

        //nhấn nút play
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mediaPlayer.isPlaying()) {
                    //đang phát -> chuyển sang hình play
                    MainActivity.mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                } else {
                    //đang ngừng -> chuyển sang hình pause
                    MainActivity.mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause_);
                    updateSeekBar();
                }
            }
        });

        //tua tới 10s
        btnNext10s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mediaPlayer.seekTo(MainActivity.mediaPlayer.getCurrentPosition() + 10000); //cộng thêm 10000ms (10s)
                updateSeekBar();
            }
        });

        //tua lui 10s
        btnPrev10s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mediaPlayer.seekTo(MainActivity.mediaPlayer.getCurrentPosition() - 10000); //cộng thêm 10000ms (10s)
                updateSeekBar();
            }
        });
        //nhấn nút close
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicActivity.this, TrangchuActivity.class);
                startActivity(intent);
            }
        });


        //nhấn nút volume
        btnVolumeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (volumeBar.getProgress() != 0) {
                    MusicActivity.this.mute();
                } else {    //thanh âm lượng đang ở mức 0 thì unmute
                    MusicActivity.this.unMute();
                }
            }
        });

        //nhấn nút repeat
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagRepeat == 1) {
                    flagRepeat = 2;
                    repeatList = true;
                    btnRepeat.setImageResource(R.drawable.repeat_on);
                } else if (flagRepeat == 2) {
                    flagRepeat = 3;
                    repeatList = false;
                    btnRepeat.setImageResource(R.drawable.repeat_one);
                    MainActivity.mediaPlayer.setLooping(true);
                } else {
                    flagRepeat = 1;
                    btnRepeat.setImageResource(R.drawable.repeat_off);
                    MainActivity.mediaPlayer.setLooping(false);
                }
            }
        });

        //nhấn nút next
        btnNextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position + 1 < arrayList.size()) {
                    prepareMediaPlayer(position + 1);
                    position += 1;
                } else {
                    if (repeatList) {
                        position = 0;
                        prepareMediaPlayer(position);
                    } else {
                        Toast.makeText(MusicActivity.this, "End of playlist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //nhấn nút previous
        btnPrevSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position - 1 > -1) {
                    prepareMediaPlayer(position - 1);
                    position -= 1;
                } else {
                    position = arrayList.size() - 1;
                    prepareMediaPlayer(position);
                }
            }
        });

        //nhấn nút shuffle
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shuffle) {
                    btnShuffle.setImageResource(R.drawable.shuffle_off);
                    arrayList.clear();
                    for (int i = 0; i < sizeArray; i++) {
                        PersonalSong song = (PersonalSong) bundle.getSerializable("song" + i);
                        arrayList.add(song);
                    }
                    shuffle = false;
                } else {
                    btnShuffle.setImageResource(R.drawable.shuffle_on);
                    Collections.shuffle(arrayList);
                    shuffle = true;
                }
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFav) {
                    btnLike.setImageResource(R.drawable.white_like);
                    playListOperations.removeSong(arrayList.get(position).getLinkSong());
                    Toast.makeText(MusicActivity.this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                    checkFav = false;
                } else {
                    Toast.makeText(MusicActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                    btnLike.setImageResource(R.drawable.liked);
                    PersonalSong song = new PersonalSong(arrayList.get(position).getNameSong(), arrayList.get(position).getArtistSong(),
                            arrayList.get(position).getImageSong(), arrayList.get(position).getTimeSong(), arrayList.get(position).getLinkSong());
                    playListOperations = new PlayListOperations(MusicActivity.this);
                    playListOperations.addSong(song);
                    checkFav = true;
                }
            }
        });

        //VolumeBar
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        if (volumeBar.getProgress() == 0) {
            btnVolumeOn.setImageResource(R.drawable.mute);
        } else if (volumeBar.getProgress() > 0 && volumeBar.getProgress() <= 5) {
            btnVolumeOn.setImageResource(R.drawable.volume_small);
        } else if (volumeBar.getProgress() > 5 && volumeBar.getProgress() <= 10) {
            btnVolumeOn.setImageResource(R.drawable.volume_medium);
        } else {
            btnVolumeOn.setImageResource(R.drawable.volume_max);
        }
        volumeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                        if (progress == 0) {
                            btnVolumeOn.setImageResource(R.drawable.mute);
                        } else if (progress > 0 && progress <= 5) {
                            btnVolumeOn.setImageResource(R.drawable.volume_small);
                        } else if (progress > 5 && progress <= 10) {
                            btnVolumeOn.setImageResource(R.drawable.volume_medium);
                        } else {
                            btnVolumeOn.setImageResource(R.drawable.volume_max);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int playPosition = (MainActivity.mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                MainActivity.mediaPlayer.seekTo(playPosition);
                txtElapsedTime.setText(milliSecondsToTimer(MainActivity.mediaPlayer.getCurrentPosition()));
            }
        });
    }

    //phát nhạc
    public void prepareMediaPlayer(int i) {
        arraySong = arrayList.get(i);
        String url = arraySong.getLinkSong();
        try {
            MainActivity.mediaPlayer.reset();
            MainActivity.mediaPlayer.setDataSource(url);
            MainActivity.mediaPlayer.prepare();
            Picasso.with(MusicActivity.this).load(arraySong.getImageSong()).into(coverArt);
            txtArtist.setText(arraySong.getArtistSong());
            txtTitle.setText(arraySong.getNameSong());
            int time = MainActivity.mediaPlayer.getDuration();
//            txtTotalTime.setText(arraySong.getTimeSong());
            txtTotalTime.setText(milliSecondsToTimer(time));
            MainActivity.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause_);
                    updateSeekBar();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnPlay.setImageResource(R.drawable.play);
                if (flagRepeat == 3) {
                    prepareMediaPlayer(position);
                } else {
                    if (position + 1 < arrayList.size()) {
                        prepareMediaPlayer(position + 1);
                        position += 1;
                    } else {
                        if (repeatList) {
                            position = 0;
                            prepareMediaPlayer(position);
                        } else {
                            Toast.makeText(MusicActivity.this, "End of playlist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void mute() {
        audioManager = (AudioManager) MusicActivity.this.getSystemService(Context.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }
        btnVolumeOn.setImageResource(R.drawable.mute);
        volumeBar.setProgress(0);
    }

    private void unMute() {
        audioManager = (AudioManager) MusicActivity.this.getSystemService(Context.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
        btnVolumeOn.setImageResource(R.drawable.volume_max);
        volumeBar.setProgress(10);
    }

    //update thời điểm của bài hát
    private final Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = MainActivity.mediaPlayer.getCurrentPosition();
            txtElapsedTime.setText(milliSecondsToTimer(currentDuration));
        }
    };

    //update progressBar khi play nhạc
    private void updateSeekBar() {
        progressBar.setProgress((int) (((float) MainActivity.mediaPlayer.getCurrentPosition() / MainActivity.mediaPlayer.getDuration()) * 100));
        handler.postDelayed(updater, 1000);
    }

    //hiển thị thời gian dưới dạng 0:00
    private String milliSecondsToTimer(long milliSeconds) {
        String timerString = "";
        String secondString;

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }
        if (seconds < 10) {
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }

        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }

    public void mapping() {
        coverArt = findViewById(R.id.thumbnail);
        txtTitle = findViewById(R.id.songTitle);
        txtArtist = findViewById(R.id.artist);
        txtElapsedTime = findViewById(R.id.elapsed_time);
        txtTotalTime = findViewById(R.id.remaining_time);
        progressBar = findViewById(R.id.progressBar);
        volumeBar = findViewById(R.id.volumeBar);
        btnVolumeOn = findViewById(R.id.speakerMax);
        btnTimer = findViewById(R.id.timer);
        btnLyrics = findViewById(R.id.lyrics);
        btnPrevSong = findViewById(R.id.prevSong);
        btnNextSong = findViewById(R.id.nextSong);
        btnPrev10s = findViewById(R.id.prev10);
        btnNext10s = findViewById(R.id.next10);
        btnPlay = findViewById(R.id.playBtn);
        btnLike = findViewById(R.id.likeBtn);
        btnShuffle = findViewById(R.id.shuffleBtn);
        btnRepeat = findViewById(R.id.repeatBtn);
        btnShare = findViewById(R.id.shareBtn);
        btnClose = findViewById(R.id.btnClose);
    }
}