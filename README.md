# GoFile4Java
The most light weight and stable library to wrap GoFile.io API made with Java. Read <https://gofile.io/api> for detailed arguments

## Add to your projects
Easy to add in your projects using gradle, maven or jar
### Gradle
- Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
- Add the dependency
```gradle
dependencies {
	        implementation 'com.github.shivam1608:GoFile4Java:main-SNAPSHOT'
	}
```

### Maven
- Add the repository
```maven
      <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
- Add the dependency
```maven 
	<dependency>
	    <groupId>com.github.shivam1608</groupId>
	    <artifactId>GoFile4Java</artifactId>
	    <version>main-SNAPSHOT</version>
	</dependency>

```

### Jar 
Download the jar from this repo 
```
out/artifact/GoFile4Java_Jar
```

## Uploading Files
- Creating the instance
``` java
GoFile goFile = new GoFile();
```
- Setting up Options (optional inputs)
``` java
goFile.setEmail("example@domain.com");
goFile.setToken("0xd2f32df334213xhsu7783hdhajdd");
goFile.setPassword("password");
goFile.setDescription("This is an example description");
goFile.setTags("tag1, tag2, tag3");
goFile.setAdminCode("1234G67U5T");
```
- OR
``` java
GoFile goFile = new GoFile(email, token, password, description, tags, adminCode);
```
expiry date is not supported now but will be added in future

- Uploading the File
``` java
goFile.upload(file)
```
file is the File Object of Java in java.io package


## Geting the Upload Response
- The response is in array formated as
``` java
String [] uploadResponse = goFile.upload(file);
uploadResponse[0]; // View Link Code
uploadResponse[1]; // Admin Code
uploadResponse[2]; // File Name
uploadResponse[3]; // MD5 Hash
uploadResponse[4]; // Download page Link
```
- The UploadResponseUtils() class (This class is used to get response using methods thus making it easy)
``` java
UploadResponseUtils response = new UploadResponseUtils(goFile.upload(file));
response.getCode();
response.getAdminCode();
response.getFileName();
response.getFileHash();
response.getDownloadLink();
```

## Deleting Uploads
- For this either the token or the adminCode must be specified
``` java
goFile.setToken("0xd2f32df334213xhsu7783hdhajdd"); // anyone of the two ~
goFile.setAdminCode("1234G5Ty6");                  // ~must be sepcified
```
- Calling the deleteUpload() function (returns true/false )
``` java
boolean status = goFile.deleteUpload(code);  // code : the file code after /d/ of the link Example : Y6gey79
```
## Account Information
- The Account() class (Seperate static class for handling account data)
``` java
String token = "0xd2f32df334213xhsu7783hdhajdd";  // the token is required for every method in Account class
```
- Functions/Methods in Account() class
``` java
Account.getEmail(token);           // returns the email of user [String]
Account.getAccountType(token);     // returns the account type [String] (standard / donor / premium)
Account.getUploadsCount(token);    // returns the total uploads of user [String]
Account.getFilesCount(token);      // returns the total files uploaded [String]
Account.getTotalSize(token);       // returns the total storage occupied on cloud in bytes [String]
Account.getTotalDownloads(token);  // returns the total number of downloads [String]
```
- The getAllFilesData() method/function (returns a JSON String containing all data about files)
``` java
String JSONString = Account.getAllFilesData(token); // Parse using any json parsing lib
```
- No Utils is available for all files parsing but will be available maybe in future 
