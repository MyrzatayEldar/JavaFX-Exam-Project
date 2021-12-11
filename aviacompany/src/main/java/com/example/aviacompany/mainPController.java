package com.example.aviacompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mainPController extends HelloController{
    @FXML
    private Label profileL,balanceL,welcomeL;

    @FXML
    private Button logoutB,buyTickets, popPhrases, aboutUs, converter;



    public void initialize() throws SQLException {
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();
        String query = "SELECT userName, userMoney FROM users WHERE userID = (SELECT MAX(userID) FROM users)";

        Statement statement = connection.createStatement();
        ResultSet queryOutput = statement.executeQuery(query);
        while(queryOutput.next()) {
            welcomeL.setText("Доброго времени суток, " + queryOutput.getString("userName") + ". Попробуйте наши услуги.");
            profileL.setText(queryOutput.getString("userName"));
            balanceL.setText(queryOutput.getString("userMoney")+"$");
        }
    }

    @FXML
    void logoutAction(ActionEvent event) {
        Stage stage = HelloApplication.getPrimaryStage();
        stage.show();
        logoutB.getScene().getWindow().hide();
    }


    @FXML
    public void buyTicketsAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("buyTickets.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Купить билеты.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("buy.png")));
        stage.setScene(scene);
        stage.show();
        buyTickets.getScene().getWindow().hide();
    }

    @FXML
    public void popPhrasesAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("phrases.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Популярные фразы.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("talking.png")));
        stage.setScene(scene);
        stage.show();
        popPhrases.getScene().getWindow().hide();

    }

    @FXML
    public void converterAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("converter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Конвертер валют.");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("conv.png")));
        stage.setScene(scene);
        stage.show();
        converter.getScene().getWindow().hide();

    }


    @FXML
    public void aboutUsAction(ActionEvent event) throws IOException{

    }


}
