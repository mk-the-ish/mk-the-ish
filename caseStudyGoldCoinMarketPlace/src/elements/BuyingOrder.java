package elements;

public class BuyingOrder extends Order implements Comparable<BuyingOrder>{
    public BuyingOrder(int traderID,double amount, double price){
        //constructor
        super(traderID,amount,price);

    }

    @Override
    public int compareTo(BuyingOrder other) {
        // Compare BuyingOrder objects based on their prices
        if (this.price < other.price) {
            return -1;
        } else if (this.price > other.price) {
            return 1;
        } else {
            // If prices are equal, compare by traderID
            if (this.traderID < other.traderID) {
                return -1;
            } else if (this.traderID > other.traderID) {
                return 1;
            } else {
                return 0;
            }
        }

    }
}
