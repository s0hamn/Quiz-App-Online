package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SetQuizPasswordActivity extends AppCompatActivity {

    CardView setPasswordCardView;
    EditText setPasswordEt;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference quizzesRef = db.collection("Quizzes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_quiz_password);

        setPasswordEt = findViewById(R.id.setPasswordEt);
        setPasswordCardView = findViewById(R.id.setPasswordCardView);

        Intent i = getIntent();
        String quizID = i.getStringExtra("QUIZ_ID");

        setPasswordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = setPasswordEt.getText().toString().trim();
                if(!(password.length() < 6)){
                    DocumentReference quizRef = quizzesRef.document(quizID);
                    Quiz quiz = new Quiz(quizID, password);
                    quizRef.set(quiz);
                    Intent intent = new Intent(SetQuizPasswordActivity.this, SetQuizActivity.class);
                    intent.putExtra("QUIZ_ID", quizID);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SetQuizPasswordActivity.this, "Password should be at least 6 digits long", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}