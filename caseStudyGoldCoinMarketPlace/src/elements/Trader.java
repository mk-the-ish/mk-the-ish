package elements;

public class Trader {
    private int id;
    private Wallet wallet;
    // Static variable to track the next available ID
    private static int nextId = 0;

    public Trader(double USD, double GoldCoin){
        this.id = nextId++;
        this.wallet = new Wallet(USD, GoldCoin);
    };
    public int getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public int sell(double amount, double price, Market market){
        // Implement logic for placing a sell order and blocking funds
        // Add the order to the market's sellingOrders
        // Update wallet balances and blocked amounts
        SellingOrder sellingOrder = new SellingOrder(this.id, amount, price);
        double totalCost = amount * price;
        if (wallet.getUSD() >= totalCost) {
            wallet.blockUSD(totalCost); // Block the USD for the buy order
            market.giveSellOrder(sellingOrder);
            wallet.blockGoldCoin(amount);
        } else {
            // Handle insufficient funds
            System.out.println("Insufficient Funds");
        }



        return sellingOrder.getId(); // Return a unique identifier for the order
    };
    public int buy(double amount, double price, Market market){
        // Implement logic for placing a buy order and blocking funds
        // Add the order to the market's buyingOrders
        // Update wallet balances and blocked amounts
        BuyingOrder buyingOrder = new BuyingOrder(this.id,amount,price);
        double totalCost = amount * price;
        if (wallet.getUSD() >= totalCost) {
            wallet.blockUSD(totalCost); // Block the USD for the buy order
            market.giveBuyOrder(buyingOrder);

        } else {
            // Handle insufficient funds
            System.out.println("Insufficient Funds");
        }

        return buyingOrder.getId(); // Return a unique identifier for the order
    };



    public static int numberOfUsers = 0;

}
