package com.snapsid.aatmanirbhar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText name, email, phone, pass;
    Button register;
    TextView login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        name=findViewById(R.id.nameid);
        email=findViewById(R.id.userid);
        phone=findViewById(R.id.phoneid);
        pass=findViewById(R.id.passid);

        register=findViewById(R.id.creid);

        login=findViewById(R.id.alreadyid);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name1=name.getText().toString();
                final String email1=email.getText().toString();
                final String phone1=phone.getText().toString();
                final String pass1=pass.getText().toString();

                mAuth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Error in LOGIN", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String userId=mAuth.getCurrentUser().getUid();

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                            Map userData=new HashMap<>();
                            userData.put("name", name1);
                            userData.put("email", email1);
                            userData.put("phone", phone1);


                            reference.updateChildren(userData);

                            Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }


                    }
                });




            }
        });



    }
}
