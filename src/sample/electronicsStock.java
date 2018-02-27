/**
 * Write a description of class electronicsStock here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
package sample;
import java.util.Random;

public class electronicsStock extends Stock {
    // instance variables - replace the example below with your own
    private String Hype;

    /**
     * Constructor for objects of class electronicsStock
     */
    public electronicsStock(String nameofstock, double valueofstock, String riskofstock, String demandofstock, String stockhype) {
        super(nameofstock, valueofstock, riskofstock, demandofstock);
        Hype = stockhype;
    }

    public static int dice() {
        Random dice = new Random();
        int dicethrow = dice.nextInt(6) + 1;
        return dicethrow;
    }

    public String getHype() {
        return Hype;
    }

    public String event() {
        int x = dice();
        int y = dice();
        int stockchange = x * y;
        String event = "";
        if (x > y) {
            event = "Something bad with electronics happened, hackers did some shit";
            stockvalue = stockvalue - stockchange;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
        } else if (y > x) {
            event = "New game on all platforms came out, big hit";
            stockvalue = stockvalue + stockchange;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
        }
        return event;
    }
}
