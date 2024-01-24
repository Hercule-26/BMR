module com.example.bmr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens g60131.atl.bmr to javafx.fxml;
    exports g60131.atl.bmr.controller;
}