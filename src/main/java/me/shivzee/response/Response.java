package me.shivzee.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Response {

    private StringBuilder builder;

    public Response(){
        this.builder = new StringBuilder();
    }

    public String getResponse(URL url){
        try{
            builder = new StringBuilder();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line=reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            reader.close();
            connection.disconnect();
        }
        catch(IOException ioException){
            System.out.println("IO Exception : "+ioException.getMessage());
            builder.append("{\"status\" : \"not ok\"}");
        }
        return builder.toString();
    }
}
