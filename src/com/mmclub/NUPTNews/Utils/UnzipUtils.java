package com.mmclub.NUPTNews.Utils;

import android.util.Log;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-14
 * Time: 上午9:15
 */
public class UnzipUtils {

    /*
     *   Refrecence : http://stackoverflow.com/questions/3382996/how-to-unzip-files-programmatically-in-android
     *   Usage:  UnzipUtils.unpackZip("/sdcard/", "hello.zip") ; the file will be unpacked under /sdcard directory
     */


    public static String unpackZip(String path, String zipname) {
        InputStream is;
        ZipInputStream zis;
        String unzip_path = null;
        try {
            Log.d("TAG", "unzip start");
            Log.d("TAG", zipname);
            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                // zapis do souboru
                filename = ze.getName();

                Log.d("TAG", "unzip"  + filename );

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(path + filename);
                    fmd.mkdirs();
                    if (unzip_path == null)
                        unzip_path = fmd.getAbsolutePath();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(path + filename);

                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return unzip_path;
        }

        return unzip_path;
    }
}
