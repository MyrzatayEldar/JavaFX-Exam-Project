package com.example.aviacompany;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController extends HelloApplication {
    @FXML
    private Label welcomeText,logSL;
    @FXML
    Parent root;
    @FXML
    private TextField nameField;

    @FXML
    private TextField moneyField;

    @FXML
    private Button enterB;




    public void initialize() throws IOException, SQLException {


    }
    @FXML
    public void toMainPage(ActionEvent event) throws SQLException{
        String name = nameField.getText();
        float money = Float.parseFloat(moneyField.getText());

        if(nameField.getText().isEmpty()||moneyField.getText().isEmpty()||
                nameField.getText().isEmpty()&&moneyField.getText().isEmpty())
        {
            logSL.setText("Поля пустые.");
        }
        else {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection1();

            String query = "INSERT INTO `users`(`userName`, `userMoney`) VALUES (\""+name+"\","+money+");";

                try {

                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    System.out.println("Success!");
                    statement.close();
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
                    stage.setTitle("Наши услуги.");
                    stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("mainIcon.png")));
                    stage.setScene(scene);
                    stage.show();
                    enterB.getScene().getWindow().hide();
                }
                catch (Exception e){
                    e.printStackTrace();
                }


        }
    }



}
