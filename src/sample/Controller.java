package sample;
import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.scene.control.ToggleGroup;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.StackPane;

public class  Controller {

        @FXML
        private Button next;

        @FXML
        private Button cancel;

        @FXML
        private StackPane stackid;

        @FXML
        private AnchorPane page1;

        @FXML
        private TextField T_id;

        @FXML
        private TextField name;

        @FXML
        private TextField persons;

        @FXML
        private TextField days;

        @FXML
        private DatePicker date;

        @FXML
        private ChoiceBox<?> location;

        @FXML
        private RadioButton economy;

        @FXML
        private ToggleGroup pack;

        @FXML
        private RadioButton gold;

        @FXML
        private RadioButton platinum;

        @FXML
        private RadioButton terms;

        @FXML
        private TextArea packagedeal;

        @FXML
        private AnchorPane page2;

        @FXML
        private TextField booking;

        @FXML
        private TextField phonenum;

        @FXML
        private TextField email;

        @FXML
        private TextField adults;

        @FXML
        private TextField children;

        @FXML
        private TextArea total_amt;

        @FXML
        private AnchorPane page3;

        @FXML
        private TextArea textarea2;

        @FXML
        private Button finish;

    }