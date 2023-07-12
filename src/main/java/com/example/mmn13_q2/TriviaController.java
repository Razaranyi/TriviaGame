package com.example.mmn13_q2;
/*This class implements the graphic interface of a trivia game
* The user can choose to quit or restart at any time
* when either of the buttons pressed or when the user answered all the question their final score will display
* In case the user answer all the question they will be able to restart
* The user will get the result of the answer once next was clicked, they can change their mind as long as next was not pressed*/
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class TriviaController {

    @FXML
    private Label quest;
    @FXML
    private RadioButton a1;

    @FXML
    private RadioButton a2;

    @FXML
    private RadioButton a3;

    @FXML
    private RadioButton a4;

    @FXML
    private Label ans1;

    @FXML
    private Label ans2;

    @FXML
    private Label ans3;

    @FXML
    private Label ans4;


    @FXML
    private Button newGame;

    @FXML
    private Button next;

    @FXML
    private Button quitBtn;
    private ArrayList<String> currentAnswers;
    private String currentQuestion;
    private String userAnswer;
    private GameLogic gl;



    @FXML
    void a1Pressed(ActionEvent event) {//saves ans1 to the user answer once its toggle button was marked
        userAnswer = ans1.getText();
    }

    @FXML
    void a2Pressed(ActionEvent event) {//saves ans2 to the user answer once its toggle button was marked
        userAnswer = ans2.getText();
    }

    @FXML
    void a3Pressed(ActionEvent event) {//saves ans3 to the user answer once its toggle button was marked
        userAnswer = ans3.getText();
    }

    @FXML
    void a4Pressed(ActionEvent event) {//saves ans4 to the user answer once its toggle button was marked
        userAnswer = ans4.getText();
    }
    @FXML
    void newGamePressed(ActionEvent event) {//restarts the game once pressed. game summary alert shows up before the restart
        conclude();
        restart();
    }

    @FXML
    void nextPressed(ActionEvent event) {//check answer, update points nad present next question once next pressed
        if (!isInput()){
            choseAnswerMsg();
        }else {

            if (gl.isRight(userAnswer)) {
                printRight();
            } else {
                printWrong();
            }

            if (!gl.outOfQuestions()) {
                gl.pickNext();
                currentAnswers.clear();
                insertStrings();
                presentString();
            } else {
                conclude();
                startNewGame();
            }
        }

    }

    @FXML
    void quitPressed(ActionEvent event) {//conclude and quit once pressed
            conclude();
            Platform.exit();
    }

    public void initialize() {//initialize new trivia game from a given text file
        gl = new GameLogic();
        currentAnswers = new ArrayList<>();
        insertStrings();
        presentString();
    }


    private void insertStrings(){ // insert question to the question field and answer into a shuffled array list
        currentQuestion = gl.getQuestionString();
        String s1 = gl.getAnswer();
        String s2 = gl.getWrong1();
        String s3 = gl.getWrong2();
        String s4 = gl.getWrong3();
        currentAnswers.add(s1);
        currentAnswers.add(s2);
        currentAnswers.add(s3);
        currentAnswers.add(s4);
        Collections.shuffle(currentAnswers);

    }
    private void presentString(){//display question and possible answers

        quest.setText(currentQuestion);
        ans1.setText(currentAnswers.get(0));
        ans2.setText(currentAnswers.get(1));
        ans3.setText(currentAnswers.get(2));
        ans4.setText(currentAnswers.get(3));
    }
    private void printRight(){//print result of last question when answer is correct
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type, "");
        alert.setHeaderText("");
        alert.setTitle("Answer result");
        alert.getDialogPane().setContentText("Your answer was: " + userAnswer+ "\nAnd this is correct! Well Done");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            alert.close();
    }

    private void printWrong(){//print result of last question when answer is wrong
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type, "");
        alert.setHeaderText("");
        alert.setTitle("Answer result");
        alert.getDialogPane().setContentText("Your answer was: " + userAnswer +"\nAnd this is wrong! the answer is: " + gl.getAnswer());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
                alert.close();

    }

    private void conclude() {//print game's overall score
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type, "");
        alert.setHeaderText("End Of game");
        alert.setTitle("End Of Game");
        alert.getDialogPane().setContentText("Your final score is: " + gl.getPoints());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            alert.close();
    }

    private void startNewGame(){//asks user to restart game. will restart or quit by their input
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.setHeaderText("Game Ended");
        alert.setTitle("");
        alert.getDialogPane().setContentText("Would you like to play again?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        alert.close();
        if (result.get() == ButtonType.OK){
            restart();
        }
        else {
            Platform.exit();
        }
    }

    private void restart(){//restart the game without constructing new Game logic object
        gl.restart();
        currentAnswers.clear();
        insertStrings();
        presentString();
    }
    private void choseAnswerMsg(){//Handel confusing toggle button feature where you cannot unclear user previous selection
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type, "");
        alert.setHeaderText("");
        alert.setTitle("No answer");
        alert.getDialogPane().setContentText("Please pick an answer");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            alert.close();
    }
    private boolean isInput(){ //is current userAnswer is a possible answer
        return Objects.equals(userAnswer, gl.getAnswer()) || Objects.equals(userAnswer, gl.getWrong1()) || Objects.equals(userAnswer, gl.getWrong2()) || Objects.equals(userAnswer, gl.getWrong3());
    }



}
