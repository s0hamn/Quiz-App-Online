package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddQuestionActivity extends AppCompatActivity {

    TextView quizIDText;
    EditText optionAEt, optionBEt, optionCEt, optionDEt, questionEt, correctOptionEt;
    String questionText, optionA,optionB,optionC,optionD,correctOption, quizID;
    FloatingActionButton saveQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        quizIDText = findViewById(R.id.quizIDText);
        optionAEt = findViewById(R.id.optionAEt);
        optionBEt = findViewById(R.id.optionBEt);
        optionCEt = findViewById(R.id.optionCEt);
        optionDEt = findViewById(R.id.optionDEt);
        correctOptionEt = findViewById(R.id.correctOptionEt);
        saveQuestionButton = findViewById(R.id.saveQuestionButton);
        questionEt = findViewById(R.id.questionEt);

        Intent intent = getIntent();
        quizID = intent.getStringExtra("QUIZ_ID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference questionsRef= db.collection("Quizzes").document(quizID).collection("Questions");



        quizIDText.setText("QUIZ ID : " + quizID );

        saveQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionText = questionEt.getText().toString().trim();
                optionA = optionAEt.getText().toString().trim();
                optionB = optionBEt.getText().toString().trim();
                optionC = optionCEt.getText().toString().trim();
                optionD = optionDEt.getText().toString().trim();
                correctOption = correctOptionEt.getText().toString().trim();

                if((questionText.equals("") || optionA.equals("") || optionB.equals("") || optionC.equals("") || optionD.equals("") || correctOption.equals(""))){
                    Toast.makeText(AddQuestionActivity.this, "Please Enter Valid value", Toast.LENGTH_SHORT).show();
                }else{
                    if (correctOption.equals(optionA) || correctOption.equals(optionB)|| correctOption.equals(optionC)|| correctOption.equals(optionD)){
                        Option A = new Option(optionA);
                        Option B = new Option(optionB);
                        Option C = new Option(optionC);
                        Option D = new Option(optionD);
                        Option correctOpt = new Option(correctOption);

                        List<Option> options = new ArrayList<>();
                        options.add(A);
                        options.add(B);
                        options.add(C);
                        options.add(D);

                        Question question = new Question(questionText, options, correctOpt);

                        DocumentReference questionDocRef = questionsRef.document(questionText);


                        questionDocRef.set(question).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddQuestionActivity.this, "Question added successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                        finish();
                    }
                    else{
                        Toast.makeText(AddQuestionActivity.this, "Correct option should match with one of the given Options!", Toast.LENGTH_SHORT).show();
                    }






                }


            }
        });


    }
}