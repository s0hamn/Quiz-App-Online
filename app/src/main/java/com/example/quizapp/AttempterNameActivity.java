package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AttempterNameActivity extends AppCompatActivity {

    EditText attempterNameEt;
    String quizID, attempterName;
    CardView enterNameCardView;



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference quizRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempter_name);

        attempterNameEt = findViewById(R.id.attempterNameEt);
        Intent intent = getIntent();

        quizID = intent.getStringExtra("QUIZ_ID");
        enterNameCardView = findViewById(R.id.enterNameCardView);

        quizRef = db.collection("Quizzes").document(quizID);



        enterNameCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempterName = attempterNameEt.getText().toString().trim();

                if(!attempterName.equals("")){
                    Attempter attempter = new Attempter(attempterName, 0);
                    DocumentReference attempterRef = quizRef.collection("Attempters").document(attempterName);
                    attempterRef.set(attempter).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AttempterNameActivity.this, "You can attempt your QUIZ", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(AttempterNameActivity.this, AttemptQuizActivity.class);
                            intent1.putExtra("QUIZ_ID", quizID);
                            intent1.putExtra("ATTEMPTER_NAME", attempterName);
                            startActivity(intent1);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }else{
                    Toast.makeText(AttempterNameActivity.this, "Please enter your Name", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}