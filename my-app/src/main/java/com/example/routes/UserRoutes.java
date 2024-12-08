package com.example.routes;

import com.example.controllers.UserController;
import com.example.models.User;
import static spark.Spark.*;
import com.google.gson.Gson;

public class UserRoutes {

    public static void init() {
        UserController userController = new UserController();

        get("/users/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params(":id"));
            User user = userController.getUserById(userId);
            if (user != null) {
                res.type("application/json");
                return "{\"id\": " + user.getId() + ", \"name\": \"" + user.getName() + "\", \"email\": \"" + user.getEmail() + "\"}";
            } else {
                res.status(404);
                return "{\"error\": \"User not found\"}";
            }
        });

        post("/users", (req, res) -> {
            String requestBody = req.body();
            Gson gson = new Gson();

            User userFromBody = gson.fromJson(requestBody, User.class);

            String name = userFromBody.getName();
            String email = userFromBody.getEmail();

            if (name == null || email == null) {
                res.status(400);
                return "{\"error\": \"Name and email are required\"}";
            }

            boolean success = userController.createUser(name, email);
            if (success) {
                res.status(201);
                return "{\"message\": \"User created successfully\"}";
            } else {
                res.status(500);
                return "{\"error\": \"Failed to create user\"}";
            }
        });
    }
}
