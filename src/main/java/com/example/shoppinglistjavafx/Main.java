package com.example.shoppinglistjavafx;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        boolean isProgramRunning = true;
        while (isProgramRunning) {
            userManager.showAllOptions();
            isProgramRunning = userManager.selectOption();
        }
    }
}
