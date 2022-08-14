package com.daos;

import com.pojos.CurrentUser;
import com.service.ConnectionManagerService;

import java.sql.*;
import java.util.List;

public class CurrentUserDAO  {
    // choose not to use implement CRUD because will not use some of the methods
    Connection connection;

    public CurrentUserDAO()
    {
        this.connection = ConnectionManagerService.getConnection();
    }


    public void create(CurrentUser currentUser) {
        String sql = "INSERT INTO currentUser (current_user) VALUES (?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(sql);

            // setting information into SQL statement
            ps.setString(1, currentUser.getCurrentUser());

            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public CurrentUser readAll() {
        CurrentUser currentUser = new CurrentUser();
        String sql = "SELECT * FROM currentUser";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next())
            {
                currentUser.setCurrentUser(resultSet.getString("current_user"));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currentUser;

    }

// will never be used will just delete from the table
    public void update(CurrentUser currentUser) {
        String sql = "UPDATE currentUser  SET current_user = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, currentUser.getCurrentUser());


            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void delete(CurrentUser currentUser) {
        String sql = "DELETE FROM currentUser";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
