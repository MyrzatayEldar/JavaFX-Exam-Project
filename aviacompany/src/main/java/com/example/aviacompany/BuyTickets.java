package com.example.aviacompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Random;

public class BuyTickets {
    ObservableList<String> genders = FXCollections.observableArrayList("Male","Female","Other");
    ObservableList<String> howMany = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> classes = FXCollections.observableArrayList("эконом","комфорт","бизнес");
    ObservableList<String> yOn = FXCollections.observableArrayList("Да","Нет");
    Random rand = new Random();
    int low = 80;
    int high = 100;
    private int money;
    private int economPrice = 1;
    private int comfortPrice = 2;
    private int businessPrice = 4;
    private int priceForTicket = rand.nextInt(high-low) + low;
    private int totalPrice;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;


    @FXML
    private Button payB,toMainPB, getTicket,toMainB;

    @FXML
    private Label orNumL,nameInfo,IINLabel, fromLabel,toLabel,classLabel, flydateLabel,RflydateLabel,howmanyLabel,timeOfPay;
    @FXML
    private ChoiceBox genderBox,count,classBox,yesOrNoBox;

    @FXML
    private TextField nameField, surnameField, patrField,
            natioField, docNumber,IIN,fromF,toF, itogoField;

    @FXML
    private DatePicker birthday, godenDO, flyDate, returnFlyDate;

    @FXML
    private CheckBox economCH, comfortCH, businessCH;

    @FXML
    private RadioButton yesRB, noRB;

    @FXML
    public void initialize(){
        genderBox.setValue("Choose your gender.");
        genderBox.setItems(genders);
        count.setValue("How many?");
        count.setItems(howMany);
        classBox.setValue("Class?");
        classBox.setItems(classes);
        yesOrNoBox.setValue("Yes or NO");
        yesOrNoBox.setItems(yOn);
    }

