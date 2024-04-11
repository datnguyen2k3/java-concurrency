module dat.nguyen.concurrency {
    requires javafx.controls;
    requires javafx.fxml;


    opens dat.nguyen.concurrency to javafx.fxml;
    exports dat.nguyen.concurrency;
}