package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditQuizActivity extends AppCompatActivity {

    EditText enterPasswordEt;
    CardView enterPasswordCardView;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference quizzesRef = db.collection("Quizzes");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);

        enterPasswordCardView = findViewById(R.id.enterPasswordCardView);
        enterPasswordEt = findViewById(R.id.enterPasswordEt);

        Intent i = getIntent();
        String quizID = i.getStringExtra("QUIZ_ID");

        enterPasswordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = enterPasswordEt.getText().toString().trim();
                DocumentReference quizRef = quizzesRef.document(quizID);

                quizRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Quiz quiz = documentSnapshot.toObject(Quiz.class);
                        if(quiz.getQuizPassword().equals(password)){
                            Toast.makeText(EditQuizActivity.this, "Password Confirmed", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditQuizActivity.this , SetQuizActivity.class);
                            intent.putExtra("QUIZ_ID", quizID);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(EditQuizActivity.this, "Wrong Password, Please Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}