package com.lhd.huynhduc.managelibrary;

import com.lhd.huynhduc.managelibrary.Class.Variable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by huynhduc on 13/01/2018.
 */

public class HTTPDataHandle {
    static String DATA = "";
    public HTTPDataHandle() {
    }
    public String getHTTPData(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line  = "";
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);

                }
                DATA =  stringBuilder.toString();
                httpURLConnection.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }
        return Variable.decode(DATA);
    }

    public void postHTTPData(String urlString,String json){
        try{
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int lenght = out.length;
            httpURLConnection.setFixedLengthStreamingMode(lenght);
            httpURLConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            httpURLConnection.connect();
            try(OutputStream os = httpURLConnection.getOutputStream())
            {
                os.write(out);
            }
            InputStream response = httpURLConnection.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void putHTTPData(String urlString,String value){
        try{
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            byte[] out = value.getBytes(StandardCharsets.UTF_8);
            int lenght = out.length;
            httpURLConnection.setFixedLengthStreamingMode(lenght);
            httpURLConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            httpURLConnection.connect();
            try(OutputStream os = httpURLConnection.getOutputStream())
            {
                os.write(out);
            }
            InputStream response = httpURLConnection.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteHTTPData(String urlString,String js){
        try{
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setDoInput(true);
            byte[] out = js.getBytes(StandardCharsets.UTF_8);
            int lenght = out.length;
            httpURLConnection.setFixedLengthStreamingMode(lenght);
            httpURLConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            httpURLConnection.connect();
            try(OutputStream os = httpURLConnection.getOutputStream())
            {
                os.write(out);
            }
            InputStream response = httpURLConnection.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
