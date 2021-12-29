package com.example.quizapp;

import static com.example.quizapp.R.color.light_green;
import static com.example.quizapp.R.drawable.tv_rounded_green;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText enterIDEt;
    CardView createQuizCardView, joinQuizCardView, editQuizCardView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference quizzesRef = db.collection("Quizzes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterIDEt = findViewById(R.id.enterIDEt);


        createQuizCardView = findViewById(R.id.createQuizCardView);
        joinQuizCardView = findViewById(R.id.joinQuizCardView);
        editQuizCardView = findViewById(R.id.editQuizCardView);

        createQuizCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createQuizCardView.setCardElevation(50.0f);
                String quizID;
                quizID = enterIDEt.getText().toString();

                if(!quizID.equals("")){
                    DocumentReference quizRef = quizzesRef.document(quizID);
                    quizRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                Toast.makeText(MainActivity.this, "Quiz already exists, Please choose a different ID", Toast.LENGTH_SHORT).show();
                            }else{

                                Intent intent = new Intent(MainActivity.this, SetQuizPasswordActivity.class);
                                intent.putExtra("QUIZ_ID", quizID);
                                startActivity(intent);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter a valid Quiz ID", Toast.LENGTH_SHORT).show();
                }

            }
        });

        joinQuizCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinQuizCardView.setCardElevation(50.0f);
                String quizID;
                quizID = enterIDEt.getText().toString();

                if(!quizID.equals("")){
                    DocumentReference quizRef = quizzesRef.document(quizID);
                    quizRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                Intent intent = new Intent(MainActivity.this, AttempterNameActivity.class);
                                intent.putExtra("QUIZ_ID", quizID);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Quiz does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this, "Please Enter a valid Quiz ID", Toast.LENGTH_SHORT).show();
                }

            }
        });

        editQuizCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editQuizCardView.setCardElevation(50.0f);
                String quizID;
                quizID = enterIDEt.getText().toString();

                if(!quizID.equals("")){
                    DocumentReference quizRef = quizzesRef.document(quizID);
                    quizRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                Intent intent = new Intent(MainActivity.this, EditQuizActivity.class);
                                intent.putExtra("QUIZ_ID", quizID);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Quiz does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this, "Please Enter a valid Quiz ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}