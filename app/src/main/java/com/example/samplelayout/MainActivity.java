package com.example.samplelayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText name,mailId, password, mobile_number;
    Button signup,signin;

    DatabaseReference reference;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public String unique_key;
    public FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        mailId=findViewById(R.id.mailId);
        password = findViewById(R.id.Password);
        mobile_number = findViewById(R.id.mobilenumber);
        signup = findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        unique_key= unique_key();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDBConnection();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginPage.class);
                startActivity(i);
            }
        });
    }

    public void checkDBConnection(){

    reference=FirebaseDatabase.getInstance().getReference("signup");
    if(name.length()==0){
        name.setError("Enter the name value");
    }else if(mailId.length()==0){
        mailId.setError("Enter the mailId");
    }else if(password.length()==0){
        password.setError("Enter the password");
    } else if (mobile_number.length()==0) {
        mobile_number.setError("Enter the mobile number");
    }else{
        final String username = name.getText().toString();
        final String userMailId=mailId.getText().toString();
        final String user_password = password.getText().toString();
        final String user_mobilenumber = mobile_number.getText().toString();

        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth.createUserWithEmailAndPassword(userMailId, user_password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final SignupSupport info = new SignupSupport(username,userMailId,user_password,user_mobilenumber, FirebaseAuth.getInstance().getCurrentUser().getUid());


                            FirebaseDatabase.getInstance().getReference("signup").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            } else {
                                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    }

    public String unique_key(){

        int length=5,i;
        char [] charr="0123456789".toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        Random random=new Random();
        for( i=0;i<length;i++){
            char c=charr[random.nextInt(charr.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}