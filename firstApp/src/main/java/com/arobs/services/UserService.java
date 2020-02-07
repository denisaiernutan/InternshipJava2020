package com.arobs.services;

import com.arobs.entities.Product;
import com.arobs.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static List<User> usersList = new ArrayList<>(7);

    public static void populateUsersList() {
        for (int i = 0; i < 7; i++) {
            User user = new User("user" + i, "pass", "fName_no" + i, "lName_no" + i, false);
            usersList.add(user);
        }
    }

    public static User login(User user) {

        if (usersList.size() == 0 && ProductService.productsList.size() == 0) {
            populateUsersList();
            ProductService.populateProductsList();
        }
        String username = user.getUsername();
        String password = user.getPassword();
//        List<User> loggedInUser = usersList.stream().filter(u -> (u.getUsername().equals(username) && u.getPassword().equals(password))).collect(Collectors.toList());

        User loggedInUser = user;
        loggedInUser.setValid(false);
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getPassword().equals(password) && usersList.get(i).getUsername().equals(username)) {
                loggedInUser = usersList.get(i);
                loggedInUser.setValid(true);
                return loggedInUser;
            }

        }
        return loggedInUser;
    }

    public static int findUserFromList(User user) {
        if (usersList != null)
            for (int i = 0; i < usersList.size(); i++) {
                User userFromList = usersList.get(i);
                if (user.getUsername().equals(userFromList.getUsername()))
                    return i;
            }
        return -1;
    }


    public static void addProduct(User user, Product product) {

        int indexUser = findUserFromList(user);
        List<Product> productList = usersList.get(indexUser).getProductsList();
        if (productList == null) {
            productList = new ArrayList<Product>(15);
        }
        productList.add(product);
        user.setProductsList(productList);
        usersList.set(indexUser, user);

    }

    public static void updateUser(User oldUser, User updateUser){
        int indexUser = findUserFromList(oldUser);

        oldUser.setUsername(updateUser.getUsername());
        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setLastName(updateUser.getLastName());

        usersList.set(indexUser, oldUser);
    }
}
