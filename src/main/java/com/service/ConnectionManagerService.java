package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerService {
    private static Connection connection;

    private ConnectionManagerService()
    {

    }

    public static Connection getConnection()
    {
        if(connection == null)
        {
            connect();

        }
        return connection;
    }

    private static void connect()
    {
        try {
            Properties props = new Properties();

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            props.load(input);

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String driver = props.getProperty("driver");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String dbname = props.getProperty("dbname");

            StringBuilder builder = new StringBuilder("jdbc:postgresql://");
            builder.append(host);
            builder.append(":");
            builder.append(port);
            builder.append("/");
            builder.append(dbname);
            builder.append("?user=");
            builder.append(username);
            builder.append("&password=");
            builder.append(password);


            Class.forName(driver);


            connection = DriverManager.getConnection(builder.toString());




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
