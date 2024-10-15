package com.george.console.impl;

import com.george.console.Console;

import java.util.Scanner;

public class DefaultConsole implements Console {

    private final Scanner scanner;

    public DefaultConsole() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public int nextInt() {
        return this.scanner.nextInt();
    }

    @Override
    public String nextLine() {
        return this.scanner.nextLine();
    }
}
