Kairos SDK (Android)
==============

Kairos is the easist way add **Face-Recognition** to your Android apps. Our API provides a full-featured and robust Face-Recognition backend, right out of the box. This is the Android client for the [Kairos Facial Recognition API](https://www.kairos.com). The package includes both the **SDK** .jar file, as well as an **example app project (IntelliJ IDEA)**. Continue reading to learn how to integrate Kairos into your Android apps.

We also have official SDKs for [iOS](https://github.com/kairosinc/Kairos-SDK-iOS), [Javascript](https://github.com/kairosinc/Kairos-SDK-Javascript) and [PHP](https://github.com/kairosinc/Kairos-SDK-PHP). 

_*Thanks to contributions by some of our customers, we also have unofficial wrappers for [Ruby](https://github.com/kany/kairos-api) and [.NET](https://github.com/humbywan/Kairos.Net)._

## What You'll Need
* An Android app project
* Internet connectivity for your device or emulator

## Limitations in this Version
This is an early version and has a few limitations. First of which, the SDK is bundled with the loopj network library (v 1.4.6) and will cause conflicts if your android app also uses loopj. Please use a different network library to avoid conflicts, if possible. Secondly, this version does not provide any camera views for image capture -- although this feature is on the roadmap for a near-future update. Finally, the example app project included was built using an IDE called IntelliJ IDEA. If you use a different IDE you'll need to move the source code files manually into your project.




---



## How to do a Quick Demo
If you just want to do a quick test run, open the **example app project** (Intellij IDEA) that is included with the SDK and follow these steps:

1. [Create your free developer account](https://www.kairos.com/signup)
2. Log into the Kairos Developer Dashboard
3. Create an application and copy your **App Id** & **App Key**
3. Paste them into the lines above the authentication method in MyActivity.java 
4. Run the app on your **device**, and watch for the API response in the log output.



---


## How to Install Kairos in your own App

1. [Create your free Kairos developer account](https://www.kairos.com/signup) if you don't already have one.
2. Log into the [dashboard](https://www.kairos.com/login) and create a new app.
3. Copy your **App ID** & **App Key** (you'll need them later).
4. [Download](https://github.com/kairosinc/Kairos-SDK-iOS) the SDK and unzip the package.
5. Navigate to the 'libs' folder which contains the SDK library file **KairosSDK.jar**.
6. Add the SDK library file (**KairosSDK.jar**) into your Android project.  
7. Import the library files wherever you want to use the SDK

```
import com.kairos.*;
```

## Authenticate Once

Before you can make API calls you'll need to create a Kairos object and set your credentials **App Id** and **App Key** (You only need to do this once). Add your App Id and App Key into the setAuthentication() method like so:

```
        // instantiate a new kairos instance
        Kairos myKairos = new Kairos();

        // set authentication
        String app_id = "arq3241";
        String api_key = "s82nrh2ns92nse2ha712net9rk3nter3";
        myKairos.setAuthentication(this, app_id, api_key);

```

## The KairosListener
Before we can start calling methods, we'll need a place to receive notifications and data when the methods return. For this purpose, the SDK provides a listener class to return the results of all asynchronous method calls. A Kairos listener is like a callback method. Instantiate a listener like so, and pass the instance into your desired kairos method. You can add your own code inside of the provided success and fail code blocks and use the provided response object as you like:

```
// Create an instance of the KairosListener
KairosListener listener = new KairosListener() {

  @Override
  public void onSuccess(String response) {
     // your code here!
     Log.d("KAIROS DEMO", response);
  }

  @Override
  public void onFail(String response) {
     // your code here!
     Log.d("KAIROS DEMO", response);
  }
};
```
---

    
## The Detect Method

The **detect** method returns you detailed information on face attributes for any images you send it. It can provide you with coordinates of features such as eyes, nose, mouth, etc. See the examples below:

###Parameters:
*	Bitmap **image**, or String **image**
*	String **selector** (optional: can be null)
*	double **minHeadScale** (optional: can be null)
*	KairosListener **listener**
*	_(See the [API Docs](http://kairos.com/docs/) for details on what the parameters do)_

###Returns:
* String **response** (face attribute data)



###Example Usage:
```
// Bare-essentials Example:
// This example uses only an image url, setting optional params to null
String image = "http://media.kairos.com/liz.jpg";
myKairos.detect(image, null, null, listener);


// Fine-grained Example:  
// This example uses a bitmap image and also optional parameters
Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
String selector = "FULL";
String minHeadScale = "0.25"; 
myKairos.detect(image, selector, minHeadScale, listener);
            
```
    
---

        
## The Enroll Method

The **enroll** methods let you register a face for later recognition. You can enroll a new face, or enroll different photos of an already enrolled for better recognition accuracy. There are four flavors of the enroll method available for you to suit your particular use case. See the examples below:
###Parameters:
*	Bitmap **image**, or String **image**
*	String **galleryId**
*	String **subjectId**
*	String **selector** (optional: can be null)
*	String **multipleFaces** (optional: can be null)
*	double **minHeadScale**  (optional: can be null)
*	KairosListener **listener**
*	_(See the [API Docs](http://kairos.com/docs/) for details on what the parameters do)_

###Returns:
* String **response** (details of the enroll request)



###Example Usage:

```
// Bare-essentials Example:
// This example uses only an image url, setting optional params to null
String image = "http://media.kairos.com/liz.jpg";
String subjectId = "Elizabeth";
String galleryId = "friends";
myKairos.enroll(image, subjectId, galleryId, null, null, null, listener);
            
         
// Fine-grained Example:     
// This example uses a bitmap image and also optional parameters
Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
String subjectId = "Elizabeth";
String galleryId = "friends";
String selector = "FULL";
String multipleFaces = "false";
String minHeadScale = "0.25";
myKairos.enroll(image, 
				subjectId, 
				galleryId, 
				selector, 
				multipleFaces, 
				minHeadScale, 
				listener);

            
```
---

    

## The Recognize Method

The **recognize** method attempts to recognize an image against a specified gallery, and returns a list of subjectIds and their confidence values as possible matches. For best recognition accuracy, it's reccomended that you have multiple enrollments (different images) per face. There are four flavors of the recognize method to suit your particular use case. See examples below: 

###Parameters:
*	Bitmap **image**, or String **image**
*	String **galleryId**
*	String **selector**  (optional: can be null)
*	String **threshold**  (optional: can be null)
*	String **minHeadScale**  (optional: can be null)
*	String **maxNumResults**  (optional: can be null)
*	KairosListener **listener**
*	_(See the [API Docs](http://kairos.com/docs/) for details on what the parameters do)_

###Returns:
* String **response** (list of possible matching subjects and their confidence values)




###Example Usage:
```
// Bare-essentials Example:
// This example uses only an image url, setting optional params to null
String image = "http://media.kairos.com/liz.jpg";
String galleryId = "friends";
myKairos.recognize(image, galleryId, null, null, null, null, listener);
            
        
// Fine-grained Example:    
// This example uses a bitmap image and also optional parameters
Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
String galleryId = "friends";
String selector = "FULL";
String threshold = "0.75";
String minHeadScale = "0.25";
String maxNumResults = "25";
myKairos.recognize(image, 
				    galleryId, 
				    selector, 
				    threshold, 
				    minHeadScale, 
				    maxNumResults, 
				    listener);

            
```
---
    
## List All Galleries

This method lists out all galleries you have created. 

###Parameters:
*	KairosListener **listener**

###Returns:
* String **response** (list of all galleries)


###Example Usage:

```
// List out all your galleries
myKairos.listGalleries(listener);
            
```
---
        
## List All Subjects in a Gallery

This method lists out all subjects enrolled in a given gallery. 

###Parameters:
*	String **galleryId**
*	KairosListener **listener**

###Returns:
* String **response** (list of all subjects in a given gallery)


###Example Usage:


```
// List out all subjects in a given gallery
String galleryId = "friends";
myKairos.listSubjectsForGallery(galleryId, listener);
            
```
---
    
## Delete a Subject from a Gallery

This method deletes a given subject. All enrollments for the given subject will be removed from the gallery. 

###Parameters:
*	String **subjectId**
*	String **galleryId**
*	KairosListener **listener**

###Returns:
* String **response** (confirmation of the results of the request)


###Example Usage:

```
// List out all subjects in a given gallery
String subjectId = "Tyrion";
String galleryId = "friends";
myKairos.deleteSubject(subjectId, galleryId, listener);
            
```
---

## View the Examples

Also see provided examples included in the example project. It contains clear examples on how to use all of the available methods in the file MyActivity.java. Just make sure to plug in your application credentials ([Create an account if you don't have one](http://kairos.com)). Also, check out the API documentation at [https://developer.kairos.com/docs](https://www.kairos.com/docs)

[![Stack Share](http://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](http://stackshare.io/kairos-api/kairos-facial-recognition-api)

##Support 
Have an issue? [Contact us](mailto:api@kairos.com) or [create an issue on GitHub](https://github.com/kairosinc/Kairos-SDK-Android)
