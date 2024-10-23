module com.archery.osugrabber {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires org.jetbrains.annotations;
    requires java.desktop;

    opens com.archery.osugrabber to javafx.fxml;
    exports com.archery.osugrabber;
    exports com.archery.osugrabber.controller;
    exports com.archery.osugrabber.enums to com.google.gson;
    opens com.archery.osugrabber.controller to javafx.fxml;
    exports com.archery.osugrabber.osu;
    opens com.archery.osugrabber.osu to javafx.fxml;
}