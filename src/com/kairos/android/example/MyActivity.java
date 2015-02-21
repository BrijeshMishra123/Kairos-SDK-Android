package com.kairos.android.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.kairos.*;
import org.json.JSONException;


import java.io.UnsupportedEncodingException;

public class MyActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // listener
        KairosListener listener = new KairosListener() {

            @Override
            public void onSuccess(String response) {
                Log.d("KAIROS DEMO", response);
            }

            @Override
            public void onFail(String response) {
                Log.d("KAIROS DEMO", response);
            }
        };


        /* * * instantiate a new kairos instance * * */
        Kairos myKairos = new Kairos();

        /* * * set authentication * * */
        String app_id = "arq3241";
        String api_key = "s82nrh2ns92nse2ha712net9rk3nter3";
        myKairos.setAuthentication(this, app_id, api_key);


        try {

            /* * * * * * * * * * * * * * * * * * * * */
            /* * *  Kairos Method Call Examples * * */
            /* * * * * * * * * * * * * * * * * * * */
            /* * * * * * * * * * * * * * * * * * **/
            /* * * * * * * * * * * * * * * * * * */
            /* * * * * * * * * * * * * * * * * **/
            /* * * * * * * * * * * * * * * * * */
            /* * * * * * * * * * * * * * * * **/
            /* * * * * * * * * * * * * * * * */



            /* * * Detect image (URL) * * */
            myKairos.detectImageWithURL("http://media.kairos.com/liz.jpg", "FULL", 0.0, listener);



            /* * * Detect image (Image) * * */
            /*
            Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
            myKairos.detectImageWithData(bitmapImage, "FULL", 0.0, listener);
            */



            /* * * Enroll subject into gallery (URL) * * */
            /*
            myKairos.enrollImageWithURL("http://media.kairos.com/liz.jpg", "your_subject_id", "your_gallery_name", listener);
            */



            /* * * Enroll subject into gallery (Image) * * */
            /*
            Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
            myKairos.enrollImageWithData(bitmapImage, "your_subject_id", "your_gallery_name", listener);
            */



            /* * * Recognize subject in gallery (URL) * * */
            /*
            myKairos.recognizeImageWithURL("http://media.kairos.com/liz.jpg", "your_gallery_name", listener);
            */



            /* * * Recognize subject in gallery (Image) * * */
            /*
            Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
            myKairos.recognizeImageWithData(bitmapImage, "your_gallery_name", listener);
            */



            /* * * List galleries * * */
            /*
            myKairos.listGalleries(listener);
            */



            /* * * List subjects in gallery * * */
            /*
            myKairos.listSubjectsForGallery("your_gallery_name", listener);
            */



            /* * * Delete subject from gallery * * */
            /*
            myKairos.deleteSubject("your_subject_id", "your_gallery_name", listener);
            */



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}