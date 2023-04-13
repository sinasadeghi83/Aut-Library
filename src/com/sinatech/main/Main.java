package com.sinatech.main;

import com.sinatech.console.Command;
import com.sinatech.components.DatabaseManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String cmdStr = scanner.nextLine();
        DatabaseManager myDb = new DatabaseManager();
        while (!cmdStr.equals("finish")){
            Command command = new Command(cmdStr, myDb);
            command.runCommand();
            cmdStr = scanner.nextLine();
        }
    }
}
