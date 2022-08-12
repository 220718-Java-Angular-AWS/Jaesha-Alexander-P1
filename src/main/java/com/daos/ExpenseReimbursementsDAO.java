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
    public void create(ExpenseReimbursements expenseReimbursements) {
//        boolean complete = false;
        String sql = "INSERT INTO expense_reimbursements (ex_user_name, ex_date, ex_type, details, amount, ex_status ) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, expenseReimbursements.getExpenseUsername());
            ps.setString(2, expenseReimbursements.getExpenseDate());
            ps.setString(3,expenseReimbursements.getExpType());
            ps.setString(4, expenseReimbursements.getExpenseDetails());
            ps.setDouble(5, expenseReimbursements.getExpenseAmount());
            ps.setString(6, expenseReimbursements.getExpenseStatus());

//            complete = ps.execute();
            ps.execute();
            ResultSet erID = ps.getGeneratedKeys();

            if(erID.next())
            {
                expenseReimbursements.setExpenseID(erID.getInt("ex_id"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return complete;

    }



    public ExpenseReimbursements read(int id ) {

        String sql = "SELECT * FROM expense_reimbursements WHERE ex_id = ?";
        ExpenseReimbursements er = new ExpenseReimbursements();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);


            ResultSet querySet = ps.executeQuery();

            if(querySet.next())
            {


                er.setExpenseUsername(querySet.getString("ex_user_name"));
                er.setExpenseDate(querySet.getString("ex_date"));
                er.setExpType(querySet.getString("ex_type"));
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
                er.setExpType(querySet.getString("ex_type"));
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
    public void update(ExpenseReimbursements expenseReimbursements) {
//        Boolean updateComplete = false;

        String sql = "UPDATE expense_reimbursements SET ex_id = ?, ex_user_name = ?, ex_date = ?, ex_type = ?, details = ?, amount = ?, ex_status = ?  WHERE ex_user_name = ? AND ex_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, expenseReimbursements.getExpenseID());
            ps.setString(2,expenseReimbursements.getExpenseUsername());
            ps.setString(3, expenseReimbursements.getExpenseDate());
            ps.setString(4,expenseReimbursements.getExpType());
            ps.setString(5,expenseReimbursements.getExpenseDetails());
            ps.setDouble(6,  expenseReimbursements.getExpenseAmount());
            ps.setString(7,expenseReimbursements.getExpenseStatus());
            ps.setString(8,expenseReimbursements.getExpenseUsername());
            ps.setInt(9, expenseReimbursements.getExpenseID());

//            updateComplete = ps.execute();
            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        return updateComplete;

    }



    // delete reimbursement
    public void delete(ExpenseReimbursements expenseReimbursements) {
//        Boolean deleteSuccess = false;
        String sql = "DELETE FROM expense_reimbursements WHERE ex_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, expenseReimbursements.getExpenseID());
//            deleteSuccess = ps.execute();
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        return deleteSuccess;

    }


}
