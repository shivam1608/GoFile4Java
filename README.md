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
