package com.daos;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ConnectionManagerService;

import java.sql.*;
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
    public void create(Users users) {

//        boolean complete = false;

        String sql = "INSERT INTO users (user_first_name, user_last_name,user_email, user_name, password, status) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // setting information into SQL statement
            ps.setString(1, users.getFirstname());
            ps.setString(2, users.getLastname());
            ps.setString(3,users.getEmail());
            ps.setString(4, users.getUsername());
            ps.setString(5, users.getPassword());
            ps.setString(6, users.getStatus());

            // sending to table
//            complete = ps.execute(); // returns boolean for update
            ps.execute();

            ResultSet keyId = ps.getGeneratedKeys();
            if(keyId.next())
            {
                users.setUser_id(keyId.getInt("user_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return complete;

    }


    //MAY BE UNNESSARY
    // will read username to check to see if the person exists in the database
    // returns a boolean to instatiate if user exists
    public Boolean readValidateUser(String username, String email) {
        Boolean exists = false;



        String sqlUser = "SELECT * FROM users WHERE user_name = ? OR user_email= ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            ps.setString(1,username);
            ps.setString(2, email);

            ResultSet result  = ps.executeQuery();

            if (result.next())
            {
                exists = true;
            }

            return exists;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Users readUser(String username) {
        Users user = null ;

        String sqlUser = "SELECT * FROM users u WHERE u.user_name = ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            ps.setString(1, username);
            ResultSet userQuery = ps.executeQuery();

            if(userQuery.next())
            {
                user = new Users(userQuery.getString("user_first_name"),
                        userQuery.getString("user_last_name"),
                        userQuery.getString("user_email"),
                        userQuery.getString("user_name"),
                        userQuery.getString("password"),
                        userQuery.getString("status"),
                        userQuery.getInt("user_id"));





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
                        queryUsersSet.getString("user_email"),
                        queryUsersSet.getString("user_name"),
                        queryUsersSet.getString("password"),
                        queryUsersSet.getString("status"));
//                System.out.println("READ ALL : " +queryUsersSet.getInt("user_id"));
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
    public void update(Users users) { // this user
        Boolean updateComplete = false;

        System.out.println("UPDATE INPUT: " + users);
        String sql = "UPDATE users  SET user_id= ?, user_first_name= ?, user_last_name= ?, user_email =?, user_name= ?,password= ?, status = ? WHERE user_name = ?";

        try {
//            System.out.println("UPDATE INPUT: " + users);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, users.getUser_id());
            ps.setString(2,users.getFirstname());
            ps.setString(3, users.getLastname());
            ps.setString(4, users.getEmail());
            ps.setString(5,users.getUsername());
            ps.setString(6, users.getPassword());
            ps.setString(7,users.getStatus());
            ps.setString(8,users.getUsername());

//            updateComplete = ps.execute();
            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        return updateComplete;
    }

    public void delete(Users users) {
//        Boolean deleteSuccess = false;
        String sql = "DELETE FROM users WHERE user_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, users.getUsername());

//            deleteSuccess = ps.execute();
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        return deleteSuccess;
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


