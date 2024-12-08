package com.example;

import com.example.routes.UserRoutes;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        System.setProperty("spark.port", "4567");

        port(4567);

        UserRoutes.init();

        System.out.println("Server started on port 4567");
    }
}