    public void save(ActionEvent event){

        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();

        String name = nameField.getText();
        String surname = surnameField.getText();
        String patr = patrField.getText();
        String gender = (String)genderBox.getValue();
        LocalDate birth = birthday.getValue();
        String natio = natioField.getText();
        String docN = docNumber.getText();
        LocalDate gDo = godenDO.getValue();
        String iin = IIN.getText();

        String query = "INSERT INTO `usersinfo`(`userName`, `userSurname`, " +
                "`userPatronymic`, `userGender`, " +
                "`userBirthday`, `userNationality`, " +
                "`user_doc_number`, `user_goden_do`," +
                " `userIIN`) VALUES(\""+ name+ "\",\""+surname+"\",\""+patr+"\",\""+gender+"\",\""+birth+"\",\""+natio+"\",\""+docN+"\",\""+gDo+"\",\""+iin+"\");";


        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Success! in BuyTickets insert1");
            statement.close();
            nameField.clear();
            surnameField.clear();
            patrField.clear();
            genderBox.setValue("Choose your gender.");
            birthday.getEditor().clear();
            natioField.clear();
            docNumber.clear();
            godenDO.getEditor().clear();
            IIN.clear();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void getPriceAction(ActionEvent event) throws SQLException{
        int howmany = Integer.parseInt(count.getValue().toString());
        String valueOfClass = (String)classBox.getValue();
        String yesOrNo = (String)yesOrNoBox.getValue();
        if(valueOfClass=="эконом"&&yesOrNo=="Да"){
            totalPrice = priceForTicket*economPrice*howmany*2;
        }
        else if(valueOfClass=="эконом"&&yesOrNo=="Нет"){
            totalPrice = priceForTicket*economPrice*howmany;
        }
        else if(valueOfClass=="комфорт"&&yesOrNo=="Нет"){
            totalPrice = priceForTicket*comfortPrice*howmany;
        }
        else if(valueOfClass=="комфорт"&&yesOrNo=="Да"){
            totalPrice = priceForTicket*comfortPrice*howmany*2;
        }

        else if(valueOfClass=="бизнес"&&yesOrNo=="Нет"){
            totalPrice = priceForTicket*businessPrice*howmany;
        }
        else if(valueOfClass=="бизнес"&&yesOrNo=="Да"){
            totalPrice = priceForTicket*businessPrice*howmany*2;
        }

        itogoField.setText(String.valueOf(totalPrice));
    }



    @FXML
    public void payAction(ActionEvent event) throws SQLException {
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();
        String from = fromF.getText();
        String to = toF.getText();
        LocalDate flyD = flyDate.getValue();
        LocalDate rFlyD = returnFlyDate.getValue();
        String valueOfClass = (String)classBox.getValue();
        String yesOrNo = (String)yesOrNoBox.getValue();
        String howmany = (String) count.getValue();
        String itogo = itogoField.getText();
        String query1 = "select userMoney from users where userID=(select max(userID) from users);";

        Statement statement1 = connection.createStatement();
        ResultSet queryOutput = statement1.executeQuery(query1);
        while(queryOutput.next()) {
            money = Integer.parseInt(queryOutput.getString("userMoney"));
        }
        System.out.println(money);


        String query2 = "INSERT INTO `orders`(`fromOrder`, `toOrder`," +
                " `dayOrder`, `orderClass`, " +
                "`toBack`, `backFly`, `howmany`, `orderBill`) " +
                "VALUES (\"" + from+   "\",\""+ to+ "\",\""+ flyD+ "\",\""+ valueOfClass+"\",\""+yesOrNo+"\",\""+rFlyD+"\",\""+howmany+"\",\""+itogo+"\");";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query2);
            System.out.println("Success! in BuyTickets insert2");
            fromF.clear();
            toF.clear();
            flyDate.getEditor().clear();
            returnFlyDate.getEditor().clear();
            count.setValue("How many?");
            classBox.setValue("Class?");
            yesOrNoBox.setValue("Yes or NO");
            itogoField.clear();
            int newMoney = money-Integer.parseInt(itogo);
            String query3 = "update users set userMoney="+newMoney+" where userID=(select `a`.id from (SELECT max(userID) as id from users) as `a`)";
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate(query3);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            System.out.println("Success! in BuyTickets insert3");
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void getTicketAction(ActionEvent event) throws IOException,SQLException{
        timeOfPay.setText("Дата и время заказа: "+now.toString());
        int lowForN = 100000;
        int highForN = 999999;
        int orderNumber = rand.nextInt(highForN-lowForN) + lowForN;
        orNumL.setText("Заказ №" + String.valueOf(orderNumber));
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        String query1 = "select userName,userSurname,userPatronymic,userIIN from usersinfo where userID=(select max(userId) from usersinfo);";
        String query2 = "select fromOrder, toOrder, orderClass,dayOrder, backFly, howmany from orders where orderId=(select max(orderId) from orders);";

        ResultSet queryOutput = statement1.executeQuery(query1);
        ResultSet queryOutput1 = statement2.executeQuery(query2);
        while(queryOutput.next()){
            nameInfo.setText(queryOutput.getString("userSurname")+" "+queryOutput.getString("userName")+" "+queryOutput.getString("userPatronymic"));
            IINLabel.setText(queryOutput.getString("userIIN"));
        }
        while(queryOutput1.next()){
            fromLabel.setText(queryOutput1.getString("fromOrder"));
            toLabel.setText(queryOutput1.getString("toOrder"));
            classLabel.setText(queryOutput1.getString("orderClass"));
            flydateLabel.setText(queryOutput1.getString("dayOrder"));
            RflydateLabel.setText(queryOutput1.getString("backFly"));
            howmanyLabel.setText(queryOutput1.getString("howmany"));
        }


    }
    @FXML
    public void toMainAction(ActionEvent event)throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Наши услуги.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("mainIcon.png")));
        stage.setScene(scene);
        stage.show();
        toMainB.getScene().getWindow().hide();
    }


    @FXML
    public void toMainAction1(ActionEvent event)throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Наши услуги.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("mainIcon.png")));
        stage.setScene(scene);
        stage.show();
        toMainPB.getScene().getWindow().hide();
    }
}
