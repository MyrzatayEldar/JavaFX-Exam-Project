package com.example.aviacompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Phrases {

    ObservableList<String> langs = FXCollections.observableArrayList(
            "английский",
            "испанский",
            "казахский",
            "португальский",
            "болгарский",
            "узбекский",
            "итальянский",
            "китайский",
            "латынь",
            "корейский",
            "монгольский",
            "турецкий",
            "чешский");
    @FXML
    private Label profileL, balanceL, someTXT, welcomeL;

    @FXML
    private Label m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;

    @FXML
    private Button logoutB;

    @FXML
    private ImageView changeIM;

    @FXML
    private ChoiceBox langCHB;

    public void initialize() throws SQLException {
        langCHB.setValue("английский");
        langCHB.setItems(langs);
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = con.getConnection1();
        String query = "SELECT userName, userMoney FROM users WHERE userID = (SELECT MAX(userID) FROM users)";

        Statement statement = connection.createStatement();
        ResultSet queryOutput = statement.executeQuery(query);
        while (queryOutput.next()) {
            welcomeL.setText("Доброго времени суток, " + queryOutput.getString("userName") + ". Попробуйте наши услуги.");
            profileL.setText(queryOutput.getString("userName"));
            balanceL.setText(queryOutput.getString("userMoney") + "$");
        }
        changeIM.setOnMouseClicked((MouseEvent e) -> {
            String value = (String) langCHB.getValue();
            someTXT.setText("Выбран язык: " + value);

            if (value == "английский") {
                m1.setText("1. Where is the nearest hotel?");
                m2.setText("2. Could you explain to me how to get through?");
                m3.setText("3. Excuse me, is this bus going to the center?");
                m4.setText("4. How do I get there from here, please?");
                m5.setText("5. Could you call me a taxi?");
                m6.setText("6. How much will it cost?");
                m7.setText("7. I need a single/double room.");
                m8.setText("8. I would like to place an order.");
                m9.setText("9. What is the address?");
                m10.setText("10. Take me to this address, please.");
            } else if (value == "испанский") {
                m1.setText("1. ¿Dónde está el hotel más cercano?");
                m2.setText("2. ¿Podría explicarme cómo pasar?");
                m3.setText("3. Lo siento, ¿este autobús va al centro?");
                m4.setText("4. ¿Cómo puedo llegar allí desde aquí, por favor?");
                m5.setText("5. ¿Podría llamarme un taxi?");
                m6.setText("6. ¿Cuánto costará?");
                m7.setText("7. Necesito una habitación individual/doble.");
                m8.setText("8. Me gustaría hacer un pedido.");
                m9.setText("9. ¿Cuál es la dirección?");
                m10.setText("10. Llévame a esta dirección, por favor.");
            } else if (value == "казахский") {

                m1.setText("1. Ең жақын қонақ үй қайда?");
                m2.setText("2. Сіз маған қалай өту керектігін түсіндіре аласыз ба?");
                m3.setText("3. Кешіріңіз, бұл автобус орталыққа бара жатыр ма?");
                m4.setText("4. Осы жерден қалай жетуге болады?");
                m5.setText("5. Сіз маған такси шақыра аласыз ба?");
                m6.setText("6. Бұл қанша тұрады?");
                m7.setText("7. Маған бір / екі орындық нөмір керек.");
                m8.setText("8. Мен тапсырыс бергім келеді.");
                m9.setText("9. Қандай мекен?");
                m10.setText("10. Мені осы мекен-жайға апарыңыз, өтінемін.");
            } else if (value == "португальский") {

                m1.setText("1. Onde está o hotel mais próximo?");
                m2.setText("2. Você poderia me explicar como passar?");
                m3.setText("3. Desculpe, esse ônibus vai para o centro?");
                m4.setText("4. Como chego lá daqui, por favor?");
                m5.setText("5. Pode chamar-me um táxi?");
                m6.setText("6. Quanto vai custar?");
                m7.setText("7. Eu preciso de um Quarto Individual / Duplo.");
                m8.setText("8. Eu gostaria de fazer um pedido.");
                m9.setText("9. Qual é a morada?");
                m10.setText("10. Leve-me para este endereço, por favor.");
            } else if (value == "болгарский") {

                m1.setText("1. Къде е най-близкият хотел?");
                m2.setText("2. Ще ми обясните ли как да мина?");
                m3.setText("3. Извинете, този автобус отива ли към центъра?");
                m4.setText("4. Как да стигна до там, моля?");
                m5.setText("5. Ще ми извикате ли такси?");
                m6.setText("6. Колко ще струва?");
                m7.setText("7. Имам нужда от единична / двойна стая.");
                m8.setText("8. Бих искал да поръчам.");
                m9.setText("9. Какъв е адресът?");
                m10.setText("10. Заведете ме на този адрес, моля.");
            } else if (value == "узбекский") {

                m1.setText("1. Eng yaqin mehmonxona qaerda?");
                m2.setText("2. Menga qanday qilib o'tishni tushuntira olasizmi?");
                m3.setText("3. Kechirasiz, bu avtobus markazga ketadimi?");
                m4.setText("4. Bu yerdan qanday qilib olishim mumkin?");
                m5.setText("5. Menga taksi qo'ng'iroq qila olasizmi?");
                m6.setText("6. Bu qancha turadi?");
                m7.setText("7. Menga bitta/ikkita xona kerak.");
                m8.setText("8. Men buyurtma berishni xohlayman.");
                m9.setText("9. Qaysi manzil?");
                m10.setText("10. Meni ushbu manzilga olib boring, iltimos.");
            } else if (value == "итальянский") {

                m1.setText("1. Dov'e 'l'hotel piu' vicino?");
                m2.setText("2. Potrebbe spiegarmi come procedere?");
                m3.setText("3. Mi scusi, questo autobus va in centro?");
                m4.setText("4. Come posso arrivarci da qui, per favore?");
                m5.setText("5. Puo ' chiamarmi un taxi?");
                m6.setText("6. Quanto costerà?");
                m7.setText("7. Mi serve una camera singola / doppia.");
                m8.setText("8. Vorrei ordinare.");
                m9.setText("9. Qual e ' l'indirizzo?");
                m10.setText("10. Mi porti a questo indirizzo, per favore.");
            } else if (value == "китайский") {

                m1.setText("1. 最近的酒店在哪里？");
                m2.setText("2. 你能给我解释一下如何通过吗?");
                m3.setText("3. 对不起，这辆车是去中心的吗？");
                m4.setText("4. 我怎么从这里到那里，好吗？");
                m5.setText("5. 你能给我叫辆出租车吗?");
                m6.setText("6. 要花多少钱？");
                m7.setText("7. 我需要一间单人/双人间。");
                m8.setText("8. 我想下订单。");
                m9.setText("9. 地址是什么？");
                m10.setText("10. 请带我去这个地址。");
            } else if (value == "латынь") {

                m1.setText("1. Ubi est proxima hotel?");
                m2.setText("2. Posset explicare ad me, quam ut per?");
                m3.setText("3. Mihi ignosce, hoc est, bus ad centrum?");
                m4.setText("4. Quam operor ego adepto illic hinc, quaeso?");
                m5.setText("5. Potes dicere me a taxi?");
                m6.setText("6. Quantum erit sumptus?");
                m7.setText("7. Opus uno/duplex locus.");
                m8.setText("8. Vellem ponere ordinem.");
                m9.setText("9. Quid est oratio?");
                m10.setText("10. Tolle me ad hoc oratio, quaeso.");
            } else if (value == "корейский") {

                m1.setText("1. 가장 가까운 호텔은 어디에 있습니까?");
                m2.setText("2. 통과 방법을 설명해 주시겠습니까?");
                m3.setText("3. 실례합니다,이 버스가 센터로 가고 있습니까?");
                m4.setText("4. 여기서 어떻게 가야합니까?");
                m5.setText("5. 택시라고 불러 주시겠습니까?");
                m6.setText("6. 비용은 얼마입니까?");
                m7.setText("7. 싱글/더 블룸이 필요합니다.");
                m8.setText("8. 주문을하고 싶습니다.");
                m9.setText("9. 주소는 무엇입니까?");
                m10.setText("10. 이 주소로 데려다주세요.");
            } else if (value == "монгольский") {

                m1.setText("1. Хамгийн ойрын зочид буудал хаана байна?");
                m2.setText("2. Та надад яаж дамжуулан авах талаар тайлбарлаж болох уу?");
                m3.setText("3. Уучлаарай, энэ автобус төв рүү явж байна уу?");
                m4.setText("4. Би Эндээс тийшээ яаж очих вэ, тэгэх үү?");
                m5.setText("5. Чи намайг такси дуудаж болох уу?");
                m6.setText("6. Энэ нь хэр үнэтэй вэ?");
                m7.setText("7. Би нэг хэрэгтэй / хоер өрөө.");
                m8.setText("8. Би захиалга өгөхийг хүсч байна.");
                m9.setText("9. Хаяг гэж юу вэ?");
                m10.setText("10. Намайг энэ хаягаар авч яваарай.");
            } else if (value == "турецкий") {

                m1.setText("1. En yakın otel nerede?");
                m2.setText("2. Bana nasıl geçeceğimi açıklayabilir misiniz?");
                m3.setText("3. Affedersiniz, bu otobüs şehir merkezine mi gidiyor?");
                m4.setText("4. Buradan oraya nasıl gidebilirim, lütfen?");
                m5.setText("5. Bana taksi çağırabilir misiniz?");
                m6.setText("6. Bu ne kadara mal olacak?");
                m7.setText("7. Tek kişilik/çift kişilik odaya ihtiyacım var.");
                m8.setText("8. Ben sipariş vermek istiyorum lütfen.");
                m9.setText("9. Adres nedir?");
                m10.setText("10. Beni bu adrese götürün, lütfen.");
            } else if (value == "чешский") {

                m1.setText("1. Kde je nejbližší hotel?");
                m2.setText("2. Mohl byste mi vysvětlit, jak projít?");
                m3.setText("3. Promiňte, jede ten autobus do centra?");
                m4.setText("4. Jak se tam dostanu, prosím?");
                m5.setText("5. Můžete mi zavolat taxi?");
                m6.setText("6. Kolik to bude stát?");
                m7.setText("7. Potřebuji jednolůžkový / dvoulůžkový pokoj.");
                m8.setText("8. Chtěl bych si objednat.");
                m9.setText("9. Jakou adresu?");
                m10.setText("10. Vezměte mě na tuhle adresu, prosím.");
            }
        });


    }

    @FXML
    public void logoutTOMain(ActionEvent event) throws IOException {
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
