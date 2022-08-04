package com.pojos;

import com.service.ConnectionManagerService;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Connecting...");
        Connection connection = ConnectionManagerService.getConnection();
        System.out.println("Done!");

    }
}
