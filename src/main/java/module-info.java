module com.example.mmn13_q2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mmn13_q2 to javafx.fxml;
    exports com.example.mmn13_q2;
}