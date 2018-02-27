package sample;

import java.util.ArrayList;

public class Portfolio {
    ArrayList<Stock> Stock = new ArrayList<>();
    ArrayList<Integer> NumberofStocks = new ArrayList<>();
    private int boughtshares;
    private int soldshares;


    public Portfolio() {
        boughtshares = 0;
        soldshares = 0;
    }

    public void setstocksbought(int howmanybought) {
        boughtshares = boughtshares + howmanybought;
    }

    public void setstockssold(int howmanysold) {
        soldshares = soldshares + howmanysold;
    }

    public int numberofstocks() {
        return boughtshares - soldshares;
    }

    public int getIndexStock(Stock stock) {
        if (!Stock.contains(stock)) {
            return -1;
        } else {
            return Stock.indexOf(stock);
        }
    }

    public void addStock(Stock stock) {
        Stock.add(stock);
    }

    public void addnumberofstocks(int n, int buy, int sell) {
        int x = buy - sell;
        if (n == -1) {

            NumberofStocks.add(x);
        } else {
            NumberofStocks.set(n, (NumberofStocks.get(n) + x));
        }
    }
}

