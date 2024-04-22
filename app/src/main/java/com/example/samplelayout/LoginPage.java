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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    EditText mailId,password;
    Button signin;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mailId=findViewById(R.id.mailId);
        password=findViewById(R.id.Password);
        signin=findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });
    }

public void checkConnection(){
        mAuth=FirebaseAuth.getInstance();
        final String MailId= mailId.getText().toString();
        final String Password=password.getText().toString();

        if (mailId.length()==0){
            mailId.setError("Enter email id");
        } else if (password.length()==0) {
            password.setError("Enter the password");
        }else{
            user =FirebaseAuth.getInstance().getCurrentUser();
            mAuth.signInWithEmailAndPassword(MailId, Password)
                    .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent i= new Intent(LoginPage.this, NotesPage.class);
                                startActivity(i);




                            } else {
                                Toast.makeText(LoginPage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }

}
}