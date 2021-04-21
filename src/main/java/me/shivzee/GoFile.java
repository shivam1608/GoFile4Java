/** GoFile4Java
 *  (A GoFile.io API wrapper)
 *  Author : shivzee
 *  Version : 0.1
 *  Release : Stable
 *  GitHub : https://github.com/shivam1608
 *
 *  Warning : Don't complain about anything in this lib !
 *
 *  Need Help ?  Check GitHub Page or DOCs
 * */


package me.shivzee;

import me.shivzee.exceptions.ErrorStatusCodeException;
import me.shivzee.exceptions.FileBridgeBreakException;
import me.shivzee.exceptions.SomethingWentWrongException;
import me.shivzee.exceptions.ValueMissingException;
import me.shivzee.requests.Request;
import me.shivzee.response.Response;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoFile {
    /** Basic Variables for Optional Input **/

    private String email;
    private String token;
    private String password;
    private String description;
    private String tags;
    private String adminCode;
    private int expiryDate;

    private Response responser;
    private ResponseParser responseParser;
    private Request requester;

    public GoFile(){
        email = "";
        token = "";
        password = "";
        description = "";
        tags = "";
        adminCode = "";
        expiryDate = -1;
        this.responser = new Response();
        this.responseParser = new ResponseParser();
        this.requester = new Request();
    }

    /**
     * Arguments
     *
     * email : user email to save file on (optional)
     * token : user token (optional)
     * password : the password to access the file (optional)
     * description : the description for file (optional)
     * tags : tags separated by , (optional)
     * adminCode : the code to access same folder (optional)
     * expiryDate : set expiry date for the file (optional)
     * */

    public GoFile(String email,String token,String password,String description,String tags,String adminCode,int expiryDate){
        this.email = email;
        this.token = token;
        this.password = password;
        this.description = description;
        this.tags = tags;
        this.adminCode = adminCode;
        this.expiryDate = expiryDate;
        this.responser = new Response();
        this.responseParser = new ResponseParser();
        this.requester = new Request();
    }

    public void setEmail(String email){
        this.email = email;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setTags(String tags){
        this.tags = tags;
    }
    public void setAdminCode(String adminCode){
        this.adminCode = adminCode;
    }

    /**
     * building this method in future

    public void setExpiryDate(int expiryDate){
        this.expiryDate = expiryDate;
    }

     */

    /** Mirroring methods ! LOL */

    /** Returns the best suitable server to take uploads */
    public String getServer() throws MalformedURLException, ErrorStatusCodeException {
        String baseUrl = "https://api.gofile.io/getServer";
        URL url = new URL(baseUrl);
        String response = responser.getResponse(url);
        if(responseParser.getKeyValue("status", response).equals("ok")){
            return responseParser.getKeyValue("server",responseParser.getKeyValue("data",response));
        }
        else{
          throw new ErrorStatusCodeException("Error Response Status 'not ok'! ");
        }
    }


    /** Returns Response in array [] after uploading the file
     * Alternative UploadResponseUtils can help you to get the response easily just pass the response
     *
     * UploadResponseUtils utils = new UploadResponseUtils(goFile.upload(file));
     * utils.getFileName();
     * check methods in docs
     *
     * Array indexes
     * 0 : code (view code)  [String]
     * 1 : adminCode  [String]
     * 2 : fileName [String]
     * 3 : md5 (the hash of file)  [String]
     * 4 : download page link  [String]
     */

    public String[] upload(File file) throws MalformedURLException, ErrorStatusCodeException, FileBridgeBreakException {
        List<String> responseData = new ArrayList<String>();
        List<String> options = new ArrayList<String>();
        options.add(adminCode);
        options.add(email);
        options.add(description);
        options.add(password);
        options.add(tags);
        GoFile f=new GoFile();
        String baseUrl = "https://"+f.getServer()+".gofile.io/uploadFile";
        try{
            URL url = new URL(baseUrl);
            String response = requester.getResponse(url , file ,options.toArray(new String[0]));
            if(responseParser.getKeyValue("status" , response).equals("ok")){
                response = responseParser.getKeyValue("data",response);
                responseData.add(responseParser.getKeyValue("code",response));
                responseData.add(responseParser.getKeyValue("adminCode",response));
                responseData.add(responseParser.getKeyValue("fileName",response));
                responseData.add(responseParser.getKeyValue("md5",response));
                responseData.add(responseParser.getKeyValue("downloadPage",response));
            }
            else{
                throw new ErrorStatusCodeException("Invalid Response Received : "+responseParser.getKeyValue("status",response));
            }
        }
        catch (FileBridgeBreakException exception){
            throw new FileBridgeBreakException(exception.getMessage());
        }

        return responseData.toArray(new String[0]);
    }

    /** deleteUpload(code)
     *
     *  Returns true if the file got deleted/removed and false on not
     * Arguments
     * code : the access code of the file or file identifier [String]
     * adminCode : the admin code for the file/folder [String]
     * token : the user token [String]
     * */



    public boolean deleteUpload(String code) throws ValueMissingException, SomethingWentWrongException {
        if(!token.equals("")){
            String baseUrl = "https://api.gofile.io/deleteUpload?c="+code+"&token="+token;
            try{
                String response = responser.getResponse(new URL(baseUrl));
                String status = responseParser.getKeyValue("status",response);
                return status.equals("ok");
            }
            catch (MalformedURLException exception){
                throw new SomethingWentWrongException("Something went wrong "+exception.getMessage());
            }
        }else if (!adminCode.equals("")){
            String baseUrl = "https://api.gofile.io/deleteUpload?c="+code+"&ac="+adminCode;
            try{
                String response = responser.getResponse(new URL(baseUrl));
                String status = responseParser.getKeyValue("status",response);
                return status.equals("ok");
            }
            catch (Exception exception){
                throw new SomethingWentWrongException("Something went wrong "+exception.getMessage());
            }
        }else{
            throw new ValueMissingException("Value of token or adminCode is missing");
        }
    }

}
