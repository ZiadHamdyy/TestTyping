package com.example.hellofx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

public class SolveTest {
    private String currentKeyPress;
    private ObservableList<Node> lettersContainer;
    private static int currentPointer = 0;
    private static int correct = 0;
    private static int wrong = 0;
    private static double wpm = 0;
    private static double accurecy = 0;

    private static long startTime;
    private static long endTime;

    @FXML
    private Label currentLabel;
    @FXML
    private Label WPM;
    @FXML
    private Label Accuracy;


    SolveTest(KeyCode event, ObservableList<Node> lettersContainer) {
        this.lettersContainer = lettersContainer;
        if (currentPointer == 0) {
            this.startTime = System.currentTimeMillis();
            System.out.println("Start of text reached: " + startTime);
        }
        if (currentPointer > lettersContainer.size() - 2) {
            this.endTime = System.currentTimeMillis();
            System.out.println("End of text reached: " + endTime);
            return;
        }
        if (event.toString().equals("SPACE")) {
            this.currentKeyPress = " ";
            this.currentLabel = (Label) lettersContainer.get(currentPointer);
        } else if ((event.toString().equals("BACK_SPACE") || event.toString().equals("DELETE") && currentPointer > 0)) {
            if (currentPointer <= 0) {return;}
            SolveTest.currentPointer--;
            this.currentKeyPress = "back_space";
            this.currentLabel = (Label) lettersContainer.get(currentPointer);
            currentLabel.setOpacity(0.7);
            currentLabel.setStyle("-fx-font-size: 30px; -fx-font-family: 'Monospaced Bold'; -fx-scale-y: 1.1; -fx-text-fill: #f2ede7;");
            currentLabel.setDisable(true);
        } else {
            this.currentKeyPress = event.toString().toLowerCase();
            this.currentLabel = (Label) lettersContainer.get(currentPointer);
        }
    }

    public void solve() {
        if (currentPointer > lettersContainer.size() - 2) {
            this.endTime = System.currentTimeMillis();
            System.out.println("End of text reached: " + endTime);
            return;
        }
        if(currentKeyPress == null){
            return;
        }
        if (currentKeyPress.equals("back_space") && currentPointer >= 0) {
            currentLabel.setStyle("-fx-font-size: 30px; -fx-font-family: 'Monospaced Bold'; -fx-scale-y: 1.1; -fx-text-fill: #f2ede7;");
            return;
        }
        String labelText = currentLabel.getText();
        if (labelText.equals(currentKeyPress)) {
            currentLabel.setStyle("-fx-font-size: 30px; -fx-font-family: 'Monospaced Bold'; -fx-scale-y: 1.1; -fx-text-fill: #f2ede7; -fx-background-color: #3C3D37;");
            currentLabel.setOpacity(1.0);
            System.out.println(true);
            SolveTest.correct++;
        } else {
            currentLabel.setStyle("-fx-font-size: 30px; -fx-font-family: 'Monospaced Bold'; -fx-scale-y: 1.1; -fx-text-fill: red; -fx-background-color: #3C3D37;");
            System.out.println(false);
            SolveTest.wrong++;
        }
        SolveTest.currentPointer++;
    }
    public void reset() {
        currentPointer = 0;
        correct = 0;
        wrong = 0;
        startTime = 0;
        endTime = 0;
    }
    public double[] setResult() {
//        Accuracy.setText("Accuracy: " + correct + " / " + (correct + wrong));
//        WPM.setText("WPM: " + (correct * 100 / (correct + wrong)));
        double[] res = {wpm, accurecy};
        return res;
    }
    public double[] result() {
        System.out.println("Correct: " + correct + " Wrong: " + wrong);
        this.accurecy = correct * 100 / (correct + wrong);
        System.out.println("acc " + accurecy);
        if (startTime != 0 && endTime != 0) {
            long timeTaken = endTime - startTime;
            System.out.println("Time taken: " + (timeTaken / 1000.0) + " seconds");
            int totalCharacters = correct;
            double totalWords = totalCharacters / 5.0;
            double timeInMinutes = timeTaken / 60000.0;
            if (wpm != 0) {return null;}
            this.wpm = totalWords / timeInMinutes;
            System.out.println(wpm + "WPM");
            double[] res = {wpm, accurecy};
            return res;
        } else {
            System.out.println("Timing not available.");
            return null;
        }
    }
}
