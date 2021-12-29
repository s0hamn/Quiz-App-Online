package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class Question {
    String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    List<Option> options = new ArrayList<>();
    Option correctOption;

    public Question(){

    }

    public Question(String question, List<Option> options, Option correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Option getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Option correctOption) {
        this.correctOption = correctOption;
    }
}
