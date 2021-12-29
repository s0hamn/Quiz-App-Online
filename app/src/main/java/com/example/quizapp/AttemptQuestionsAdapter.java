package com.example.quizapp;

import static com.example.quizapp.R.color.blue;
import static com.example.quizapp.R.color.light_green;
import static com.example.quizapp.R.color.red;
import static com.example.quizapp.R.color.white;
import static com.example.quizapp.R.color.yellow;
import static com.example.quizapp.R.drawable.et_border;
import static com.example.quizapp.R.drawable.tv_rounded_blue;
import static com.example.quizapp.R.drawable.tv_rounded_green;
import static com.example.quizapp.R.drawable.tv_rounded_red;
import static com.example.quizapp.R.drawable.tv_rounded_yellow;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AttemptQuestionsAdapter extends FirestoreRecyclerAdapter<Question, AttemptQuestionsAdapter.AttemptQuestionsViewHolder> {


    String attempterName = AttemptQuizActivity.attempterName;
    String quizID = AttemptQuizActivity.quizID;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference attemptersRef = db.collection("Quizzes").document(quizID).collection("Attempters");
    DocumentReference attempterDocRef = attemptersRef.document(attempterName);

    int attempterPoints = 0;

    public AttemptQuestionsAdapter(@NonNull FirestoreRecyclerOptions<Question> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull AttemptQuestionsViewHolder holder, int position, @NonNull Question model) {
        holder.questionText.setText(model.getQuestion());

        List<Option> options = model.getOptions();
        Option optionA = options.get(0);
        Option optionB = options.get(1);
        Option optionC = options.get(2);
        Option optionD = options.get(3);

        holder.optionATV.setText(optionA.getOptionValue());
        holder.optionBTV.setText(optionB.getOptionValue());
        holder.optionCTV.setText(optionC.getOptionValue());
        holder.optionDTV.setText(optionD.getOptionValue());

        final Handler handler = new Handler();

        holder.optionATV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                holder.optionATV.setBackgroundResource(blue);
                holder.optionATV.setTextColor(view.getResources().getColor(R.color.white));
                holder.optionATV.setBackground(view.getResources().getDrawable(tv_rounded_blue));
                if (optionA.getOptionValue().equals(model.getCorrectOption().getOptionValue())) {
                    attempterDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Attempter attempter = documentSnapshot.toObject(Attempter.class);
                                attempterPoints+= 10;
                                attempterDocRef.update("attempterPoints", attempterPoints);

                            }
                        }
                    });
                }
                holder.optionATV.setClickable(false);
                holder.optionBTV.setClickable(false);
                holder.optionCTV.setClickable(false);
                holder.optionDTV.setClickable(false);

            }
        });

        holder.optionBTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.optionBTV.setBackgroundResource(red);
                holder.optionBTV.setTextColor(view.getResources().getColor(R.color.white));
                holder.optionBTV.setBackground(view.getResources().getDrawable(tv_rounded_red));
                if (optionB.getOptionValue().equals(model.getCorrectOption().getOptionValue())) {
                    attempterDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Attempter attempter = documentSnapshot.toObject(Attempter.class);
                                attempterPoints += 10;
                                attempterDocRef.update("attempterPoints", attempterPoints);

                            }
                        }
                    });
                }
                holder.optionATV.setClickable(false);
                holder.optionBTV.setClickable(false);
                holder.optionCTV.setClickable(false);
                holder.optionDTV.setClickable(false);

            }
        });

        holder.optionCTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.optionCTV.setBackgroundResource(yellow);
                holder.optionCTV.setTextColor(view.getResources().getColor(R.color.white));
                holder.optionCTV.setBackground(view.getResources().getDrawable(tv_rounded_yellow));

                if (optionC.getOptionValue().equals(model.getCorrectOption().getOptionValue())) {
                    attempterDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Attempter attempter = documentSnapshot.toObject(Attempter.class);
                                attempterPoints += 10;
                                attempterDocRef.update("attempterPoints", attempterPoints);
                            }
                        }
                    });
                }
                holder.optionATV.setClickable(false);
                holder.optionBTV.setClickable(false);
                holder.optionCTV.setClickable(false);
                holder.optionDTV.setClickable(false);

            }
        });

        holder.optionDTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.optionDTV.setBackgroundResource(light_green);
                holder.optionDTV.setTextColor(view.getResources().getColor(R.color.white));
                holder.optionDTV.setBackground(view.getResources().getDrawable(tv_rounded_green));
                if (optionD.getOptionValue().equals(model.getCorrectOption().getOptionValue())) {
                    attempterDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Attempter attempter = documentSnapshot.toObject(Attempter.class);
                                attempterPoints+=10;
                                attempterDocRef.update("attempterPoints", attempterPoints);
                            }
                        }
                    });
                }

                holder.optionATV.setClickable(false);
                holder.optionBTV.setClickable(false);
                holder.optionCTV.setClickable(false);
                holder.optionDTV.setClickable(false);

            }
        });
    }

    @NonNull
    @Override
    public AttemptQuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attempt_question_item,
                parent, false);
        return new AttemptQuestionsViewHolder(v);
    }

    class AttemptQuestionsViewHolder extends RecyclerView.ViewHolder {
        TextView questionText, optionATV, optionBTV, optionCTV, optionDTV;

        public AttemptQuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            optionATV = itemView.findViewById(R.id.optionATV);
            optionBTV = itemView.findViewById(R.id.optionBTV);
            optionCTV = itemView.findViewById(R.id.optionCTV);
            optionDTV = itemView.findViewById(R.id.optionDTV);

        }
    }


}

