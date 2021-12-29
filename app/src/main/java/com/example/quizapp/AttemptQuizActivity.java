package com.example.quizapp;

import static com.example.quizapp.R.id.progressDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class AttemptQuizActivity extends AppCompatActivity {
    TextView quizIDText;

    public static String attempterName;
    public static String quizID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference questionsRef;
    private AttemptQuestionsAdapter adapter;

    FloatingActionButton submitButton;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_quiz);

        quizIDText = findViewById(R.id.quizID);
        submitButton = findViewById(R.id.submitButton);

        Intent intent = getIntent();
        quizID = intent.getStringExtra("QUIZ_ID");
        attempterName = intent.getStringExtra("ATTEMPTER_NAME");

        quizIDText.setText(quizID);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Handler handler = new Handler();

//                progressDialog = new ProgressDialog(AttemptQuizActivity.this);
//
//                progressDialog.setContentView(R.id.progressDialog);
//                progressDialog.show();
//
//                progressDialog.setMessage("Loading....");
//
//                progressDialog.getWindow().setBackgroundDrawableResource(
//                        android.R.color.transparent
//                );

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        progressDialog.dismiss();
                        Intent intent1 = new Intent(AttemptQuizActivity.this, ResultActivity.class);
                        intent1.putExtra("QUIZ_ID", quizID);
                        intent1.putExtra("ATTEMPTER_NAME", attempterName);
                        startActivity(intent1);
                        finish();
                    }
                }, 500);



            }
        });



        questionsRef = db.collection("Quizzes").document(quizID).collection("Questions");

        setUpRecyclerView();






    }

    private void setUpRecyclerView() {

        Query query = questionsRef;
        FirestoreRecyclerOptions<Question> options = new FirestoreRecyclerOptions.Builder<Question>()
                .setQuery(query, Question.class)
                .build();

        adapter = new AttemptQuestionsAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}