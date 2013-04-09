package com.mmclub.NjuptNews.Utils;

import com.mmclub.NjuptNews.NewsApplication;
import org.apache.http.util.EncodingUtils;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-24
 * Time: 下午12:51
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {
    public static String readFile(String fileName) throws IOException {
        String res = "";
        try {
            FileInputStream fin = NewsApplication.getContext().openFileInput(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public static String readFile(File file) {
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

        return text.toString();
    }


    public static  String[] file_list_to_string_array(List<File> list){
        String[] strings = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            strings[i] = list.get(i).getName();
        }
        return strings;
    }

}
