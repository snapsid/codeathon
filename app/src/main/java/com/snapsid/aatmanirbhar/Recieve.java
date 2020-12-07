package com.snapsid.aatmanirbhar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Recieve extends AppCompatActivity {


    EditText otp;
    Button verify;

    DatabaseReference mDatabaseReference;

    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);

        otp=findViewById(R.id.otpid);
        verify=findViewById(R.id.verifyid);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("otp");

        mReference = FirebaseDatabase.getInstance().getReference();


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String otpSt=otp.getText().toString();

                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI
                        String post = dataSnapshot.getValue().toString();
                        Log.d("otp11", post);

                        if(otpSt.equals(post))
                        {

                            mReference.child("rxd").setValue("verified");
                            mReference.child("rx").setValue(1);

                            Toast.makeText(getApplicationContext(), "verified", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();

                        }



                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w("eeee", "loadPost:onCancelled", databaseError.toException());
                        // ...
                    }
                };
                mDatabaseReference.addListenerForSingleValueEvent(postListener);




            }
        });


    }
}
