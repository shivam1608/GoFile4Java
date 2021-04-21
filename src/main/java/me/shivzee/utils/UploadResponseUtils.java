package me.shivzee.utils;

/** The UploadResponseUtils Class is for easily parsing the upload response without any response table just pass the response
 * and call the methods thus making it easier for the person using this lib made by a NOOB !
 * */

public class UploadResponseUtils {
    private String [] uploadResponse;
    public UploadResponseUtils(String [] uploadResponse){
        this.uploadResponse = uploadResponse;
    }

    public String getCode(){
        return uploadResponse[0];
    }
    public String getAdminCode(){
        return uploadResponse[1];
    }
    public String getFileName(){
        return uploadResponse[2];
    }
    public String getFileHash(){
        return uploadResponse[3];
    }
    public String getDownloadLink(){
        return uploadResponse[4];
    }
}
