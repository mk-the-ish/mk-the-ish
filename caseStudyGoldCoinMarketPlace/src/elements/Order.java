package elements;

import java.util.ArrayList;

public abstract class Order {
    //abstract class for sellOrder and buyOrder

    private int id;
    double amount;
    double price;
    int traderID;
    private static int nextId = 0;
    private ArrayList<Trader> traders;
    public Order(int traderID,double amount, double price){
        //abstract constructor
        this.id = nextId++;
        this.amount= amount;
        this.price = price;
        this.traderID = traderID;

    };

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public void addTrader(Trader trader){
        traders.add(trader);
    }

    public void setTraders(ArrayList<Trader> traders) {
        this.traders = traders;
    }

    public Trader getTrader() {
        Trader orderTrader = null;
        for (Trader trader : traders) {
            if (trader.getId() == traderID) {
                orderTrader = trader;
            }
        }

        return orderTrader;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public int getTraderID() {
        return traderID;
    }
}
