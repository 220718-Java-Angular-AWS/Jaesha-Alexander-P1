package com.pojos;

import java.util.Date;

public class ExpenseReimbursements {

    private int expenseID;
    private String expenseUsername;
    private String expenseDate;
    private String expenseDetails;
    private double expenseAmount;
    private String expenseStatus;
    private String expType;

    public ExpenseReimbursements() {
        this.expenseStatus = "Pending";
    }
    public ExpenseReimbursements(String expenseUsername, String expenseDate, String expType, String expenseDetails, double expenseAmount) {
        this.expenseUsername = expenseUsername;
        this.expenseDate = expenseDate;
        this.expType = expType;
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = "Pending";

    }

    public ExpenseReimbursements(String expenseUsername, String expenseDate, String expType, String expenseDetails, double expenseAmount, String expenseStatus) {
        this.expenseUsername = expenseUsername;
        this.expenseDate = expenseDate;
        this.expType = expType;
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = expenseStatus;
    }

    public ExpenseReimbursements(String expenseUsername, String expenseDate, String expType, String expenseDetails, double expenseAmount, String expenseStatus, int expenseID) {
        this.expenseUsername = expenseUsername;
        this.expenseDate = expenseDate;
        this.expType = expType;
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = expenseStatus;
        this.expenseID = expenseID;
    }


    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getExpenseUsername() {
        return expenseUsername;
    }

    public void setExpenseUsername(String expenseUsername) {
        this.expenseUsername = expenseUsername;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(String expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(String expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    @Override
    public String toString() {

        return "ID: " + getExpenseID()+ " Username:  " + getExpenseUsername() + " Date:  " + getExpenseDate() + " Type: "+ getExpType() + " Details: " + getExpenseDetails() + " Amount: " + getExpenseAmount() + " Status: " + getExpenseStatus();

    }
}
