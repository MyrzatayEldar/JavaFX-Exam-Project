package com.example.aviacompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Converter {

    ObservableList<String> currencies = FXCollections.observableArrayList(
            "евро",
            "тенге",
            "рубли",
            "лира",
            "сом",
            "крона",
            "фунт стерлинг",
            "жэньменьби",
            "рупия",
            "франк");

    @FXML
    private TextField iHave,iGet;

    double dToEuro = 0.88;
    double dToTenge = 435.84;
    double dToRubles = 73.45;
    double dToLira = 13.88;
    double dToSom = 10822.0;
    double dToCronas = 22.40;
    double dToPound = 0.75;
    double dToUan = 6.37;
    double dToRupia = 75.79;
    double dToFrank = 0.92;

    private double totalValue;
    @FXML
    private CheckBox moneyCH;

    @FXML
    private Button logoutB;
    @FXML
    private Label profileL, balanceL;
    @FXML
    private ChoiceBox exchange;

    private int myMoney;
    @FXML
    public void initialize() throws SQLException {
        exchange.setValue("Выберите валюту.");
        exchange.setItems(currencies);
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();
        Statement statement1 = connection.createStatement();


        String sql1 = "SELECT userName, userMoney FROM users WHERE userID = (SELECT MAX(userID) FROM users)";


        ResultSet queryOutput1 = statement1.executeQuery(sql1);

        while(queryOutput1.next()) {
            profileL.setText(queryOutput1.getString("userName"));
            balanceL.setText(queryOutput1.getString("userMoney")+"$");
        }
    }

    @FXML
    public void converting(ActionEvent event){
        String value = (String) exchange.getValue();
        if(value=="евро"){
            totalValue = myMoney * dToEuro;
            iGet.setText(String.valueOf(totalValue)+ " евро.");
        }
        else if(value=="тенге"){
            totalValue = myMoney * dToTenge;
            iGet.setText(String.valueOf(totalValue)+ " тенге.");
        }
        else if(value=="рубли"){
            totalValue = myMoney * dToRubles;
            iGet.setText(String.valueOf(totalValue)+ " рублей.");
        }
        else if(value=="лира"){
            totalValue = myMoney * dToLira;
            iGet.setText(String.valueOf(totalValue)+ " лира.");
        }
        else if(value=="сом"){
            totalValue = myMoney * dToSom;
            iGet.setText(String.valueOf(totalValue)+ " сом.");
        }

        else if(value=="крона"){
            totalValue = myMoney * dToCronas;
            iGet.setText(String.valueOf(totalValue)+ "крон.");
        }
        else if(value=="фунт стерлинг"){
            totalValue = myMoney * dToPound;
            iGet.setText(String.valueOf(totalValue)+ " фунтов стерлингов.");
        }
        else if(value=="жэньменьби"){
            totalValue = myMoney * dToUan;
            iGet.setText(String.valueOf(totalValue)+ " жэньменьби.");
        }
        else if(value=="рупия"){
            totalValue = myMoney * dToRupia;
            iGet.setText(String.valueOf(totalValue) + " рупии.");
        }
        else if(value=="франк"){
            totalValue = myMoney * dToFrank;
            iGet.setText(String.valueOf(totalValue) + " франков.");
        }

    }

    @FXML
    public void setMyMoney(ActionEvent event) throws SQLException {
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();
        Statement statement = connection.createStatement();

        String sql = "select userMoney from users where userID=(select max(userID) from users)";
        ResultSet queryOutput = statement.executeQuery(sql);
        if(moneyCH.isSelected()){
            while(queryOutput.next()){
                myMoney = Integer.parseInt(queryOutput.getString("userMoney"));
                iHave.setText(queryOutput.getString("userMoney"));
            }
        }

    }

    @FXML
    public void toMainAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Наши услуги.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("mainIcon.png")));
        stage.setScene(scene);
        stage.show();
        logoutB.getScene().getWindow().hide();
    }
}
