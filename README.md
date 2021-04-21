# GoFile4Java
The most light weight and stable library to wrap GoFile.io API made with Java 

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
