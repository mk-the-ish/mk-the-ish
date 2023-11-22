package elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Market {
    private PriorityQueue<SellingOrder> sellingOrders;
    private PriorityQueue<BuyingOrder> buyingOrders;
    private ArrayList<Transaction> transactions;
    private ArrayList<Trader> traders;
    private int marketFee;

    public Market(int fee){
        this.sellingOrders = new PriorityQueue<>(Comparator.comparingDouble(SellingOrder::getPrice));
        this.buyingOrders = new PriorityQueue<>(Comparator.comparingDouble(BuyingOrder::getPrice).reversed());
        this.transactions = new ArrayList<>();
        this.marketFee = fee;
    };
    public void giveSellOrder(SellingOrder order){
        sellingOrders.offer(order);
        order.setTraders(traders);
        makeOpenMarketOperation(order.getPrice());
    };

    public void giveBuyOrder(BuyingOrder order){
        buyingOrders.offer(order);
        order.setTraders(traders);
        makeOpenMarketOperation(order.getPrice());
    };

    public void makeOpenMarketOperation(double price){
        // Implement logic to adjust market prices based on given price
        // Check for overlapping prices and execute transactions
        // Check for overlapping prices between buy and sell orders
        while (!buyingOrders.isEmpty() && !sellingOrders.isEmpty()) {
            BuyingOrder buyOrder = buyingOrders.peek();
            SellingOrder sellOrder = sellingOrders.peek();

            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                System.out.println("Overlaping prices found");

                // Execute a transaction
                double transactionPrice = sellOrder.getPrice();
                double amountToTransact = Math.min(buyOrder.getAmount(), sellOrder.getAmount());

                // Calculate fees
                double fee = (transactionPrice * amountToTransact * marketFee) / 1000.0;
                double transactionAmount = transactionPrice * amountToTransact - fee;

                // Update trader wallets
                if(buyOrder.getTrader() != null){
                    buyOrder.getTrader().getWallet().unblockUSD(amountToTransact*buyOrder.getPrice());
                }
                if(sellOrder.getTrader() != null){
                    sellOrder.getTrader().getWallet().unblockGoldCoin(amountToTransact);
                }


                // Add the transaction to the list
                transactions.add(new Transaction(buyOrder, sellOrder));

                // Update the remaining amounts in the orders
                buyOrder.setAmount(buyOrder.getAmount() - amountToTransact);
                sellOrder.setAmount(sellOrder.getAmount() - amountToTransact);

                // Remove orders with zero or negative amounts
                if (buyOrder.getAmount() <= 0) {
                    buyingOrders.poll();
                }
                if (sellOrder.getAmount() <= 0) {
                    sellingOrders.poll();
                }
            } else {
                System.out.println("No overlaping prices");
                break;
            }
        }

    };

    public void checkTransactions(ArrayList<Trader> traders) {
        ArrayList<BuyingOrder> matchedBuyingOrders = new ArrayList<>();
        ArrayList<SellingOrder> matchedSellingOrders = new ArrayList<>();

        // Find matched orders
        for (BuyingOrder buyOrder : buyingOrders) {
            for (SellingOrder sellOrder : sellingOrders) {
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    matchedBuyingOrders.add(buyOrder);
                    matchedSellingOrders.add(sellOrder);
                }
            }
        }

        // Execute transactions for matched orders
        for (int i = 0; i < matchedBuyingOrders.size(); i++) {
            BuyingOrder buyOrder = matchedBuyingOrders.get(i);
            SellingOrder sellOrder = matchedSellingOrders.get(i);

            double transactionPrice = Math.min(buyOrder.getPrice(), sellOrder.getPrice());
            double transactionAmount = Math.min(buyOrder.getAmount(), sellOrder.getAmount());

            double marketFee = (transactionAmount * transactionPrice * this.marketFee) / 1000.0;
            double finalTransactionAmount = transactionAmount * transactionPrice - marketFee;

            Trader buyer = traders.get(buyOrder.getTraderID());
            Trader seller = traders.get(sellOrder.getTraderID());

            // Deduct USD and GoldCoin from the buyer's wallet
            buyer.getWallet().deductUSD(finalTransactionAmount);
            //buyer.getWallet().deductGoldCoin(transactionAmount);

            // Deduct USD and GoldCoin from the seller's wallet
            //seller.getWallet().deductUSD(finalTransactionAmount);
            seller.getWallet().deductGoldCoin(transactionAmount);

            // Add USD and GoldCoin to the buyer's wallet
            //buyer.getWallet().addUSD(finalTransactionAmount);
            buyer.getWallet().addGoldCoin(transactionAmount);

            // Add USD and GoldCoin to the seller's wallet
            seller.getWallet().addUSD(finalTransactionAmount);
            //seller.getWallet().addGoldCoin(transactionAmount);

            // Deduct market fee from the buyer's wallet
            buyer.getWallet().deductUSD(marketFee);

            // Deduct market fee from the buyer's wallet
            buyer.getWallet().deductUSD(marketFee);

            // Add the transaction to the list of transactions
            transactions.add(new Transaction(buyOrder,sellOrder ));

            buyOrder.setAmount(buyOrder.getAmount() - transactionAmount);
            sellOrder.setAmount(sellOrder.getAmount() - transactionAmount);

            // Remove orders with zero or negative amounts
            if (buyOrder.getAmount() <= 0) {
                buyingOrders.remove(buyOrder);
            }
            if (sellOrder.getAmount() <= 0) {
                sellingOrders.remove(sellOrder);
            }
        }
    }



    public void setTraders(ArrayList<Trader> traders) {
        this.traders = traders;
    }





}
