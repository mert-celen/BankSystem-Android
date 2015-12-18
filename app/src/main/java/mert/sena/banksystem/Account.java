package mert.sena.banksystem;

import java.util.Stack;

public class Account {

    private String name;
    private String detail;
    private double amount;
    private int id;
    private static int nextId = 10000;
    private double dept;
    private double limit;
    private double original_limit;
    private String username;
    private String password;
    private Stack messages;
    private int pinCode;


    public Account(String name, String detail, double amount, double dept, double limit, String username, String password,int pinCode) {
        this.name = name;
        this.detail = detail;
        this.amount = amount;
        this.dept = dept;
        this.limit = limit;
        this.original_limit = limit;
        this.username = username;
        this.password = password;
        this.id = nextId++;
        this.pinCode = pinCode;
        app.currentid = this.id;
        messages = new Stack();
    }

    public void addMoney(double m) {
        this.amount += m;
    }

    public boolean removeMoney(double m) {
        if (this.getAmount() >= m) {
            this.amount -= m;
            return true;
        } else {
            if (limit < m || m < 0)
                return false;
            else {
                limit -= m;
                dept += m;
                amount =+ m;
                return true;
            }
        }
    }

    public boolean payDept(double m) {
        if (m > dept)
            return false;
        if (amount >= m) {
            dept -= m;
            limit += m;
            if (limit > original_limit)
                limit = original_limit;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }


    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public double getDept() {
        return dept;
    }

    public double getLimit() {
        return limit;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addStack(String s) {
        messages.push(s);
    }

    public String getfromStack() {
        return messages.pop().toString();
    }

    public boolean hasmoreStack() {

        if (messages.size() > 0)
            return true;
        else
            return false;
    }
}
