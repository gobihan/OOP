/**
 * Write a description of class Stocks here.
 * <p>
 * Gobihan Manogarasingam
 * Level 1
 */
package sample;
public class Stock {
    protected double stockvalue;
    protected String risk;
    // instance variables - replace the example below with your own
    private String stockname;
    private String demand;

    /**
     * Constructor for objects of class Stocks
     */
    public Stock(String nameofstock, double valueofstock, String riskofstock, String demandofstock) {
        // initialise instance variables
        stockname = nameofstock;
        stockvalue = valueofstock;
        risk = riskofstock;
        demand = demandofstock;
    }

    public String getstockname() {
        return stockname;
    }

    public double getstockvalue() {
        return stockvalue;
    }

    public String getrisk() {
        return risk;
    }

    public String getdemand() {
        return demand;
    }

    public double stockcost(int howmanybuy, int howmanysell) {
        return (howmanybuy - howmanysell) * stockvalue;
    }

    public String event() {
        double x = Math.random();
        double y = Math.random();
        String event = "";
        if (x > y) {
            stockvalue = stockvalue - 100;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
            event = "Oh nooooo, Trump became president, the stock value will go down.";
        } else if (y > x) {
            stockvalue = stockvalue + 100;
            if (stockvalue > 50) {
                risk = "low";
            } else if (stockvalue < 50) {
                risk = "high";
            }
            event = "Well, somewhere in Africa, they found a new oil reserve";
        }
        return event;
    }
}

