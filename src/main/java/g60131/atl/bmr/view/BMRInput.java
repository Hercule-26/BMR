package g60131.atl.bmr.view;

import g60131.atl.bmr.model.LifeStyle;
import g60131.atl.bmr.model.Sexe;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class BMRInput extends GridPane {

    private TextField tfTaille;
    private TextField tfPoid;
    private TextField tfAge;
    private RadioButton femme;
    private RadioButton homme;
    private ToggleGroup toggleGroup;
    private ChoiceBox choiceBox;

    public BMRInput() {

        this.setPadding(new Insets(0, 0, 0, 10));
        this.setVgap(2);
        // Données
        Label donnees = new Label("Données");
        donnees.setUnderline(true);
        this.add(donnees, 0, 0);

        // Taille
        Label taille = new Label("Taille (cm)");
        tfTaille = new TextField();
        tfTaille.setPromptText("Taille en cm"); // Texte en transparent
        this.add(taille, 0, 5);
        this.add(tfTaille, 1, 5);

        // Poid
        Label poid = new Label("Poind (kg)");
        tfPoid = new TextField();
        tfPoid.setPromptText("Poid en kg"); // Texte en transparent
        this.add(poid, 0, 10);
        this.add(tfPoid, 1, 10);

        // Age
        Label age = new Label("Age (années)");
        tfAge = new TextField();
        tfAge.setPromptText("Age en années"); // Texte en transparent
        this.add(age, 0, 15);
        this.add(tfAge, 1, 15);

        // -------------------------------------------------------------
        // Faire en sorte de pouvoir écrire uniquement des entier dans les zones de textes
        tfPoid.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            onlyNumber(e);
        });
        tfAge.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            onlyNumber(e);
        });
        tfTaille.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            onlyNumber(e);
        });
        // -------------------------------------------------------------

        // Sexe
        Label sexe = new Label("Sexe");
        femme = new RadioButton("Femme");
        homme = new RadioButton("Homme");
        toggleGroup = new ToggleGroup(); // Permet de sélectionner qu'un seul radio bouton
        femme.setToggleGroup(toggleGroup);
        homme.setToggleGroup(toggleGroup);
        HBox hommeFemme = new HBox(femme, homme);
        homme.setPadding(new Insets(0, 0, 0, 5));
        this.add(sexe, 0, 20);
        this.add(hommeFemme, 1, 20);

        // Style de vie
        Label styleDeVie = new Label("Style de vie");
        choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Sédantaire", "Peu actif", "Actif", "Fort actif", "Extrêmement actif");
        choiceBox.setMinWidth(150); // La longueur du choiceBox
        this.add(styleDeVie, 0, 25);
        this.add(choiceBox, 1, 25);
    }

    public void clear() {
        tfAge.clear();
        tfTaille.clear();
        tfPoid.clear();
        homme.setSelected(false);
        femme.setSelected(false);
        choiceBox.setValue(null);
    }

    public double getTaille() {
        double taille = Double.parseDouble(tfTaille.getText());
        return taille;
    }
    public int getAge() {
        int age = Integer.parseInt(tfAge.getText());
        return age;
    }
    public double getPoid() {
        double poids = Double.parseDouble(tfPoid.getText());
        return poids;
    }

    public Sexe getSexe() {
        if (toggleGroup.getToggles().get(0).isSelected()) { // Si c'est la case homme qui à été selectionner
            return Sexe.Homme;
        } else if (toggleGroup.getToggles().get(1).isSelected()) {
            return Sexe.Femme;
        } else {
            return null;
        }
    }
    public LifeStyle getLifeStyle() {
        switch (choiceBox.getValue().toString()) {
            case "Sédantaire" : {
                return LifeStyle.SEDENTAIRE;
            }
            case "Peu actif" : {
                return LifeStyle.PEU_ACTIF;
            }
            case "Actif" : {
                return LifeStyle.ACTIF;
            }
            case "Fort actif" : {
                return LifeStyle.FORT_ACTIF;
            }
            case "Extrêment actif" : {
                return LifeStyle.EXTREMEMENT_ACTIF;
            }
            default: {
                return null;
            }
        }
    }

    private void onlyNumber(KeyEvent e){
        try {
            Integer.parseInt(e.getCharacter());
        } catch (NumberFormatException numberFormatException) {
            e.consume();
        }
    }
}
