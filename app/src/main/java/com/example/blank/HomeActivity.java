package com.example.blank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    private static final int picId = 123;

    Button camBtn,saveBtn,analyseBtn;
    ImageView clickedImg;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        camBtn = (Button)findViewById(R.id.camera_button);
        saveBtn = (Button)findViewById(R.id.save_button);
        analyseBtn = (Button)findViewById(R.id.analyse_button);
        clickedImg = (ImageView)findViewById(R.id.click_image);

        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, picId);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) clickedImg.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                try {
                    FileOutputStream outStream = null;
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/blank");
                    dir.mkdirs();
                    @SuppressLint("DefaultLocale") String fileName = String.format("%d.jpg", System.currentTimeMillis());
                    File outFile = new File(dir, fileName);
                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                    Toast.makeText(HomeActivity.this, "Image saved successfully",Toast.LENGTH_SHORT).show();
                }catch(FileNotFoundException e){
                    Toast.makeText(HomeActivity.this, "Image not saved",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }catch(IOException e){
                    Toast.makeText(HomeActivity.this, "Image not saved",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

        analyseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Result.class);
                startActivity(intent);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == picId) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            clickedImg.setImageBitmap(photo);
        }
    }
}