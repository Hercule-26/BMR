package g60131.atl.bmr.controller;

import g60131.atl.bmr.model.Person;
import g60131.atl.bmr.view.BMRInput;
import g60131.atl.bmr.util.Observer;
import g60131.atl.bmr.model.LifeStyle;
import g60131.atl.bmr.model.Sexe;
import g60131.atl.bmr.view.BMRResult;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application implements Observer {
    private VBox root = new VBox();
    private HBox bigHbox = new HBox();
    private BMRInput inputGrid;
    private BMRResult outputGrid;
    private Person person;
    private Menu menu;

    public static void main(String[] args) {
        // Lancer JavaFx
        launch();
    }

    @Override
    public void start(Stage stage) {
        person = new Person();
        initMenu(stage);
        initInput();
        initResult();
        initButtons();
        person.register(this);

        // Inset : HAUT | DROITE | BAS | GAUCHE
        root.setSpacing(5);
        root.setPadding(new Insets(15, 0, 0, 15));

        var scene = new Scene(root, 480, 320);
        stage.setTitle("Calcule du BMR"); // Titre du programme au lancement
        stage.setResizable(false); // Empêcher de modifier la taille du programme
        stage.setScene(scene);
        stage.show();
    }

    // ============================================================================================================

    private void initMenu(Stage stage) {
        menu = new Menu("Menu");
        MenuItem menuExitItem = new MenuItem("Exit");
        menu.getItems().add(menuExitItem);

        MenuBar menuBar = new MenuBar(menu);
        root.getChildren().add(menuBar);

        menuExitItem.setOnAction(event -> {
            person.unregister(this);
            stage.close();
        });
    }

    private void initInput() {
        inputGrid = new BMRInput();
        bigHbox.setPadding(new Insets(0, 0, 15, 10));
        bigHbox.getChildren().addAll(inputGrid);
    }

    private void initResult() {
        outputGrid = new BMRResult();
        outputGrid.setPadding(new Insets(0, 0, 0, 15));
        outputGrid.setVgap(2);
        bigHbox.getChildren().add(outputGrid);
        root.getChildren().add(bigHbox);
    }

    private void initButtons() {
        // BMR
        Button btnBMR = new Button("Calcule du BMR");
        btnBMR.setMinWidth(440); btnBMR.setMinHeight(25);
        btnBMR.setPadding(new Insets(0, 0, 0, 10));

        Button btnClear = new Button("Clear");
        btnClear.setMinWidth(440); btnClear.setMinHeight(25);
        btnClear.setPadding(new Insets(0, 0, 0, 10));

        root.getChildren().addAll(btnBMR, btnClear);

        // Event BMR Button
        btnBMR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double taille = inputGrid.getTaille();
                double poid = inputGrid.getPoid();
                int age = inputGrid.getAge();
                Sexe sexe = inputGrid.getSexe();
                LifeStyle lifeStyle = inputGrid.getLifeStyle();

                person.set(taille, poid, age, sexe, lifeStyle);            }
        });

        // Clear les zones de textes
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                inputGrid.clear();
                outputGrid.clear();
            }
        });
    }

    @Override
    public void update() {
        try {
            double bmr = person.calculBmr();
            double calories = person.calculeCalories();
            outputGrid.setBMR(bmr);
            outputGrid.setCalories(calories);
        } catch (Exception e) {
            outputGrid.setFailed();
        }
    }
}