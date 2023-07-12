package com.example.mmn13_q2;
/*This class implements The rules and the logic of the trivia game
* adds  or decrease points according to the answer result
* pick next question and erase last question from question bank
* supply question and answers*/

import java.util.Random;

public class GameLogic {
    private QuestionBank qb;
    private int points;
    private Question pickedQuestion;
    private int questionIndex;
    private final int CORRECT_ANSWER = 10;
    private final int WRONG_ANSWER = -5;

    public GameLogic() { // constructor with default file name
        qb = new QuestionBank("trivia.txt");
        points = 0;
        pickedQuestion = pickQuestion();
    }
    public GameLogic(String triviaFileName) {// constructor with any other file name
        qb = new QuestionBank(triviaFileName);
        points = 0;
        pickedQuestion = pickQuestion();
    }

    public boolean isRight(String answer){//check if the answer is right or wrong and updates points accordingly
        if (answer.equals(pickedQuestion.getAnswer())){
            points+=CORRECT_ANSWER;
            qb.removeQuestion(questionIndex);
            return true;
        }
        points += WRONG_ANSWER;
        qb.removeQuestion(questionIndex);
        return false;
    }
    public void pickNext(){ // public method to choose random question from the question bank
       pickedQuestion = pickQuestion();
    }

    private Question pickQuestion(){ // private method to chose random question from the question bank
        Random random = new Random();
        questionIndex= random.nextInt(qb.getQuestionBank().size());
        return qb.getQuestion(questionIndex);
    }
    public void restart(){ //restarts the game
        qb.restart();
        points = 0;
        pickedQuestion = pickQuestion();
    }

    public String getQuestionString(){
        return pickedQuestion.getQuestion();
    }

    public String getAnswer(){
        return  pickedQuestion.getAnswer();
    }

    public  String getWrong1(){
        return pickedQuestion.getWrong1();
    }
    public  String getWrong2(){
        return pickedQuestion.getWrong2();
    }
    public  String getWrong3(){
        return pickedQuestion.getWrong3();
    }



    public int getPoints() {
        return points;
    }
    public boolean outOfQuestions(){ //checks if there are any questions left in the question bank
        return qb.getQuestionBank().isEmpty();
    }
}


