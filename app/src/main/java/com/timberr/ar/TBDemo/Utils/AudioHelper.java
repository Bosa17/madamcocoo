package com.timberr.ar.TBDemo.Utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

import com.timberr.ar.TBDemo.R;

import java.io.IOException;

public class AudioHelper {

    static final String LOG_TAG = AudioHelper.class.getSimpleName();

    private Context mContext;

    private MediaPlayer mPlayer;
    AudioManager audioManager ;


    public AudioHelper(Context context) {
        this.mContext = context.getApplicationContext();
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

    }

    public void playMusic() {
        int result = audioManager.requestAudioFocus(null,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Log.d("AudioFocus", "Audio focus received");
        } else {
            Log.d("AudioFocus", "Audio focus NOT received");
        }
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(mContext,
                    Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.start_music));
            mPlayer.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not setup media player");
            mPlayer = null;
            return;
        }
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    public void stopPlaying() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

}