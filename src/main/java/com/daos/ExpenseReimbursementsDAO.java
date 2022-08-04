package com.daos;

import com.pojos.ExpenseReimbursements;
import com.service.ConnectionManagerService;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseReimbursementsDAO implements CRUD<ExpenseReimbursements>  {
    Connection connection;
    public ExpenseReimbursementsDAO() {
        this.connection = ConnectionManagerService.getConnection();
    }


    // adds expense to the database
    public Boolean create(ExpenseReimbursements expenseReimbursements) {
        Boolean createSuccess = false;
        String sql = "INSERT INTO expense_reimbursements (ex_user_name, ex_date, details, amount, ex_status ) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            createSuccess = ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return createSuccess;
    }



    public ExpenseReimbursements read(int index ) {
        ExpenseReimbursements er = new ExpenseReimbursements();;

        String sql = "SELECT * FROM expense_reimbursements WHERE e.ex_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,index);

            ResultSet querySet = ps.executeQuery();

            if(querySet.next())
            {
                er.setExpenseUsername(querySet.getString("ex_user_name"));
                er.setExpenseDate(querySet.getString("ex_date"));
                er.setExpenseDetails(querySet.getString("details"));
                er.setExpenseAmount(querySet.getDouble("amount"));
                er.setExpenseStatus(querySet.getString("ex_status"));
                er.setExpenseID(querySet.getInt("ex_id"));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return er;


    }



    // read all the reimbursements in the database
    public List<ExpenseReimbursements> readAll() {
        List<ExpenseReimbursements> erList = new ArrayList<ExpenseReimbursements>();

        String sql = "SELECT * FROM expense_reimbursements";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet querySet = ps.executeQuery();

            while(querySet.next())
            {
                ExpenseReimbursements er = new ExpenseReimbursements();
                er.setExpenseUsername(querySet.getString("ex_user_name"));
                er.setExpenseDate(querySet.getString("ex_date"));
                er.setExpenseDetails(querySet.getString("details"));
                er.setExpenseAmount(querySet.getDouble("amount"));
                er.setExpenseStatus(querySet.getString("ex_status"));
                er.setExpenseID(querySet.getInt("ex_id"));

                erList.add(er);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return erList;
    }




    // update reimbursement status so pending to complete or cancel
    public Boolean update(ExpenseReimbursements expenseReimbursements) {
        Boolean updateComplete = false;

        String sql = "UPDATE expense_reimbursements e SET e.ex_id = ?, e.ex_user_name = ?,e.ex_date = ?, e.details = ?, e.amount = ?, e.ex_status = ?,  WHERE e.ex_user_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, expenseReimbursements.getExpenseID());
            ps.setString(2,expenseReimbursements.getExpenseUsername());
            ps.setString(3, expenseReimbursements.getExpenseDate());
            ps.setString(4,expenseReimbursements.getExpenseDetails());
            ps.setDouble(5,  expenseReimbursements.getExpenseAmount());
            ps.setString(6,expenseReimbursements.getExpenseStatus());
            ps.setString(7,expenseReimbursements.getExpenseUsername());

            updateComplete = ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updateComplete;

    }



    // delete reimbursement
    public Boolean delete(ExpenseReimbursements expenseReimbursements) {
        Boolean deleteSuccess = false;
        String sql = "DELETE FROM expense_reimbursements e WHERE e.user_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, expenseReimbursements.getExpenseUsername());

            deleteSuccess = ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deleteSuccess;

    }




    // NOT NECCESSARY BECAUSE WE CAN GET ALL THE REIMBURSEMENTS AND ITER THROUGH THEM IN THE SERVICE LAYER

//    // reads a specific reimbursement based off its SERIAL number
//    public ExpenseReimbursements read(int index ) {
//        ExpenseReimbursements er = new ExpenseReimbursements();
//
//        String sql = "SELECT * FROM expense_reimbursements e WHERE e.ex_id = ?";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//            ps.setInt(1, index);
//            ResultSet rs = ps.executeQuery();
//
//            if(rs.next())
//            {
//                er.setExpenseUsername(rs.getString("ex_user_name"));
//                er.setExpenseDate(rs.getDate("ex_date"));
//                er.setExpenseDetails(rs.getString("details"));
//                er.setExpenseAmount(rs.getDouble("amount"));
//                er.setExpenseStatus(rs.getString("ex_status"));
//                er.setExpenseID(rs.getInt("ex_id"));
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return er;
//    }
//
//    // reads reimbursements based on status pending , complete or canceled
//
//
//    // reads reimbursements based on user - to get all the expenseReimbursements for a specific users
//    public List<ExpenseReimbursements> read(String username ) {
//
//    }
//    // update the details of reimbursement
//    public Boolean updateDetails(ExpenseReimbursements expenseReimbursements) {
//        Boolean updateComplete = false;
//
//        String sql = "UPDATE expense_reimbursements e SET e.details = ?,e.amount = ?,e.ex_date = ? WHERE e.ex_user_name = ?";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, expenseReimbursements.getExpenseDetails());
//            ps.setDouble(2,expenseReimbursements.getExpenseAmount());
//            ps.setDate(3, (Date) expenseReimbursements.getExpenseDate());
//            ps.setString(4,expenseReimbursements.getExpenseUsername());
//            updateComplete = ps.execute();
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return updateComplete;
//    }
}
