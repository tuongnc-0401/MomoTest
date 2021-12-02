package com.company;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // variable to store date, budget, percent of previous date
    public static Date savedDate = new Date("12/1/2021");
    public static int budget = 50000;
    public static int percentWin = 10;
    public static void main(String[] args) {

        while (true) {
            int totalMoney = 0;
            System.out.println("=== Welcome to Coin Operator Soda Machine ===");
            totalMoney = inputMoney();
            choosePorduct(totalMoney);


            System.out.println("Do you want to continue use Machine?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Your choices (1-2): ");
            Scanner input = new Scanner(System.in);
            String choices = input.nextLine().trim();
            // user dont want to buy
            if (choices.equals("2")) {
                break;
            }
        }
    }

    /**
     * this function is called, when user buy 3 consecutive same product,
     * it is luck charm for user to get a free product
     * @param product: name of product
     * @param value: value of product
     */
    private static void isLucky (String product, int value){

        // check available promotion every transaction, to update percentWin
        checkAvailablePromotion();

        // random a number
        int foo = (int) (Math.random() * 100);

        // if number is less chances %, user is lucky and got the prizes
        if (foo <= percentWin) {
            if(budget> value){ // if available budget is higher the product prices
                System.out.println("=================================================");
                System.out.println("|| Congratulation, You got a free "+product+ " ||");
                System.out.println("=================================================");
                budget-= value;
            } else { // if available budget is not enough
                System.out.println("Sorry! Better luck next time!");
            }
        } else { // if user is not lucky
            System.out.println("Sorry! Better luck next time!");
        }
    }

    /**
     *  this function is used to check available promotion of every purchase transaction
     */
    private static void checkAvailablePromotion() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // check it into a new day
        if ((sdf.format(currentDate).compareTo(sdf.format(savedDate))) >= 1) {
            // check the limit budget of previous date is left or not
            if(budget > 0) { // if yes, increase chance by 50%
                percentWin = percentWin + (int) (percentWin*0.5);
            } else { // if no, reset percentWin to 10%
                percentWin = 10;
            }
            // new day, so reset the budget
            budget = 50000;
            // set the saved date by current date
            savedDate = currentDate;
        }
    }

    /**
     * After inputting money, user will choose product
     * this function will let user choose product which they want to buy
     * @param totalMoney : amount of money, user inputted
     */
    private static void choosePorduct(int totalMoney) {
        int money = totalMoney;
        System.out.println("You have inputted "+money+" VND");
        // the number of coke, pepsi and sode which user buy
        int countCoke = 0, countPepsi = 0, countSoda = 0;
        Scanner input = new Scanner(System.in);
        String choices;
        // menu for user to choose
        while (true) {
            System.out.println("-----------You have " + money + " VND--------");
            System.out.println("Please choose the product");
            System.out.println("1. Coke (10 000 VND)");
            System.out.println("2. Pepsi (10 000 VND)");
            System.out.println("3. Soda (20 000)");
            System.out.println("4. Cancel buying");
            System.out.println("---------------------------");
            System.out.print("Choose number (1-3): ");
            choices =input.nextLine().trim();

            // check if the input is wrong
            if (!choices.equals("1")&&!choices.equals("2") && !choices.equals("3")&& !choices.equals("4")) {
                System.out.println("You enter the wrong input! Please enter it again");
            } else {
                // user dont want to continue buying
                if(choices.equals("4")){
                    break;
                }

                // user want to buy Coke
                if (choices.equals("1")) {
                    if(!checkEnoughMoney(money,10000)){
                        System.out.println("You don't have enough money");
                        break;
                    }
                    money -= 10000;
                    countCoke+=1;
                } else if (choices.equals("2")) { // user want to buy Pepsi
                    if(!checkEnoughMoney(money,10000)){
                        System.out.println("You don't have enough money");
                        break;
                    }
                    money -= 10000;
                    countPepsi+=1;
                } else if (choices.equals("3")) { // user want to buy Soda
                    if(!checkEnoughMoney(money,20000)){
                        System.out.println("You don't have enough money");
                        break;
                    }
                    money -= 20000;
                    countSoda+=1;
                }
                // ask user to continue buying
                System.out.println("Do you want to continue buying?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Your choices (1-2): ");
                choices =input.nextLine().trim();
                // user dont want to buy
                if (choices.equals("2")){
                    break;
                }
            }
        }
        // Print the result of purchase
        System.out.println("========================");
        if(countCoke!=0 || countPepsi!=0 || countSoda!=0){
            System.out.println("You bought :");
        }
        if(countCoke!=0) {
            System.out.println(countCoke + " Coke");
        }
        if(countPepsi!=0) {
            System.out.println(countPepsi + " Pepsi");
        }
        if(countSoda!=0) {
            System.out.println(countSoda + " Soda");
        }
        System.out.println("Your refund is "+ money+ " VND" );
        System.out.println("========================");
        // If user buy more than 3 consecutive product, they have chance to win a free product
        if (countCoke >=3) {
            int ticket = countCoke /3; // if users buy 6, they get 2 ticket chance to win a free
            System.out.println(" You buy "+ countCoke+ " so you have "+ ticket+" chances to win " +
                    "a free Coke" );
            for (int i = 0; i < ticket; i++){
                isLucky("Coke", 10000);
            }
        }

        if (countPepsi >=3) {
            int ticket = countPepsi /3; // if users buy 9, they get 3 ticket chance to win a free
            System.out.println(" You buy "+ countPepsi+ " so you have "+ ticket+" chances to win " +
                    "a free Pepsi" );
            for (int i = 0; i < ticket; i++){
                isLucky("Pepsi", 10000);
            }
        }

        if (countSoda >=3) {

            int ticket = countSoda / 3; // if users buy 3, they get 1 ticket chance to win a free
            System.out.println(" You buy "+ countSoda+ " so you have "+ ticket+" chances to win " +
                    "a free Pepsi" );
            for (int i = 0; i < ticket; i++){
                isLucky("Soda", 20000);
            }
        }

    }


    /**
     * check amount of money is enough to buy product
     * @param totalMoney
     * @param price
     * @return true or false
     */
    private static boolean checkEnoughMoney(int totalMoney, int price){
        return totalMoney >= price ? true : false;
    }

    /**
     * user input money
     * @return total money
     */
    private static int inputMoney() {
        int money = 0; // total money
        Scanner input = new Scanner(System.in);
        String choices;
        // give the options for user to enter money
        while (true) {
            System.out.println("-----------You have input " + money + " VND--------");
            System.out.println("Please enter the money");
            System.out.println("1. 10 000 VND.");
            System.out.println("2. 20 000 VND.");
            System.out.println("3. 50 000 VND.");
            System.out.println("4. 100 000 VND.");
            System.out.println("5. 200 000 VND.");
            System.out.println("6. Choose the product");
            System.out.println("---------------------------");
            System.out.print("Choose number (1-6): ");
            choices =input.nextLine().trim();
            // add the money to total money
            if (choices.equals("1")) {
               money += 10000;
            } else if (choices.equals("2")) {
                money += 20000;
            } else if (choices.equals("3")) {
                money += 50000;
            } else if (choices.equals("4")) {
                money += 100000;
            } else if (choices.equals("5")) {
                money += 200000;
            } else if (choices.equals("6")) { // user do not enter money and want to buy
                return money; // return the total money
            } else {
                System.out.println("You enter the wrong input! Please enter it again");
            }
        }

    }


}
