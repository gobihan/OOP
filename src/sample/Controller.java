package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    static ArrayList<Stock> stocks = new ArrayList<Stock>();
    static Stock a;
    static Portfolio p = new Portfolio();
    static int exists;
    static String stock;
    @FXML
    private TextField stockName;
    @FXML
    private Label stockAvailability;
    @FXML
    private TextArea stockDetail;
    @FXML
    private Button invest;
    @FXML
    private TextField buy;
    @FXML
    private TextField sell;
    @FXML
    private Button investAgain;
    @FXML
    private TextArea news;
    @FXML
    private TextArea portfolio;
    @FXML
    private Label costOfShares;
    @FXML
    private Button refresh;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Label loginFail;

    public static void setStock() throws IOException {
        File f1 = new File("C:\\Users\\gobih\\IdeaProjects\\Stocks\\src\\sample\\stocks.txt");
        BufferedReader breader = new BufferedReader(new FileReader(f1));
        String line;
        String[] lines;
        while ((line = breader.readLine()) != null) {
            lines = line.split(" ");
            if (lines[0].equals("1")) {
                stocks.add(new electronicsStock(lines[1], Double.parseDouble(lines[2]), lines[3], lines[4], lines[5]));
            } else if (lines[0].equals("2")) {
                stocks.add(new pharmaceuticalsStock(lines[1], Double.parseDouble(lines[2]), lines[3], lines[4], lines[5]));
            } else if (lines[0].equals("3")) {
                stocks.add(new fashionStock(lines[1], Double.parseDouble(lines[2]), lines[3], lines[4], lines[5]));
            }
        }
    }

    public static boolean toSell(int sell) {
        if ((exists == -1 && sell > 0) ^ (exists >= 0 && sell > p.NumberofStocks.get(exists))) {
            return false;
        } else {
            return true;
        }
    }

    public static Stock searchStock(String name) {
        Stock s = new Stock("", 0, "", "");
        for (int i = 0; i < stocks.size(); i++) {
            if (name.equals(stocks.get(i).getstockname())) {
                s = stocks.get(i);
            }
        }
        return s;
    }

    public void login(ActionEvent event) throws IOException {
        setStock();
        if (username.getText().equals("gobihan") && password.getText().equals("monkeyboy10")) {
            loginFail.setText("");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Stock GUI");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.initOwner(login.getScene().getWindow());
            primaryStage.showAndWait();
        } else {
            loginFail.setText("Either your username and password is incorrect");
        }
    }

    public void getStock(ActionEvent event) throws IOException {
        stock = stockName.getText();
        a = searchStock(stock);
        if ((a instanceof electronicsStock)) {
            stockAvailability.setText("");
            electronicsStock A = (electronicsStock) a;
            stockDetail.setText(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nHype: " + A.getHype());
        } else if ((a instanceof fashionStock)) {
            stockAvailability.setText("");
            fashionStock A = (fashionStock) a;
            stockDetail.setText(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nTrend: " + A.gettrend());
        } else if ((a instanceof pharmaceuticalsStock)) {
            stockAvailability.setText("");
            pharmaceuticalsStock A = (pharmaceuticalsStock) a;
            stockDetail.setText(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nRate of Medicine production: " + A.getrate());
        } else if (a.getstockname().equals("")) {
            stockAvailability.setText("Sorry, this stock isn't available in the Stock market");

        }

    }

    public void investStock(ActionEvent event) throws IOException {
        if (event.getSource() == invest) {

            exists = p.getIndexStock(a);
            if (exists == -1) {
                stockDetail.appendText("\nYou have this number of stocks: 0");
            } else {
                stockDetail.appendText("\nYou have this number of stocks: " + p.NumberofStocks.get(exists));
            }
        }
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Popup.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Invest in stock");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(invest.getScene().getWindow());
        stage.show();
    }

    public void investStockAgain(ActionEvent event) throws IOException {
        if (p.getIndexStock(a) == -1) {
            p.addStock(a);
        }
        int buyy = 0;
        int selll = 0;
        try {
            buyy = Integer.parseInt(buy.getText());
            selll = Integer.parseInt(sell.getText());
        } catch (NumberFormatException e) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Exception.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("ALERT");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(investAgain.getScene().getWindow());
            stage.show();
        }
        boolean toSell = toSell(selll);
        if (toSell == false) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Notenuffstocksalert.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("ALERT");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(investAgain.getScene().getWindow());
            stage.showAndWait();
        } else if (toSell == true) {
            p.addnumberofstocks(exists, buyy, selll);
            costOfShares.setText("The total cost of this many shares is " + a.stockcost(buyy, selll) + "(GBX)");
        }
    }

    public void refresh(ActionEvent event) throws IOException {
        news.appendText(a.event() + "\n");
        portfolio.setText("STOCKNAME\tNO.OF SHARES\n");
        for (int i = 0; i < p.Stock.size(); i++) {
            portfolio.appendText(p.Stock.get(i).getstockname() + "\t\t\t" + p.NumberofStocks.get(i) + "\n");
        }
        if ((a instanceof electronicsStock)) {
            electronicsStock A = (electronicsStock) a;
            stockDetail.setText(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nHype: " + A.getHype());
        } else if ((a instanceof fashionStock)) {
            fashionStock A = (fashionStock) a;
            stockDetail.setText(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nTrend: " + A.gettrend());
        } else if ((a instanceof pharmaceuticalsStock)) {
            pharmaceuticalsStock A = (pharmaceuticalsStock) a;
            stockDetail.setText(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nRate of Medicine production: " + A.getrate());
        }
    }
}
