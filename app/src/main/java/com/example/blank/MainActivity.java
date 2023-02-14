package com.example.blank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button)findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if(user.equals("") || pass.equals("") || repass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields",Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    Boolean checkUser = DB.checkUsername(user);
                    if(!checkUser) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User already exists! Please sign in",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Passwords not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}