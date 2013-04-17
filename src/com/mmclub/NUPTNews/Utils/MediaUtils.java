package com.mmclub.NUPTNews.Utils;

import android.media.MediaPlayer;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-13
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class MediaUtils {
    /**
     * Usage:
     *
     * MediaUtils media = MediaUtils.getMediaPalyer();
     * media.play("file:///hello.mp3");
     * media.stop();
     * media.release();
     *
     */


    private static MediaUtils _MediaUtils;
    private MediaPlayer mediaPlayer;


    private MediaUtils(){
        mediaPlayer = new MediaPlayer();
    }

    public static MediaUtils getInstance(){
        if (_MediaUtils == null){
            _MediaUtils = new MediaUtils();
        }
        return _MediaUtils;
    }

    public void play(File file){
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(String file){
        try {
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }


}
