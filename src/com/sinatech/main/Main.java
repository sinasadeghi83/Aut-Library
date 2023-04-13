package com.sinatech.main;

import com.sinatech.models.Command;
import com.sinatech.models.Database;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String cmdStr = scanner.nextLine();
        Database myDb = new Database();
        while (!cmdStr.equals("finish")){
            Command command = new Command(cmdStr, myDb);
            command.runCommand();
            cmdStr = scanner.nextLine();
        }
    }
}
