package sample; /**
 * Write a description of class TestStock here.
 * <p>
 * Gobihan Manogarasingam
 * Level 6
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestStock {
    static ArrayList<Stock> stocks = new ArrayList<Stock>();

    public static void main(String[] param) throws IOException {
        setStock();
        String stock = inputword("What share do you want to invest in?");
        Portfolio p = new Portfolio();
        int howmanybuy = 0;
        int howmanysell = 0;
        p.setstocksbought(howmanybuy);
        p.setstockssold(howmanysell);
        Stock a = searchStock(stock);
        while ((a.getstockname().equals(""))) {
            stock = inputword("This share is not found in the Stockmarket\nWhat share do you want to invest in?");
            a = searchStock(stock);
        }
        while ((a.getstockname()).equals(stock)) {
            if ((a instanceof Stock)) {
                if ((a instanceof electronicsStock)) {
                    electronicsStock A = (electronicsStock) a;
                    System.out.println(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nHype: " + A.getHype());
                    String carryon1;
                    do {
                        carryon1 = inputword("Do you still want to invest in these shares?");
                    }
                    while (!((carryon1).equals("YES") || (carryon1).equals("NO")));
                    if (carryon1.equals("YES")) {
                        int exists = p.getIndexStock(A);
                        if (exists == -1) {
                            System.out.println("You have this number of stocks: 0");
                        } else {
                            System.out.println("You have this number of stocks: " + p.NumberofStocks.get(exists));
                        }
                        //System.out.println("You have this number of stocks: "+p.getnumberofstocks(stock));
                        int buy = inputnumber("How many shares do you want to invest in?");
                        int sell = inputnumber("How many shares do you want to sell?");
                        p.addnumberofstocks(exists, buy, sell);
                        System.out.println("The total cost of this many shares is " + A.stockcost(buy, sell) + "(GBX)");
                        a.event();
                    } else if (carryon1.equals("NO")) {
                        a.event();
                    } else {
                        carryon1 = inputword("Not valid, please input yes or no");
                    }
                } else if ((a instanceof fashionStock)) {
                    fashionStock A = (fashionStock) a;
                    System.out.println(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nTrend: " + A.gettrend());
                    String carryon2;
                    do {
                        carryon2 = inputword("Do you still want to invest in these shares?");
                    }
                    while (!((carryon2).equals("YES") || (carryon2).equals("NO")));
                    if (carryon2.equals("YES")) {
                        int exists = p.getIndexStock(A);
                        if (exists == -1) {
                            System.out.println("You have this number of stocks: 0");
                        } else {
                            System.out.println("You have this number of stocks: " + p.NumberofStocks.get(exists));
                        }
                        //System.out.println("You have this number of stocks: "+p.getnumberofstocks(stock));
                        int buy = inputnumber("How many shares do you want to invest in?");
                        int sell = inputnumber("How many shares do you want to sell?");
                        p.addnumberofstocks(exists, buy, sell);
                        System.out.println("The total cost of this many shares is " + A.stockcost(buy, sell) + "(GBX)");
                        a.event();
                    } else if (carryon2.equals("NO")) {
                        a.event();
                    } else {
                        carryon2 = inputword("Not valid, please input yes or no");
                    }
                } else if ((a instanceof pharmaceuticalsStock)) {
                    pharmaceuticalsStock A = (pharmaceuticalsStock) a;
                    System.out.println(A.getstockname() + "\nIt's stockvalue is " + A.getstockvalue() + " (GBX)\nRisk: " + A.getrisk() + " \nRate of Medicine production: " + A.getrate());
                    String carryon3;
                    do {
                        carryon3 = inputword("Do you still want to invest in these shares?");
                    }
                    while (!((carryon3).equals("YES") || (carryon3).equals("NO")));
                    if (carryon3.equals("YES")) {
                        int exists = p.getIndexStock(A);
                        if (exists == -1) {
                            System.out.println("You have this number of stocks: 0");
                        } else {
                            System.out.println("You have this number of stocks: " + p.NumberofStocks.get(exists));
                        }
                        //System.out.println("You have this number of stocks: "+p.getnumberofstocks(stock));
                        int buy = inputnumber("How many shares do you want to invest in?");
                        int sell = inputnumber("How many shares do you want to sell?");
                        p.addnumberofstocks(exists, buy, sell);
                        System.out.println("The total cost of this many shares is " + A.stockcost(buy, sell) + "(GBX)");
                        a.event();
                    } else if (carryon3.equals("NO")) {
                        a.event();
                    }
                }
            }
            do {
                stock = inputword("If you want to invest in another share enter the name of the share to invest in, if you don't, just enter no");
                a = searchStock(stock);
                if (stock.equals("NO")) {
                    break;
                }
            }
            while ((a.getstockname().equals("")));
        }
    }

    public static String inputword(String question) {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        word = scanner.nextLine().toUpperCase();
        return word;
    }

    public static int inputnumber(String question) {
        int number;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println(question);
            number = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            number = inputnumber("Not valid, try again");
        }
        return number;
    }

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

    public static Stock searchStock(String name) {
        Stock s = new Stock("", 0, "", "");
        for (int i = 0; i < stocks.size(); i++) {
            if (name.equals(stocks.get(i).getstockname())) {
                s = stocks.get(i);
            }
        }
        return s;
    }
}


