package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText UserName,Password,Email;
    Button Register, login;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper  = new DatabaseHelper(this);
        UserName = findViewById(R.id.RegisterUserNametxt);
        Password = findViewById(R.id.RegisterPasswordtxt);
        Email = findViewById(R.id.RegisterEmailtxt);
        Register =findViewById(R.id.Registrationbutton);
        login = findViewById(R.id.loginbutton);
//login
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
//Registration
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( TextUtils.isEmpty(UserName.getText()) && TextUtils.isEmpty(Password.getText()) &&TextUtils.isEmpty(Email.getText()) ){
                    UserName.setError( "UserName is required!" );
                    Password.setError("Password is required!");
                    Email.setError("Email is required!");
                }else if( TextUtils.isEmpty(UserName.getText()) && TextUtils.isEmpty(Password.getText())){
                    UserName.setError( "UserName is required!" );
                    Password.setError("Password is required!");
                }else if( TextUtils.isEmpty(UserName.getText()) && TextUtils.isEmpty(Email.getText()) ){
                    UserName.setError( "UserName is required!" );
                    Email.setError("Email is required!");
                }else if( TextUtils.isEmpty(Password.getText()) &&TextUtils.isEmpty(Email.getText()) ){
                    Password.setError("Password is required!");
                    Email.setError("Email is required!");
                } else if( TextUtils.isEmpty(UserName.getText())){
                    UserName.setError( "UserName is required!" );
                }else if(TextUtils.isEmpty(Password.getText())) {
                    Password.setError("Password is required!");
                }else if(TextUtils.isEmpty(Email.getText())) {
                    Email.setError("Email is required!");
                }else if(Password.getText().length() <6) {
                    Password.setError("Weak Password .Password is 6 characters long");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                    Email.setError("Invalid Email!");
                }else {
                   long id =  databaseHelper.Registration(UserName.getText().toString(),Password.getText().toString(),Email.getText().toString());
                   if(id > 0){
                       Toast.makeText(MainActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                         startActivity(intent);
                       UserName.setText("");
                       Password.setText("");
                       Email.setText("");
                   }

                }

            }
        });


    }
    //shared preferences onResume
    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String username = sharedPreferences.getString("UserName","");
        String password = sharedPreferences.getString("Password", "");
        String email = sharedPreferences.getString("Email", "");
        UserName.setText(username);
        Password.setText(password);
        Email.setText(email);

    }
    //shared preferences onPause
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("UserName", UserName.getText().toString());
        myEdit.putString("Password", Password.getText().toString());
        myEdit.putString("Email", Email.getText().toString());
        myEdit.commit();
    }
}