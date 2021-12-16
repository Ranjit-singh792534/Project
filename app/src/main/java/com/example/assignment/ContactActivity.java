package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {
EditText contactName,contactphone,contactemail;
 Button contact ,view;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        databaseHelper  = new DatabaseHelper(this);
        contactName = findViewById(R.id.contactContactNametxt);
        contactphone = findViewById(R.id.contactPhonetxt);
        contactemail = findViewById(R.id.contactEmailtxt);
        contact = findViewById(R.id.addbutton);
        view = findViewById(R.id.viewbtn);

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ContactActivity.this, ViewActivity.class);
                startActivity(activityChangeIntent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( TextUtils.isEmpty(contactName.getText()) && TextUtils.isEmpty(contactphone.getText()) &&TextUtils.isEmpty(contactemail.getText()) ){
                    contactName.setError( "contact Name is required!" );
                    contactphone.setError("phone no is required!");
                    contactemail.setError("Email is required!");
                }else if( TextUtils.isEmpty(contactName.getText()) && TextUtils.isEmpty(contactphone.getText())){
                    contactName.setError( "contact Name is required!" );
                    contactphone.setError("Phone no is required!");
                }else if( TextUtils.isEmpty(contactName.getText()) && TextUtils.isEmpty(contactemail.getText()) ){
                    contactName.setError( "contact Name is required!" );
                    contactemail.setError("Email is required!");
                }else if( TextUtils.isEmpty(contactphone.getText()) &&TextUtils.isEmpty(contactemail.getText()) ){
                    contactphone.setError("Phone no is required!");
                    contactemail.setError("Email is required!");
                } else if( TextUtils.isEmpty(contactemail.getText())){
                    contactName.setError( "contact Name is required!" );
                }else if(TextUtils.isEmpty(contactphone.getText())) {
                    contactphone.setError("Phone no is required!");
                }else if(TextUtils.isEmpty(contactemail.getText())) {
                    contactemail.setError("Email is required!");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(contactemail.getText().toString()).matches()) {
                    contactemail.setError("Invalid Email!");
                }else {
                    long id =databaseHelper.addUserDetail(contactName.getText().toString(),contactphone.getText().toString(),contactemail.getText().toString());
                    if(id>0){
                        Toast.makeText(ContactActivity.this, "Insertion Successful!", Toast.LENGTH_SHORT).show();
                        Intent activityChangeIntent = new Intent(ContactActivity.this, ViewActivity.class);
                             startActivity(activityChangeIntent);
                    }else{
                        Toast.makeText(ContactActivity.this, "Insertion unSuccessful!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }

}
