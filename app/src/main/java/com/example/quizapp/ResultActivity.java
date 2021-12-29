package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ResultActivity extends AppCompatActivity {

    TextView pointsText, nameText;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pointsText = findViewById(R.id.pointsText);
        nameText = findViewById(R.id.nameText);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        String quizID = intent.getStringExtra("QUIZ_ID");
        String attempterName = intent.getStringExtra("ATTEMPTER_NAME");

        CollectionReference questionsColRef = db.collection("Quizzes").document(quizID).collection("Questions");
        DocumentReference attempterDocRef = db.collection("Quizzes").document(quizID).collection("Attempters").document(attempterName);

        questionsColRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    count++;
                }
            }
        });

        attempterDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Attempter attempter = documentSnapshot.toObject(Attempter.class);
                pointsText.setText(String.valueOf(attempter.getAttempterPoints()) + "/" + String.valueOf(count*10));
                nameText.setText(attempter.getAttempterName());


            }
        });




    }
}