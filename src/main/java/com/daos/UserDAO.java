package com.daos;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ConnectionManagerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CRUD<Users> {

    Connection connection; // used to connect to the database

    // constructor used to create set the  connection
    public UserDAO() {
        this.connection = ConnectionManagerService.getConnection();
    }

    // CRUD - Create Read Update Delete

    // will take in a user and input it into the database
    // when user signs up
    public Boolean create(Users users) {
        Boolean complete = false;
        String sql = "INSERT INTO users (user_first_name, user_last_name,user_name, password, status) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            // setting information into SQL statement
            ps.setString(1, users.getFirstname());
            ps.setString(2, users.getLastname());
            ps.setString(1, users.getUsername());
            ps.setString(2, users.getPassword());
            ps.setString(3, users.getStatus());

            // sending to table
            complete = ps.execute(); // returns boolean for update

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complete;
    }


    // will read username to check to see if the person exists in the database
    // returns a boolean to instatiate if user exists
    public Boolean readValidateUser(String username) {
        Boolean usernameExists;

        String sqlUser = "SELECT * FROM users u WHERE u.user_name = ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            ps.setString(1, username);
            usernameExists = ps.execute();

            return usernameExists;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Users readUser(String username) {
        Users user = new Users();

        String sqlUser = "SELECT * FROM users u WHERE u.user_name = ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            ps.setString(1, username);
            ResultSet userQuery = ps.executeQuery();

            if(userQuery.next())
            {
                user = new Users(userQuery.getString("user_first_name"),
                        userQuery.getString("user_last_name"),
                        userQuery.getString("user_name"),
                        userQuery.getString("password"),
                        userQuery.getString("status"));
                user.setUser_id(userQuery.getInt("user_id"));




            }
            userQuery.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;

    }



    public List<Users> readAll() {
        // store the users to be returned
        List<Users> userListDatabase = new ArrayList<Users>();

        String sqlReadUsers = "SELECT * FROM users";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlReadUsers);
            ResultSet queryUsersSet = ps.executeQuery();

            //getting all the users from the returned set
            // and creating user objects to add to the list
            while(queryUsersSet.next())
            {
                Users user = new Users(queryUsersSet.getString("user_first_name"),
                        queryUsersSet.getString("user_last_name"),
                        queryUsersSet.getString("user_name"),
                        queryUsersSet.getString("password"),
                        queryUsersSet.getString("status"));
                user.setUser_id(queryUsersSet.getInt("user_id"));

                userListDatabase.add(user);


            }
            queryUsersSet.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userListDatabase;

    }



    // will change the status of the user between user and admin
    // to use has to change user status first in another method
    // and then pass user to this method
    public Boolean update(Users users) {
        Boolean updateComplete = false;

        String sql = "UPDATE users u SET u.user_id= ?,u.user_first_name= ?,u.user_last_name= ?,u.user_name= ?,u.password= ?, u.status = ? WHERE u.user_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, users.getUser_id());
            ps.setString(2,users.getFirstname());
            ps.setString(1, users.getLastname());
            ps.setString(2,users.getUsername());
            ps.setString(1, users.getPassword());
            ps.setString(2,users.getStatus());
            ps.setString(2,users.getUsername());

            updateComplete = ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updateComplete;
    }

    public Boolean delete(Users users) {
        Boolean deleteSuccess = false;
        String sql = "DELETE FROM users WHERE u.user_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, users.getUsername());

            deleteSuccess = ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deleteSuccess;
    }

    // NOT NECCESSARY REALIZED CAN READ ALL AND THEN ITERATE THE ONES WE WANT FROM THE SERVICE LAYER


//    // will read username to check the person status (admin or user)
//    // returns a user
//    // can be used to check user status
//    public Users readUser(Users user) {
//        Users readUser = new Users();
//        String sqlUser = "SELECT * FROM users u WHERE u.user_name = ? ";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sqlUser);
//            ps.setString(1, user.getUsername());
//            ResultSet UserSet = ps.executeQuery();
//
//            if(UserSet.next())
//            {
//                readUser.setFirstname(UserSet.getString("user_first_name"));
//                readUser.setLastname(UserSet.getString("user_last_name"));
//                readUser.setUsername(UserSet.getString("user_name"));
//                readUser.setPassword(UserSet.getString("password"));
//                readUser.setStatus(UserSet.getString("status"));
//
//                readUser.setUser_id(UserSet.getInt("user_id"));
//            }
//            UserSet.close();
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return readUser;
//    }
}


