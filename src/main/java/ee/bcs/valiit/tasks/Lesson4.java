package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    static HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {
        String accountNr = "EE123";
        String createAccount = "createAccount";
        String getBalance = "getBalance";
        String depositMoney = "depositMoney";
        String withdrawMoney = "withdrawMoney";
        String transfer = "transfer";
        BigDecimal amount = BigDecimal.valueOf(0);
        BigDecimal accountBalance = BigDecimal.valueOf(0);


        System.out.println("To create account, type: \""+createAccount+"\" and account nr in format \""+accountNr+"\" (space separated, no quotes!).");
        System.out.println("To get acc balance, type: \""+getBalance+"\" and account nr in format \""+accountNr+"\" (space separated, no quotes!).");
        System.out.println("To deposit money, type: \""+depositMoney+"\", account in format \""+accountNr+"\" and amount in format \"1234\" (space separated, no quotes!).");
        System.out.println("To withdraw money, type: \""+withdrawMoney+"\", account in format \""+accountNr+"\" and amount in format \"1234\" (space separated, no quotes!).");
        System.out.println("To transfer money, type: \""+transfer+"\", from which acc\""+accountNr+"\" , to which acc\""+accountNr+"\" and what amount \"1234\" (space separated, no quotes!).");
        System.out.print("Type \"quit\" to exit");
        Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Type your request here: ");
                String line = scanner.nextLine();
                String[] input = line.split(" "); //võiks kasutada - line.startsWith()
                //System.out.println(Arrays.toString(line.split(" ")));
                //if (line.equalsIgnoreCase("exit")){
                //    break;
                if (input[0].equals("quit")) {
                    break;

                    // TODO 1
                    // Add command: "createAccount ${accountNr}"
                    // this has to store accountNr with 0 balance
                } else if (input[0].equals(createAccount)) {
                    accountBalanceMap.put(input[1], BigDecimal.valueOf(0));
                    System.out.println("Account number has been set to: " + input[1]);
                }

                // TODO 2
                // Add command: "getBalance ${accountNr}"
                // this has to display account balance of specific acount
                else if (input[0].equals(getBalance)) {
                    System.out.println("Account balance for acc "+input[1]+" is "+accountBalanceMap.get(input[1]));
                }

                // TODO 3
                // Add command: "depositMoney ${accountNr} ${amount}
                // this has to add specified amount of money to account
                // You have to check that amount is positive number
                else if (input[0].equals(depositMoney)) {
                    BigDecimal bd = new BigDecimal(input[2]);
                    if (bd.compareTo(BigDecimal.valueOf(0)) > 0) {
                        System.out.println("Previous balance for this account was: " + accountBalanceMap.get(input[1]));
                        accountBalance = accountBalanceMap.get(input[1]).add(bd);
                        accountBalanceMap.replace(input[1], accountBalance);
                        System.out.println("New balance for this account is: " + accountBalanceMap.get(input[1]));
                    } else {
                        System.out.println("Check your amount input");
                        continue;
                    }
                }
                // TODO 4
                // Add command: "withdrawMoney ${accountNr} ${amount}
                // This has to remove specified amount of money from account
                // You have to check that amount is positive number
                // You may not allow this transaction if account balance would become negative
                else if (input[0].equals(withdrawMoney)) {
                    BigDecimal bd = new BigDecimal(input[2]);
                    if (bd.compareTo(BigDecimal.valueOf(0)) > 0) {
                        System.out.println("Previous balance for this account was: " + accountBalanceMap.get(input[1]));
                        accountBalance = accountBalanceMap.get(input[1]).subtract(bd);
                        accountBalanceMap.replace(input[1], accountBalance);
                        System.out.println("New balance for this account is: " + accountBalanceMap.get(input[1]));
                    } else {
                        System.out.println("Check your amount entry, must be positive nr");
                        continue;
                    }
                }

            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction
                else if (input[0].equals(transfer)) {
                    BigDecimal bd = new BigDecimal(input[3]);
                    if (bd.compareTo(BigDecimal.valueOf(0)) > 0) {
                        System.out.println("Previous balance for transferring account was: " + accountBalanceMap.get(input[1]));
                        accountBalance = accountBalanceMap.get(input[1]).subtract(bd);
                        accountBalanceMap.replace(input[1], accountBalance);
                        System.out.println("New balance for this account is: " + accountBalanceMap.get(input[1]));
                        System.out.println("Previous balance for receiving account was: " + accountBalanceMap.get(input[2]));
                        accountBalance = accountBalanceMap.get(input[2]).add(bd);
                        accountBalanceMap.replace(input[2], accountBalance);
                        System.out.println("New balance for this account is: " + accountBalanceMap.get(input[2]));
                    } else {
                        System.out.println("Check your amount entry, must be positive nr");
                        continue;
                    }
                }
            else {
                System.out.println("Unknown command");
                }
        }
    }
}
