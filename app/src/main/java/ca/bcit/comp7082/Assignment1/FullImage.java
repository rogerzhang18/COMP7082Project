package ca.bcit.comp7082.Assignment1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.util.UUID;

public class FullImage extends Activity {

    private String imagePath;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        int position = getIntent().getExtras().getInt("imgPos");
        imagePath = getIntent().getExtras().getString("path");

        File imgFile = new  File(imagePath);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = findViewById(R.id.full_image_view);

            myImage.setImageBitmap(myBitmap);

        }
        Button buttonUpload;
        buttonUpload = findViewById(R.id.uploadnow);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadMultipart(imagePath);
            }
        });

    }

    private void uploadMultipart(String filepath) {
        //getting name for the image
        String name = "test";
        String uploadurl = "http://142.232.139.102/AndroidUploadImage/upload.php";
        //getting the actual path of the image
        Log.e("Upload", filepath);
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();
            Log.e("Upload", filepath);

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, uploadurl)
                    .addFileToUpload(filepath, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
