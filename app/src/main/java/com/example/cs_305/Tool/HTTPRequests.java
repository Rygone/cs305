package com.example.cs_305.Tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public final class HTTPRequests {
    public static String downloadUrl(String url) {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            URL uUrl = new URL(url);
            connection = (HttpsURLConnection) uUrl.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            stream = connection.getInputStream();
            if (stream != null) {
                StringBuilder textBuilder = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader
                        (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c = 0;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }
                result = textBuilder.toString();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try { stream.close(); }
                catch (IOException e) { e.printStackTrace(); }
            }
            if (connection != null) {
                try { connection.disconnect(); }
                catch (Exception e) { e.printStackTrace(); }
            }
        }
        return result;
    }
}
