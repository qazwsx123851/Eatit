package com.example.oolong.eatit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.oolong.eatit.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText etAccount,etId,etPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etAccount = findViewById(R.id.etaccount);
        etId = findViewById(R.id.etname);
        etPassword = findViewById(R.id.etpassword);

        btnSignUp = findViewById(R.id.btnSignUp);

        //Init FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog(SignUp.this);
                mdialog.setMessage("Please Waiting");
                mdialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etAccount.getText().toString()).exists()){
                            mdialog.dismiss();
                            Toast.makeText(SignUp.this,"Account already exists",Toast.LENGTH_SHORT).show();
                        }else {
                            mdialog.dismiss();
                            User user = new User(etId.getText().toString(),etPassword.getText().toString());
                            table_user.child(etAccount.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"SignUp Successfully!!",Toast.LENGTH_SHORT).show();
                            finish();
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
