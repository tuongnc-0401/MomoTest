package com.company;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Date savedDate = new Date("12/01/2021");
        Date currentDate = new Date();

        int totalMoney = 0;
        int leftMoney = 0;


//        System.out.println("------- Welcome Coin-Operated Soda Machine----------");
//        System.out.println("Please choose an action");
//        System.out.println("1. Enter money.");
//        System.out.println("2. Choose product");
//        System.out.println("3. Exit");
//        System.out.println("---------------------------");
//        System.out.print("Choose number (1-3): ");
//        Scanner input = new Scanner(System.in);
//
//        String number;
//        for(number = input.nextLine().trim(); !number.equals("1") && !number.equals("2") && !number.equals("3"); number = input.nextLine().trim()) {
//            System.out.println("Your input is incorrect! Please choose a number from 1 to 3!");
//            System.out.print("Choose number (1-3): ");
//        }
        totalMoney = inputMoney();
        choosePorduct(totalMoney);
    }

    private static void choosePorduct(int totalMoney) {
        int money = totalMoney;
        System.out.println("You have inputted "+money+" VND");
        // the number of coke, pepsi and sode which user buy
        int countCoke = 0, countPepsi = 0, countSoda = 0;
        Scanner input = new Scanner(System.in);
        String choices;
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

            if (!choices.equals("1")&&!choices.equals("2") && !choices.equals("3")&& !choices.equals("4")) {
                System.out.println("You enter the wrong input! Please enter it again");
            } else {

                if(choices.equals("4")){
                    break;
                }

                if (choices.equals("1")) {
                    if(!checkEnoughMoney(money,10000)){
                        System.out.println("You don't have enough money");
                        break;
                    }
                    money -= 10000;
                    countCoke+=1;
                } else if (choices.equals("2")) {
                    if(!checkEnoughMoney(money,10000)){
                        System.out.println("You don't have enough money");
                        break;
                    }
                    money -= 10000;
                    countPepsi+=1;
                } else if (choices.equals("3")) {
                    if(!checkEnoughMoney(money,20000)){
                        System.out.println("You don't have enough money");
                        break;
                    }
                    money -= 20000;
                    countSoda+=1;
                }

                System.out.println("Do you want to continue buying?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Your choices (1-2): ");
                choices =input.nextLine().trim();
                if (choices.equals("2")){
                    break;
                }
            }



        }
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
        System.out.println("You have left "+ money+ " VND" );

    }


    private static boolean checkEnoughMoney(int totalMoney, int price){
        return totalMoney >= price ? true : false;
    }
    private static int inputMoney() {
        int money = 0;
        Scanner input = new Scanner(System.in);
        String choices;
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
            } else if (choices.equals("6")) {
                return money;
            } else {
                System.out.println("You enter the wrong input! Please enter it again");
            }
        }

    }


}
