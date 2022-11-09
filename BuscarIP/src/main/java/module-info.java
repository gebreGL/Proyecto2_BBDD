module com.example.buscarip {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires annotations;
    requires java.sql;
    requires java.desktop;
    opens com.example.buscarip to javafx.fxml;
    exports com.example.buscarip;
    exports com.example.buscarip.controller;
    opens com.example.buscarip.controller to javafx.fxml;
    exports com.example.buscarip.model;
    opens com.example.buscarip.model ;
}