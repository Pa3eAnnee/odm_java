module com.example.odm_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
	requires org.json;
	requires jdk.xml.dom;
	requires com.calendarfx.view;
	requires javafx.graphics;
	requires java.net.http;

    opens com.example.odm_java to javafx.fxml;
    exports com.example.odm_java;
}