package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class  Controller implements Initializable{

    private int count = 0,id;
    private int index=0;
    private String loc,name1,phno,eadd,packagee;
    private int DaysBooking=4;//4 days 3 nights
    private float[] cost_factor={1.0f ,0.5f};
    private float total=0.0f;
    int p=0,c=0;
    private int[] LocCost={100000,175000,250000};
    ObservableList<String> locationList = FXCollections.observableArrayList("Amsterdam","Bali","Cyprus","Egypt","Japan","London",
            "Paris","Singapore","Thailand");
        @FXML
        private Button next;
        @FXML
        void nextpage(ActionEvent event){
            System.out.println("next button clicked!");
            System.out.println(date.getValue());
            namesync();

            if(count==0){
                page1.setVisible(false);
                page2.setVisible(true);
                page3.setVisible(false);
                count++;
                prev.setDisable(false);
                next.setDisable(true);
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
        private Button calculate;
        @FXML
        void calculation(){
            System.out.println("Calculation is being done!!");
            DaysBooking = Integer.parseInt(days.getText());
            p = Integer.parseInt(adults.getText());
            c = Integer.parseInt(children.getText());
            total = DaysBooking*(p*cost_factor[0]*LocCost[index]+c*cost_factor[1]*LocCost[index]);
            total_amt.setText(String.valueOf(total));
            System.out.println(total);
            next.setDisable(false);
        }

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
        private MenuItem search;

        @FXML
        private Button DelButton;

        @FXML
        private Button SerButton;

        @FXML
        private TextField tid;

        @FXML
        private Label serdis;

        @FXML
        void searchM(){

            page1.setVisible(false);
            page2.setVisible(false);
            page3.setVisible(true);
            finish.setVisible(false);
            again.setVisible(false);
            DelButton.setVisible(false);
            textarea2.setVisible(false);
            SerButton.setVisible(true);
            SerButton.setDisable(false);
            serdis.setVisible(true);
            tid.setVisible(true);
            terms.setVisible(false);
            next.setDisable(true);
            prev.setDisable(true);

        }

        @FXML
        void searchMM(){
            Main m = new Main();
            id = Integer.parseInt(tid.getText());
            String op = m.SanD(id,1);
            tid.setVisible(false);
            next.setVisible(true);
            prev.setVisible(true);
            serdis.setVisible(false);
            SerButton.setVisible(false);
            textarea2.setVisible(true);
            again.setVisible(true);
            again.setDisable(false);
            if(op == "Record is not present"){
                textarea2.setText("Record with  the specified Travel ID is not present in the disk.");
                DelButton.setDisable(true);
            }
            else{
                DelButton.setDisable(false);
                String[] display1= op.split("[|]");
                String   display = "Record found!!" +
                        "\n" +"Booking ID : "+id+"\n"+
                        "Name :"+display1[1]+"\n" +
                        "Email :"+display1[2]+"\n" +
                        "Phone Number:"+display1[3]+"\n" +
                        "Location :"+display1[4]+"\n" +
                        "Package:"+display1[5]+"\n"+
                        "Booking Date:"+display1[9]+"\n" +
                        "Duration :"+display1[6]+" Days & "+(Integer.parseInt(display1[6])+1)+" Nights"+"\n" +
                        "Total Amount : Rs."+String.valueOf(display1[8])+"\n";
                textarea2.setText(display);

            }

        }
        @FXML
        void deleteM(){
            page1.setVisible(false);
            page2.setVisible(false);
            page3.setVisible(true);
            finish.setVisible(false);
            again.setVisible(false);
            DelButton.setDisable(true);
            DelButton.setVisible(true);
            textarea2.setVisible(false);
            SerButton.setVisible(true);
            SerButton.setDisable(false);
            serdis.setVisible(true);
            tid.setVisible(true);
            terms.setVisible(false);
            next.setDisable(true);
            prev.setDisable(true);


        }

        @FXML
        void deleteMM() {
            Main m = new Main();
            id = Integer.parseInt(tid.getText());
            String op = m.SanD(id, 2);
            tid.setVisible(false);
            serdis.setVisible(false);
            SerButton.setVisible(false);
            textarea2.setVisible(true);
            again.setVisible(true);
            again.setDisable(false);
            if (op == "Record is not present") {
                textarea2.setText("Record with  the specified Travel ID is not present in the disk.");
                DelButton.setDisable(true);
            } else if(op == "Record is deleted") {
                    DelButton.setDisable(true);
                    String display = "Record at specified index has been deleted Successfully!";
                textarea2.setText(display);
            }
        }

        @FXML
        private MenuItem delete;
        @FXML
        private Button finish;

        @FXML
        void save(){
            System.out.println("finish button pressed!!");
            finish.setVisible(false);
            String code = T_id.getText();
            id = Integer.parseInt(code);
            name1 = name.getText();
            eadd = email.getText();
            phno = phonenum.getText();
            p = Integer.parseInt(persons.getText());
            total = Float.parseFloat(total_amt.getText());
            String DateSel = String.valueOf(date.getValue());
            String record = id+"|"+name1+"|"+eadd+"|"+phno+"|"+loc+"|"+packagee+"|"+DaysBooking+"|"+p+"|"+String.valueOf(total)+"|"+DateSel;
            System.out.println(record);
            String display;
            display = "      Booking Details:-\n" +
                    "\n" +"Booking ID : "+id+"\n"+
                    "Name :"+name1+"\n" +
                    "Email :"+eadd+"\n" +
                    "Phone Number:"+phno+"\n" +
                    "Location :"+loc+"\n" +
                    "Package:"+packagee+"\n"+
                    "Booking Date:"+DateSel+"\n" +
                    "Duration :"+DaysBooking+" Days & "+String.valueOf(DaysBooking+1)+" Nights"+"\n" +
                    "Total Amount : Rs."+String.valueOf(total)+"\n";
            Main m = new Main();
            int dataBlock= m.searchIndex(id);
            m.storeRecord(dataBlock, id, record);
            //id is the index
            textarea2.setVisible(true);
            again.setDisable(false);
            again.setVisible(true);
            textarea2.setText(display);
        }
        @FXML
        private Button again;

        @FXML
        void again_action(){
            clean();
        }

        @Override@FXML
    public void initialize(URL location, ResourceBundle resources) {
        Location.setItems(locationList);
        economy.setSelected(true);
    }
    public Controller(){}
    public void clean(){
      textarea2.setVisible(false);
      again.setDisable(true);
      again.setVisible(false);
      finish.setVisible(true);
      DelButton.setVisible(false);
      DelButton.setDisable(true);
      serdis.setVisible(false);
      tid.setVisible(false);
      SerButton.setVisible(false);
      SerButton.setDisable(true);
      page3.setVisible(false);
      page2.setVisible(false);
      page1.setVisible(true);
      T_id.clear();
      name.clear();
      persons.clear();
      days.clear();
      booking.clear();
      phonenum.clear();
      adults.clear();
      children.clear();
      total_amt.clear();
      email.clear();
      textarea2.clear();
      terms.setVisible(true);
      terms.setSelected(false);
    }

    @FXML
    void open1(){
        File pdfFile = new File("D:\\IntelliJ IDEA 2018.1.1\\IntellijProject\\MiniProject\\travels.dat");
        open(pdfFile);
    }

    @FXML
    void open2(){
            File newf = new File("D:\\IntelliJ IDEA 2018.1.1\\IntellijProject\\MiniProject\\index.dat");
    }

    void open(File fml){
        if (fml.exists())
        {
            if (Desktop.isDesktopSupported())
            { try
                {Desktop.getDesktop().open(fml);}
              catch (IOException e)
                {e.printStackTrace();}
            }
            else
            {System.out.println("File Format is not supported!!");}
        }
        else
        {System.out.println("File is not exists!");}
    }


    @FXML
    public void Close() {
        System.exit(0);
    }

    @FXML
    public void about(){
            page3.setVisible(true);
            page1.setVisible(false);
            page2.setVisible(false);
            finish.setVisible(false);
            again.setVisible(false);
            next.setVisible(false);
            prev.setVisible(false);
            textarea2.setVisible(true);
            textarea2.setText("TABS or Travel Agency Booking System is a desktop application created with the the help of javafx and file handling java operations.\n" +
                    "It is a MiniProject for the subject FileStructure and is made by the students :\nShashank Singh(1by16is041),& \nShishir Shetty(1by16is043)\n" +
                    "It basically uses three level indexing methodology and is based on ISAM(Indexed Sequential Access Method).As a project it still lacks few " +
                    "capabilities and faces few issues but can be developed into a \nproper standalone application.");
    }
}

