package me.shivzee;

import me.shivzee.ResponseParser;
import me.shivzee.exceptions.ErrorStatusCodeException;
import me.shivzee.exceptions.SomethingWentWrongException;
import me.shivzee.response.Response;

import java.net.URL;

public class Account {

    /** Returns the user email from token*/

    public static String getEmail(String token) throws SomethingWentWrongException {
        Response responser = new Response();
        ResponseParser parser=new ResponseParser();
        String baseUrl = "https://api.gofile.io/getAccountInfo?token="+token;
        try{
            URL url=new URL(baseUrl);
            String response = responser.getResponse(url);
            if(parser.getKeyValue("status",response).equals("ok")){
                return parser.getKeyValue("email",parser.getKeyValue("data",response));
            }else {
                throw new ErrorStatusCodeException("Error Response Received "+parser.getKeyValue("status",response));
            }
        }
        catch (Exception e){
            throw new SomethingWentWrongException("Something went wrong "+e.getMessage());
        }
    }

    /** Returns Account Type According to the subscription */

    public static String getAccountType(String token) throws SomethingWentWrongException {
        Response responser = new Response();
        ResponseParser parser=new ResponseParser();
        String baseUrl = "https://api.gofile.io/getAccountInfo?token="+token;
        try{
            URL url=new URL(baseUrl);
            String response = responser.getResponse(url);
            if(parser.getKeyValue("status",response).equals("ok")){
                return parser.getKeyValue("account",parser.getKeyValue("data",response));
            }else {
                throw new ErrorStatusCodeException("Error Response Received "+parser.getKeyValue("status",response));
            }
        }
        catch (Exception e){
            throw new SomethingWentWrongException("Something went wrong "+e.getMessage());
        }
    }


    /** Returns the number of uploads by the user by token */

    public static String getUploadsCount(String token) throws SomethingWentWrongException {
        Response responser = new Response();
        ResponseParser parser=new ResponseParser();
        String baseUrl = "https://api.gofile.io/getAccountInfo?token="+token;
        try{
            URL url=new URL(baseUrl);
            String response = responser.getResponse(url);
            if(parser.getKeyValue("status",response).equals("ok")){
                return parser.getKeyValue("uploadsCount",parser.getKeyValue("data",response));
            }else {
                throw new ErrorStatusCodeException("Error Response Received "+parser.getKeyValue("status",response));
            }
        }
        catch (Exception e){
            throw new SomethingWentWrongException("Something went wrong "+e.getMessage());
        }
    }

    /** Returns the no of files user has by token*/

    public static String getFilesCount(String token) throws SomethingWentWrongException {
        Response responser = new Response();
        ResponseParser parser=new ResponseParser();
        String baseUrl = "https://api.gofile.io/getAccountInfo?token="+token;
        try{
            URL url=new URL(baseUrl);
            String response = responser.getResponse(url);
            if(parser.getKeyValue("status",response).equals("ok")){
                return parser.getKeyValue("filesCount",parser.getKeyValue("data",response));
            }else {
                throw new ErrorStatusCodeException("Error Response Received "+parser.getKeyValue("status",response));
            }
        }
        catch (Exception e){
            throw new SomethingWentWrongException("Something went wrong "+e.getMessage());
        }
    }

    /** Returns the total size occupied in the cloud/server */

    public static String getTotalSize(String token) throws SomethingWentWrongException {
        Response responser = new Response();
        ResponseParser parser=new ResponseParser();
        String baseUrl = "https://api.gofile.io/getAccountInfo?token="+token;
        try{
            URL url=new URL(baseUrl);
            String response = responser.getResponse(url);
            if(parser.getKeyValue("status",response).equals("ok")){
                return parser.getKeyValue("totalSize",parser.getKeyValue("data",response));
            }else {
                throw new ErrorStatusCodeException("Error Response Received "+parser.getKeyValue("status",response));
            }
        }
        catch (Exception e){
            throw new SomethingWentWrongException("Something went wrong "+e.getMessage());
        }
    }

    /** Returns the total download in a user's account */

    public static String getTotalDownloads(String token) throws SomethingWentWrongException {
        Response responser = new Response();
        ResponseParser parser=new ResponseParser();
        String baseUrl = "https://api.gofile.io/getAccountInfo?token="+token;
        try{
            URL url=new URL(baseUrl);
            String response = responser.getResponse(url);
            if(parser.getKeyValue("status",response).equals("ok")){
                return parser.getKeyValue("totalDownloaded",parser.getKeyValue("data",response));
            }else {
                throw new ErrorStatusCodeException("Error Response Received "+parser.getKeyValue("status",response));
            }
        }
        catch (Exception e){
            throw new SomethingWentWrongException("Something went wrong "+e.getMessage());
        }
    }


    /** Returns a JSON Formatted String containing data of All Files uploaded by user */

    public static String getAllFilesData(String token) throws SomethingWentWrongException {
       String response="{}";
       String baseUrl = "https://api.gofile.io/getUploadsList?token="+token;
       try{
           Response responser = new Response();
           ResponseParser parser=new ResponseParser();
           response = responser.getResponse(new URL(baseUrl));
           if(parser.getKeyValue("status" , response).equals("ok")){
               response = parser.getKeyValue("data",response);
           }else{
               throw new ErrorStatusCodeException("Invalid Status Code "+parser.getKeyValue("status",response));
           }
       }catch (Exception exception){
           throw new SomethingWentWrongException("Something went wrong "+exception.getMessage());
        }
       return response;
    }


}
