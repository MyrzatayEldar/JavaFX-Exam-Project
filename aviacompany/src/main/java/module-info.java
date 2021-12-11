module com.example.aviacompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.example.aviacompany to javafx.fxml;
    exports com.example.aviacompany;
}