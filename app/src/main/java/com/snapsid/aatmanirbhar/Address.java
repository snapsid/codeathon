package com.snapsid.aatmanirbhar;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Address extends AppCompatActivity {

    EditText name, phone, address, pincode;
    Button place;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        place= findViewById(R.id.placeid);

        name=findViewById(R.id.nameid);
        phone=findViewById(R.id.phoneid);
        address=findViewById(R.id.addressid);
        pincode=findViewById(R.id.pincodeid);
        mDatabase = FirebaseDatabase.getInstance().getReference("order");

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameSt= name.getText().toString();
                String phoneSt= phone.getText().toString();
                String addressSt= address.getText().toString();
                String pincodeSt= pincode.getText().toString();

                String myphone="7037107108";



                String fin="---DOT QUESTIONMARK---\n"+
                        "Name: "+nameSt+"\n"+
                        "Phone Num: "+phoneSt+"\n"+
                        "Address: "+addressSt+"\n"+
                        "pincode: "+pincodeSt+"\n"+
                        "Order: "+"Product 1"+"\n"+
                        "Amount: "+"Rs 1"+"\n";

                Map userData=new HashMap<>();

                userData.put("name", nameSt);
                userData.put("phone", phoneSt);
                userData.put("address", addressSt);
                userData.put("pincode", pincodeSt);
                userData.put("order", "product 1");
                userData.put("amount", "1 rs");
                mDatabase.child(nameSt).setValue(userData);



                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(myphone, null, fin, null, null);




            }
        });


    }
}