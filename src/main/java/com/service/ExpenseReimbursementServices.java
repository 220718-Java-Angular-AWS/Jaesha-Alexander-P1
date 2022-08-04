package com.service;

import com.daos.ExpenseReimbursementsDAO;
import com.pojos.ExpenseReimbursements;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public class ExpenseReimbursementServices {
    private ExpenseReimbursementsDAO expenseReimbursementsDAO;

    public ExpenseReimbursementServices()
    {
        this.expenseReimbursementsDAO = new ExpenseReimbursementsDAO();
    }

    public Boolean save(ExpenseReimbursements expenseReimbursements)
    {
        return expenseReimbursementsDAO.create(expenseReimbursements);
    }

    public ExpenseReimbursements readER(int index)
    {
        return expenseReimbursementsDAO.read(index);
    }

    public List<ExpenseReimbursements> readAllER()
    {
        return expenseReimbursementsDAO.readAll();
    }

    public Boolean updateER(ExpenseReimbursements expenseReimbursements)
    {
        return expenseReimbursementsDAO.update(expenseReimbursements);
    }

    public Boolean deleteER(ExpenseReimbursements expenseReimbursements)
    {
        return expenseReimbursementsDAO.delete(expenseReimbursements);
    }
}
