module com.c195_software_ii__advanced_java_concepts_pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.c195_software_ii__advanced_java_concepts_pa to javafx.fxml;
    opens com.c195_software_ii__advanced_java_concepts_pa.Models to javafx.base;
    exports com.c195_software_ii__advanced_java_concepts_pa to javafx.fxml, javafx.graphics;
}