package elements;

public class Transaction {
    private SellingOrder sellingOrder;
    private BuyingOrder buyingOrder;
    private double transactionAmount;

    public Transaction(BuyingOrder buyingOrder, SellingOrder sellingOrder) {
        this.buyingOrder = buyingOrder;
        this.sellingOrder = sellingOrder;
        // Calculate the transaction amount based on the orders (if needed)
        this.transactionAmount = Math.min(buyingOrder.getAmount(), sellingOrder.getAmount()) * Math.min(buyingOrder.getPrice(), sellingOrder.getPrice());
    }

    public SellingOrder getSellingOrder() {
        return sellingOrder;
    }

    public BuyingOrder getBuyingOrder() {
        return buyingOrder;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sellingOrder=" + sellingOrder +
                ", buyingOrder=" + buyingOrder +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}
