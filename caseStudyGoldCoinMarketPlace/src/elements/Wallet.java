package elements;

public class Wallet {
    private int walletID;
    private double  USD;
    private double  GoldCoin;

    private double blockedGoldCoin;
    private  double blockedUSD;
    public  Wallet (double USD, double GoldCoin){
        this.USD = USD;
        this.GoldCoin = GoldCoin;
        this.blockedUSD = 0;
        this.blockedGoldCoin = 0;
    };

    public double getUSD() {
        return USD;
    }

    public double getGoldCoin() {
        return GoldCoin;
    }

    public double getBlockedUSD() {
        return blockedUSD;
    }

    public double getBlockedGoldCoin() {
        return blockedGoldCoin;
    }

    public void blockUSD(double amount) {
        // Implement logic to block USD funds
        if (amount <= USD) {
            USD -= amount;
            blockedUSD += amount;
        } else {
            // Handle insufficient funds
            System.out.println("Insufficient USD funds to block.");
        }
    }

    public void blockGoldCoin(double amount) {
        // Implement logic to block GoldCoin funds
        if (amount <= GoldCoin) {
            GoldCoin -= amount;
            blockedGoldCoin += amount;
        } else {
            // Handle insufficient funds
            System.out.println("Insufficient GoldCoin funds to block.");
        }
    }

    public void unblockUSD(double amount) {
        // Implement logic to unblock USD funds
        if (amount <= blockedUSD) {
            blockedUSD -= amount;
            USD += amount;
        } else {
            // Handle attempting to unblock more than blocked
            System.out.println("Attempted to unblock more USD than is blocked.");
        }
    }

    public void unblockGoldCoin(double amount) {
        // Implement logic to unblock GoldCoin funds
        if (amount <= blockedGoldCoin) {
            blockedGoldCoin -= amount;
            GoldCoin += amount;
        } else {
            // Handle attempting to unblock more than blocked
            System.out.println("Attempted to unblock more GoldCoin than is blocked.");
        }
    }

    public void deductUSD(double amount) {
        if (amount >= 0 && amount <= USD) {
            USD -= amount;
        } else {
            // Handle invalid amount or insufficient funds
            System.out.println("Invalid amount or insufficient USD funds to deduct.");
        }
    }

    public void deductGoldCoin(double amount) {
        if (amount >= 0 && amount <= GoldCoin) {
            GoldCoin -= amount;
        } else {
            // Handle invalid amount or insufficient GoldCoin funds
            System.out.println("Invalid amount or insufficient GoldCoin funds to deduct.");
        }
    }

    public void addUSD(double amount) {
        if (amount >= 0) {
            USD += amount;
        } else {
            // Handle invalid amount
            System.out.println("Invalid amount to add USD.");
        }
    }

    public void addGoldCoin(double amount) {
        if (amount >= 0) {
            GoldCoin += amount;
        } else {
            // Handle invalid amount
            System.out.println("Invalid amount to add GoldCoin.");
        }
    }

}
