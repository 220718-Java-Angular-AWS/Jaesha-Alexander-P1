package com.service;

import com.daos.ExpenseReimbursementsDAO;
import com.pojos.ExpenseReimbursements;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReimbursementServices {
    private ExpenseReimbursementsDAO expenseReimbursementsDAO;

    //validate service layer

    public ExpenseReimbursementServices()
    {
        this.expenseReimbursementsDAO = new ExpenseReimbursementsDAO();
    }

    // submitting a request
    public void save(ExpenseReimbursements expenseReimbursements)
    {
         expenseReimbursementsDAO.create(expenseReimbursements);
    }

    public ExpenseReimbursements readER(int id)
    {
        return expenseReimbursementsDAO.read(id);
    }

    public List<ExpenseReimbursements> readAllER()
    {
        return expenseReimbursementsDAO.readAll();
    }

    public List<ExpenseReimbursements> readByUser(String username)
    {
        List<ExpenseReimbursements> allEr = expenseReimbursementsDAO.readAll();
        List<ExpenseReimbursements> userList = new ArrayList<>();

        for(ExpenseReimbursements er : allEr)
        {
            if(er.getExpenseUsername().equals(username))
            {
                userList.add(er);
            }
        }

        return userList;
    }

    public void updateER(ExpenseReimbursements expenseReimbursements)
    {
         expenseReimbursementsDAO.update(expenseReimbursements);
    }

    public void deleteER(ExpenseReimbursements expenseReimbursements)
    {
         expenseReimbursementsDAO.delete(expenseReimbursements);
    }

    // cancel request
    public void cancelRequest(ExpenseReimbursements er)
    {
        er.setExpenseStatus("cancelled");
        // maybe even delete
        expenseReimbursementsDAO.update(er);
    }

    // allows user to view all there pending and completed ER
    public List<ExpenseReimbursements> viewAll(String username)
    {
        List<ExpenseReimbursements> userER = new ArrayList<ExpenseReimbursements>();

        List<ExpenseReimbursements> allER = expenseReimbursementsDAO.readAll();

        for(ExpenseReimbursements er: allER)
        {
            if(er.getExpenseUsername().equals(username))
            {
                userER.add(er);
            }
        }
        return userER;
    }

    //filter requests
    public List<ExpenseReimbursements> viewByStatusAdmin(String status)
    {
        List<ExpenseReimbursements> request = new ArrayList<ExpenseReimbursements>();

        List<ExpenseReimbursements> filter = expenseReimbursementsDAO.readAll();

        for(ExpenseReimbursements er : filter)
        {
            if(status.equals("All"))
            {
                if(er.getExpenseStatus().equals("Pending") || er.getExpenseStatus().equals("Approved")||status.equals("Cancelled") || er.getExpenseStatus().equals("Denied"))
                {
                    request.add(er);
                }
            }
            if(status.equals("Pending"))
            {
                if(er.getExpenseStatus().equals("Pending"))
                {
                    request.add(er);
                }
            }
            if(status.equals("Approved"))
            {
                if(er.getExpenseStatus().equals("Approved"))
                {
                    request.add(er);
                }
            }
            if(status.equals("Denied"))
            {
                if(er.getExpenseStatus().equals("Denied"))
                {
                    request.add(er);
                }
            }


        }


        return request;
    }

}
