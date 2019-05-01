package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.scene.control.ToggleGroup;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class  Controller implements Initializable{

    private int count = 0,id;
    private int index=0;
    private String loc,name1,phno,eadd,packagee;
    private int DaysBooking=4;//4 days 3 nights
    private float[] cost_factor={1.0f ,0.5f};
    private float total=0.0f;
    private int[] LocCost={100000,175000,250000};
    ObservableList<String> locationList = FXCollections.observableArrayList("Amsterdam","Bali","Cyprus","Egypt","Japan","London",
            "Paris","Singapore","Thailand");
        @FXML
        private Button next;
        @FXML
        void nextpage(ActionEvent event){
            System.out.println("next button clicked!");
            namesync();
            if(count==0){
                page1.setVisible(false);
                page2.setVisible(true);
                page3.setVisible(false);
                count++;
                prev.setDisable(false);
            }
            else if(count==1){
                page1.setVisible(false);
                page2.setVisible(false);
                page3.setVisible(true);
                count++;
                prev.setDisable(false);
            }

        }

    @FXML
        private Button prev;

        @FXML
         void prevpage(ActionEvent event){
            System.out.println("previous button clicked!");
            if(count==1){
                page1.setVisible(true);
                page2.setVisible(false);
                page3.setVisible(false);
                count--;
                prev.setDisable(true);
            }
            else if(count==2){
                page1.setVisible(false);
                page2.setVisible(true);
                page3.setVisible(false);
                count--;
                prev.setDisable(false);
            }

        }
        @FXML
        private StackPane stackid;

        @FXML
        private AnchorPane page1;

        @FXML
        private TextField T_id;

        @FXML
        private TextField name;
        @FXML
        void namesync(){
                System.out.println(name.getText());
                booking.setText(name.getText());
                booking.setEditable(false);
            }


        @FXML
        private TextField persons;

        @FXML
        private TextField days;

        @FXML
        private DatePicker date;

        @FXML
        private ChoiceBox Location;

        @FXML
        void getLocation(){
            loc = Location.getValue().toString();
            System.out.println(loc);
        }

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
        void nextcontrol(ActionEvent event){
            if(terms.isSelected()){
                next.setDisable(false);
                finish.setDisable(false);
            }
            else{
                next.setDisable(true);
                finish.setDisable(true);
                count=1;
                prevpage(event);
            }

        }

        @FXML
        private TextArea packagedeal;

        @FXML
        void TextChange(){
            if(economy.isSelected()){
                index=0;
                packagee = "Economy";
                packagedeal.setText("        COST :Rs. 100000\n" +
                                    "            per person\n"+
                                    "\nIncludes:-\n" +
                                    "Flight tickets,\n" +
                                    "Airport transfers,\n" +
                                    "Accommodation at 3 star hotel,\n" +
                                    "4 Days & 5 Nights\n"+
                                    "double sharing,\n" +
                                    "Attractions,\n" +
                                    "Buffet breakfast & Dinner,\n" +
                                    "Tour bus to and from the sights");
            }
            else if(gold.isSelected()){
                packagee="Gold";
                index=1;
                packagedeal.setText("        COST :Rs. 175000\n" +
                                    "            per person\n"+
                                    "\nIncludes:-\n" +
                                    "Flight tickets,\n" +
                                    "Airport transfers,\n" +
                                    "Accommodation at 3 star hotel,\n" +
                                    "4 Days & 5 Nights\n"+
                                    "Deluxe rooms in hotel\n"+
                                    "Lunch inclusive on all the days\n" +
                                    "Attractions,\n" +
                                    "Buffet breakfast & Dinner,\n" +
                                    "Tour bus to and from the sights");
            }
            else if(platinum.isSelected()){
                packagee = "platinum";
                index=2;
                packagedeal.setText("        COST :Rs. 250000\n" +
                                    "            per person\n"+
                                    "\nIncludes:-\n" +
                                    "Business class flight tickets\n" +
                                    "Airport transfers,\n" +
                                    "Accommodation at 4 star hotel,\n" +
                                    "4 Days & 5 Nights\n" +
                                    "Suites room at hotel\n"+
                                    "Lunch inclusive on all the days\n" +
                                    "Attractions,\n" +
                                    "Buffet breakfast & Dinner,\n" +
                                    "chauffeur driven car provided");
            }
            }



        @FXML
        private AnchorPane page2;

        @FXML
        private TextField booking;
        @FXML
        void getbooking(){
            booking.setText(name.getText());
        }

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

        @FXML
        void save(){
            String code = T_id.getText();
            id = Integer.parseInt(code);
            name1 = name.getText();
            eadd = email.getText();
            phno = phonenum.getText();
            //loc has location, packagee has package
            DaysBooking = Integer.parseInt(days.getText());
            //total =


        }

        @Override@FXML
    public void initialize(URL location, ResourceBundle resources) {
        Location.setItems(locationList);
        economy.setSelected(true);
    }
    public Controller(){}

}