package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    String quizID;

    public String getQuizPassword() {
        return quizPassword;
    }

    public void setQuizPassword(String quizPassword) {
        this.quizPassword = quizPassword;
    }

    String quizPassword;

    public Quiz(){

    }

    public Quiz(String quizID, String quizPassword) {
        this.quizID = quizID;
        this.quizPassword = quizPassword;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }
}
