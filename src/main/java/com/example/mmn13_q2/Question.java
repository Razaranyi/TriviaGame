package com.example.mmn13_q2;
/*the class implements the question object by the given structure - question, answer and three wrong answers*/
public class Question {
    private String question,answer,wrong1,wrong2,wrong3;

    public Question(String question, String answer, String wrong1, String wrong2, String wrong3) { 
        this.question = question;
        this.answer = answer;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.wrong3 = wrong3;


    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getWrong1() {
        return wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public String getWrong3() {
        return wrong3;
    }
}
