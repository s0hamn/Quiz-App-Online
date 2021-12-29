package com.example.quizapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

public class QuestionAdapter extends FirestoreRecyclerAdapter<Question, QuestionAdapter.QuestionHolder> {

    public QuestionAdapter(@NonNull FirestoreRecyclerOptions<Question> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestionHolder holder, int position, @NonNull Question model) {
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
        holder.correctAnswerTV.setText(model.getCorrectOption().getOptionValue());


    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,
                parent, false);
        return new QuestionHolder(v);
    }

    class QuestionHolder extends RecyclerView.ViewHolder{
        TextView questionText, optionATV,optionBTV,optionCTV,optionDTV, correctAnswerTV;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            optionATV = itemView.findViewById(R.id.optionATV);
            optionBTV = itemView.findViewById(R.id.optionBTV);
            optionCTV = itemView.findViewById(R.id.optionCTV);
            optionDTV = itemView.findViewById(R.id.optionDTV);
            correctAnswerTV = itemView.findViewById(R.id.correctAnswerTV);
        }
    }



}
