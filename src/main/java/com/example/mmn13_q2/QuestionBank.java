package com.example.mmn13_q2;
/*This class stores all questions from a given file (in a given format of question\nanswer\nwrong1\nwrong 2\nwrong3
* Exception will throw in case of wrong number of lines in the file or wrong file name/location
* the class will the input in arrayList of Question objects which each index  store each line as a string according to its properties (question, correct/wrong answer) */
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank {
    private ArrayList<Question> questionBank;
    private ArrayList<Question> originalQuestionBank;
    private final int NUMBER_OF_ROWS_PER_QUESTION = 5;

    public QuestionBank(String triviaName)  {
        questionBank = new ArrayList<>();
        try {
            readFile(triviaName);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong file structure. \nPlease enter a question followed by 4 possible answers");
            alert.showAndWait();
            System.exit(0);        }
    }

    private void readFile(String triviaName) throws IOException {
        Scanner input = null;
        try {
            input = new Scanner(new File(triviaName));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Could not open or find file");
            alert.showAndWait();
            System.exit(0);
            return;
        }

        int count = 0;
        boolean flag = false;
        String q = "", a = "", w1 = "", w2 = "", w3 = "";

        while (input.hasNextLine()) {
            int inputIndex = count % 5;

            switch (inputIndex) {
                case 0 -> q = input.nextLine();
                case 1 -> a = input.nextLine();
                case 2 -> w1 = input.nextLine();
                case 3 -> w2 = input.nextLine();
                default -> w3 = input.nextLine();
            }
            count++;
            if (count == NUMBER_OF_ROWS_PER_QUESTION){
                flag = true;
            }

            if (flag && count%NUMBER_OF_ROWS_PER_QUESTION == 0) {
                Question question = new Question(q, a, w1, w2, w3);
                questionBank.add(question);
            }
        }//all file read
        if (count%NUMBER_OF_ROWS_PER_QUESTION != 0){
            throw new IOException();
        }
        input.close();
        originalQuestionBank = new ArrayList<>(questionBank);


    }

    public ArrayList<Question> getQuestionBank() {
        return questionBank;
    }
    public void restart() {
        questionBank = new ArrayList<>(originalQuestionBank);
    }
    public Question getQuestion(int  i){
        return questionBank.get(i);
    }


    public void removeQuestion(int i){
        questionBank.remove(i);
    }


}


