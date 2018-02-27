/**
 * Write a description of class fashionStock here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
package sample;

import java.util.Random;

public class fashionStock extends Stock {
    // instance variables - replace the example below with your own
    protected String trending;

    /**
     * Constructor for objects of class fashionStock
     */
    public fashionStock(String nameofstock, double valueofstock, String riskofstock, String demandofstock, String trend) {
        super(nameofstock, valueofstock, riskofstock, demandofstock);
        trending = trend;
    }

    public static int dice() {
        Random dice = new Random();
        int dicethrow = dice.nextInt(6) + 1;
        return dicethrow;
    }

    public String gettrend() {
        return trending;
    }

    public String event() {
        int x = dice();
        int y = dice();
        String event = "";
        if (x > y) {
            stockvalue = stockvalue - 45;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
            event = "Something bad happened in fashion";
        } else if (y > x) {
            System.out.println("Something good happened in fashion");
            stockvalue = stockvalue + 45;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
            event = "Something good happened in fashion";
        }
        return event;
    }
}

