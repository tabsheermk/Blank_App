package com.example.blank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class Result extends AppCompatActivity {

    private static final Random RANDOM = new Random();

    private static final String[] HELLOS = {
            "83.2",
            "97.3",
            "56.7",
            "13.5",
            "77.5",
            "66.4"
    };

    private final int  arrLen = HELLOS.length;

    TextView username, result;
    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        username = (TextView)findViewById(R.id.username2);
        result = (TextView)findViewById(R.id.result);
        homeBtn = (Button)findViewById(R.id.homebutton);
        result.setText(HELLOS[RANDOM.nextInt(arrLen)]);

        homeBtn.setOnClickListener(view ->{
            Intent intent = new Intent(Result.this,HomeActivity.class);
            startActivity(intent);
        });
    }
}