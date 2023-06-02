package com.example.pulseoneapp_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirmPassword = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonRegister);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"pulseoneapp_v2", null,1);
                if(username.length() == 0 || email.length() ==0 || password.length() == 0 || confirm.length() ==0){
                    Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this,Login.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit and special symbol!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                    Toast.makeText(getApplicationContext(), "Password and Confirm password did not match", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }
        public static boolean isValid(String password){
            int f1=0, f2=0, f3=0;
            if (password.length()<8){
                return false;
            } else {
                for (int p = 0; p < password.length(); p++){
                    if(Character.isLetter(password.charAt(p))){
                        f1=1;
                    }
                }
                for (int r = 0; r < password.length(); r++){
                    if(Character.isDigit(password.charAt(r))){
                    f2=1;
            }
        }
                for (int s = 0; s < password.length(); s++){
                    char c = password.charAt(s);
                    if(c>=33&&c<=46||c==64){
                        f3=1;
                    }
                }
                return f1 == 1 && f2 == 1 && f3 == 1;
            }
    }
}
