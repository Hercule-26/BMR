package g60131.atl.bmr.view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class BMRResult extends GridPane {

    private Label tfBmr;
    private TextField tfCalories;
    public BMRResult() {
        // Résultats
        Label resultats = new Label("Résultats");
        resultats.setUnderline(true);
        this.add(resultats, 0, 0);

        // BMR
        Label bmr = new Label("BMR");
        tfBmr = new Label();
        this.add(bmr, 0, 5);
        this.add(tfBmr ,1, 5);

        // Calories
        Label calories = new Label("Calories");
        tfCalories = new TextField();
        tfCalories.setEditable(false); // Faire en sorte de ne pas pouvoir écrire
        tfCalories.setPromptText("Dépense en calories");
        this.add(calories, 0, 10);
        this.add(tfCalories, 1, 10);
    }

    public void setBMR(double bmr) {
        tfBmr.setStyle("-fx-text-fill: black;");
        tfBmr.setText(bmr + "");
    }
    public void setCalories(double calories) {
        tfCalories.setStyle("-fx-text-fill: black;");
        tfCalories.setText(calories + "");
    }
    public void clear() {
        tfBmr.setText("");
        tfCalories.clear();
    }

    public void setFailed() {
        tfBmr.setStyle("-fx-text-fill: red;");
        tfCalories.setStyle("-fx-text-fill: red;");
        tfBmr.setText("Failed !");
        tfCalories.setText("Failed !");
    }
}
