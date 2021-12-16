package com.example.assignment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    EditText UserName,Password,Email;
    Button Register, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //variables
        databaseHelper  = new DatabaseHelper(this);
        UserName = findViewById(R.id.loginUserNametxt);
        Password = findViewById(R.id.loginPasswordtxt);
        Email = findViewById(R.id.loginEmailtxt);
        Register =findViewById(R.id.Registrationbutton);
        login = findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //validation
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
                    boolean check =databaseHelper.checkUser(UserName.getText().toString(),Password.getText().toString(),Email.getText().toString());
                 if(check == true){
                     Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(LoginActivity.this, ContactActivity.class);
                       LoginActivity.this.startActivity(intent);
                 }else{
                     Toast.makeText(LoginActivity.this, "Login unSuccessful!", Toast.LENGTH_SHORT).show();
                 }

                }

            }
        });
        //Register button
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(activityChangeIntent);

            }
        });

    }
    //shared preference on resume
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
//shared preferences onpause
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
