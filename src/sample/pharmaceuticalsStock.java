/**
 * Write a description of class pharmaceuticalsStock here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
package sample;
import java.util.Random;

public class pharmaceuticalsStock extends Stock {
    // instance variables - replace the example below with your own
    private String rateofmedicineproduction;

    /**
     * Constructor for objects of class pharmaceuticalsStock
     */
    public pharmaceuticalsStock(String nameofstock, double valueofstock, String riskofstock, String demandofstock, String rate) {
        super(nameofstock, valueofstock, riskofstock, demandofstock);
        rateofmedicineproduction = rate;
    }

    public static int dice() {
        Random dice = new Random();
        int dicethrow = dice.nextInt(6) + 1;
        return dicethrow;
    }

    public String getrate() {
        return rateofmedicineproduction;
    }

    public String event() {
        int x = dice();
        int y = dice();
        String event = "";
        if (x > y) {
            event = "Cancer was a government lie, just a way of making money, the public is outraged ";
            stockvalue = stockvalue - 250;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
        } else if (y > x) {
            event = "Massive epidemic outbreak";
            stockvalue = stockvalue + 250;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
        }
        return event;
    }
}

