package com.softwareacademy.maps.asynctaskdowload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */

public class FileDowload {
    public static String download(String stringUrl, String fileName, ProgressUpdate progressUpdate) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(stringUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(fileName);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {

                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    progressUpdate.updateProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
        return null;
    }
}
