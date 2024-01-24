package g60131.atl.bmr.model;

import g60131.atl.bmr.util.Observer;
import g60131.atl.bmr.util.Observable;
import javafx.scene.control.Alert;

import java.util.LinkedList;
import java.util.List;

public class Person implements Observable {
    private List<Observer> observers = new LinkedList<>();
    private double taille;
    private double poids;
    private int age;
    private Sexe sexe;
    private LifeStyle lifeStyle;
    private double bmrCalcul;

    private double calculeCalories;

    public Person() {
     }

    public void set(double taille, double poids, int age, Sexe sexe, LifeStyle lifeStyle) {
        this.taille = taille;
        this.poids = poids;
        this.age = age;
        this.sexe = sexe;
        this.lifeStyle = lifeStyle;
        notifyObserver();
    }

    @Override
    public void register(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    @Override
    public void unregister(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    private void notifyObserver() {
        for (Observer obs: observers) {
            obs.update();
        }
    }

    public double calculeCalories() {
        double bmr = calculBmr();
        switch (lifeStyle) {
            case SEDENTAIRE : {
                calculeCalories = bmr * 1.2;
                return calculeCalories;
            }
            case PEU_ACTIF : {
                calculeCalories = bmr * 1.375;
                return calculeCalories;
            }
            case ACTIF : {
                calculeCalories = bmr * 1.55;
                return calculeCalories;
            }
            case FORT_ACTIF : {
                calculeCalories = bmr * 1.725;
                return calculeCalories;
            }
            case EXTREMEMENT_ACTIF : {
                calculeCalories = bmr * 1.9;
                return calculeCalories;
            }
            default: throw new IllegalArgumentException();
        }
    }

    public double calculBmr () {
        if (poids == 0 || taille == 0 || age == 0) { // Condition si les zone de texte sont à 0
            alert(); // Afficher un popUp
        }
        switch (sexe) {
            case Homme : {
                bmrCalcul = (13.7 * poids) + (5 * taille) - (6.8 * age) + 66;
                return bmrCalcul;
            }
            case Femme : {
                bmrCalcul = (9.6 * poids) + (1.8 * taille) - (4.7 * age) + 655;
                return bmrCalcul;
            }
            default : {
                throw new IllegalArgumentException();
            }
        }
    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("valeurs incorrecte");
        alert.setContentText("Veuillez entrez des valeurs supérieur à zéro");
        alert.show();
        throw new IllegalArgumentException();
    }

}
