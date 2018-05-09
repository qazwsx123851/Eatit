package com.example.oolong.eatit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oolong.eatit.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText edaccount,edpassword;
    Button btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edaccount = (MaterialEditText)findViewById(R.id.etaccount);
        edpassword = (MaterialEditText)findViewById(R.id.etpassword);
        btnsignin = findViewById(R.id.btnSignin);

        //Init FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mdialog = new ProgressDialog(SignIn.this);
                mdialog.setMessage("Please Waiting");
                mdialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user not in database
                        if(dataSnapshot.child(edaccount.getText().toString()).exists()){
                            //get user information
                            mdialog.dismiss();
                            User user = dataSnapshot.child(edaccount.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(edpassword.getText().toString())){
                                Toast.makeText(SignIn.this, "Sign Suucess!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SignIn.this, "Sign Failed!",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SignIn.this, "User Not exist!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
