package com.example.samplelayout;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotesPage extends AppCompatActivity {
EditText notes;
Button upload;
DatabaseReference reference;
FirebaseAuth mAuth;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        notes=findViewById(R.id.notes);
        upload=findViewById(R.id.uploadNotes);
        mAuth=FirebaseAuth.getInstance();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
checkConnection();
            }
        });

    }

    public void checkConnection(){
        reference=FirebaseDatabase.getInstance().getReference("notes");
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(notes.length()==0){
            notes.setError("Please provide notes to upload");
        }else{
            final String note=notes.getText().toString();

            final notesSupportClass info = new notesSupportClass(note,FirebaseAuth.getInstance().getCurrentUser().getUid());


            FirebaseDatabase.getInstance().getReference("notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(NotesPage.this, "notes upload failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
}