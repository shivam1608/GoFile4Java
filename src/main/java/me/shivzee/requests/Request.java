package me.shivzee.requests;

import me.shivzee.exceptions.FileBridgeBreakException;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    private StringBuilder builder;
    private String[] optionKeys = {"ac","email","description","password","tags"};

    public Request(){
        builder = new StringBuilder();
    }

    public String getResponse(URL url , File file ,String[] Options) throws FileBridgeBreakException {
        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept" , "application/json");


            FileBody fileBody = new FileBody(file);

            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
            multipartEntity.addPart("file" , fileBody);

            for(int i=0;i<Options.length;i++){
                if(!Options[i].equals("")){
                    StringBody stringBody=new StringBody(Options[i]);
                    multipartEntity.addPart(optionKeys[i] , stringBody);
                }
            }

            connection.setRequestProperty("Content-Type",multipartEntity.getContentType().getValue());
            OutputStream outputStream = connection.getOutputStream();

            try{
                multipartEntity.writeTo(outputStream);
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line=reader.readLine()) != null){
                    builder.append(line).append("\n");
                }
            }
            catch (Exception exception){
                throw new FileBridgeBreakException("File Could Not Be Uploaded "+exception.getMessage());
            }
            finally {
               outputStream.close();
               connection.disconnect();
            }

        }
        catch (IOException ioException){
           System.out.println("IOException Caught (Something went wrong) "+ioException.getMessage());
           builder.append("{\"status\" : \"not ok\"}");
        }
       return builder.toString();
    }
}
