module org.example.demo01 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.jfree.jfreechart;
    requires java.desktop;

    opens org.example.demo01 to javafx.fxml;
    exports org.example.demo01;
}