package com.example.pulseoneapp_v2;

import static android.widget.Toast.*;
import static android.widget.Toast.makeText;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText edUsername, edEmailAddress, edPassword;
    Button btn;
    TextView tv1;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextUsername);
        edEmailAddress = findViewById(R.id.editTextEmailAddress);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv1 = findViewById(R.id.textViewForgotPassword);
        tv2 = findViewById(R.id.textViewRegisterHere);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = edUsername.getText().toString();
                String email = edEmailAddress.getText().toString();
                String password = edPassword.getText().toString();
                Database db =new Database(getApplicationContext(),"pulseoneapp_v2", null,1);
                if(username.length() == 0 || password.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill in all the details", LENGTH_SHORT).show();
                }else{
                    if(db.login(username, email, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Success", LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        //to save our data with key and value;
                        editor.apply();
                        startActivity(new Intent(Login.this,Homepage.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username, Email and Password", LENGTH_SHORT).show();
                    }
                 }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(Login.this,Registration.class));
                }
            });
        }
}
