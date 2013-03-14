package com.mmclub.NjuptNews.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-13
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class DownloadTask  extends AsyncTask<String, Integer, String> {

        public static final String PATH = "/sdcard/njuptNews";


        @Override
        protected String doInBackground(String... sUrl) {
            try {
                URL url = new URL(sUrl[0]);
                String file_name = sUrl[1];
                URLConnection connection = url.openConnection();
                connection.connect();
                // this will be useful so that you can show a typical 0-100% progress bar
                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(PATH + file_name);

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
                UnzipUtils.unpackZip(PATH, file_name);
                Log.d("TAG", "done");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "expection");
            }
            return null;
        }
}
