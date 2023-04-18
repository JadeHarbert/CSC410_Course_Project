module edu.alma.teamleft {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jooq;


    exports edu.alma.teamleft;
    opens edu.alma.teamleft to javafx.fxml;
    exports edu.alma.teamleft.tables.records to org.jooq;
}