package executable;

import elements.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Main <input_file> <output_file>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        try {
            // Read the input file and process its content
            List<String> lines = readInputFile(inputFileName);

            // Extract commission rate and marketplace fee
            int commissionRate = Integer.parseInt(lines.get(0));
            String[] marketplaceInfo = lines.get(1).split(" ");
            int initialMarketplaceFee = Integer.parseInt(marketplaceInfo[0]);
            int numUsers = Integer.parseInt(marketplaceInfo[1]);
            int numQueries = Integer.parseInt(marketplaceInfo[2]);

            // Create traders and market
            ArrayList<Trader> traders;
            traders = createTraders(lines.subList(2, 2 + numUsers));
            Market market = new Market(initialMarketplaceFee);

            // Process queries
            int currentIndex = 2 + numUsers;
            for (int i = 0; i < numQueries; i++) {
                String queryLine = lines.get(currentIndex);
                String[] queryParts = queryLine.split(" ");
                int queryType = Integer.parseInt(queryParts[0]);

                switch (queryType) {
                    case 10:
                        // Buy query
                        int traderID = Integer.parseInt(queryParts[1]);
                        double price = Double.parseDouble(queryParts[2]);
                        double amount = Double.parseDouble(queryParts[3]);
                        int result = traders.get(traderID).buy(amount, price, market);
                        if (result == 0) {
                            System.out.println("Successful Buy: Trader(ID=" + traderID + ")");
                        } else {
                            System.out.println("Failed Buy: Trader(ID=" + traderID + ")");
                        }
                        break;

                    // Add cases for other query types if needed

                    default:
                        System.out.println("Unsupported Query Type: " + queryType);
                        break;
                }

                currentIndex++;
            }

            // Check final transactions and update wallet balances
            ArrayList<Transaction> transactions = market.checkTransactions(traders);

            // Print transaction details and final wallet balances
            printTransactionDetails(transactions);
            printFinalWalletBalances(traders);

            // Write output to the specified output file
            writeOutputToFile(outputFileName, commissionRate, transactions, traders);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }






    private static List<String> readInputFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static ArrayList<Trader> createTraders(List<String> lines) {
        ArrayList<Trader> traders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length >= 2) {
                double initialUSD = Double.parseDouble(parts[0]);
                double initialGoldCoin = Double.parseDouble(parts[1]);
                traders.add(new Trader(initialUSD, initialGoldCoin));
            }
        }
        return traders;
    }

    private static void processQueries(List<String> lines, ArrayList<Trader> traders, Market market, int startIndex, int numQueries) {
        for (int i = startIndex; i < startIndex + numQueries; i++) {
            String queryLine = lines.get(i);
            String[] queryParts = queryLine.split(" ");
            int queryType = Integer.parseInt(queryParts[0]);

            switch (queryType) {
                case 10:
                    // Buy query
                    int traderID = Integer.parseInt(queryParts[1]);
                    double price = Double.parseDouble(queryParts[2]);
                    double amount = Double.parseDouble(queryParts[3]);
                    int result = traders.get(traderID).buy(amount, price, market);
                    if (result == 0) {
                        System.out.println("Successful Buy: Trader(ID=" + traderID + ")");
                    } else {
                        System.out.println("Failed Buy: Trader(ID=" + traderID + ")");
                    }
                    break;

                // Add cases for other query types if needed

                default:
                    System.out.println("Unsupported Query Type: " + queryType);
                    break;
            }
        }
    }

    private static void printTransactionDetails(ArrayList<Transaction> transactions) {
        System.out.println("Transaction Details:");
        for (Transaction transaction : transactions) {
            System.out.println("Transaction: Buyer(ID=" + transaction.getBuyingOrder().getTraderID() +
                    ") bought from Seller(ID=" + transaction.getSellingOrder().getTraderID() +
                    ") - Amount: $" + transaction.getTransactionAmount());
        }
    }

    private static void writeOutputToFile(String fileName, int commissionRate, ArrayList<Transaction> transactions, ArrayList<Trader> traders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(commissionRate + "\n");

            writer.write(traders.get(0).getMarketplaceFee() + " " + traders.size() + " " + transactions.size() + "\n");

            for (Trader trader : traders) {
                writer.write(trader.getInitialUSD() + " " + trader.getInitialGoldCoin() + "\n");
            }

            for (Transaction transaction : transactions) {
                writer.write("10 " + transaction.getBuyingOrder().getTraderID() + " " +
                        transaction.getSellingOrder().getPrice() + " " +
                        transaction.getBuyingOrder().getAmount() + "\n");
            }

            System.out.println("Output written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to the output file: " + e.getMessage());
        }
    }





    // Implement other methods (e.g., process queries, print transaction details, etc.) as needed

    // Implement writeOutputToFile method as in the previous response
}
