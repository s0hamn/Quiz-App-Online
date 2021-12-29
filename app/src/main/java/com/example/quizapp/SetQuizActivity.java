package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SetQuizActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference questionsColRef;
    private QuestionAdapter adapter;

    TextView quizIDText;

    FloatingActionButton addQuestionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        

        quizIDText = findViewById(R.id.quizID);
        addQuestionButton = findViewById(R.id.addQuestionButton);

        Intent intent = getIntent();
        String quizID = intent.getStringExtra("QUIZ_ID");

        quizIDText.setText(quizID);

        questionsColRef = db.collection("Quizzes").document(quizID).collection("Questions");
        
        setUpRecyclerView();

        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SetQuizActivity.this, AddQuestionActivity.class);
                intent1.putExtra("QUIZ_ID", quizID);
                startActivity(intent1);
            }
        });




    }

    private void setUpRecyclerView() {
        Query query = questionsColRef;
        FirestoreRecyclerOptions<Question> options = new FirestoreRecyclerOptions.Builder<Question>()
                .setQuery(query, Question.class)
                .build();

        adapter = new QuestionAdapter(options);

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