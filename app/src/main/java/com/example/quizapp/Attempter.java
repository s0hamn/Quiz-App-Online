package com.example.quizapp;

public class Attempter {

    String attempterName;
    int attempterPoints;

    public Attempter(){

    }
    public Attempter(String attempterName, int attempterPoints) {
        this.attempterName = attempterName;
        this.attempterPoints = attempterPoints;
    }

    public String getAttempterName() {
        return attempterName;
    }

    public void setAttempterName(String attempterName) {
        this.attempterName = attempterName;
    }

    public int getAttempterPoints() {
        return attempterPoints;
    }

    public void setAttempterPoints(int attempterPoints) {
        this.attempterPoints = attempterPoints;
    }
}
