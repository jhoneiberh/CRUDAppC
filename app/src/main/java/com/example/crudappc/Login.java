package com.example.crudappc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity
{

    EditText editUser;
    EditText editPassword;
    Button btnLogin;
    String PASSWORD = "android";
    String USER = "android";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        editUser = (EditText) findViewById(R.id.editUser);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( editUser.getText().toString().equals(USER) && editPassword.getText().toString().equals(PASSWORD))
                {
                    Toast.makeText(Login.this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Login.this, "credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
