package mert.sena.banksystem;

import java.util.ArrayList;

public class AccountList{

    ArrayList<Account> list = new ArrayList<Account>();

    public AccountList() {
    }

    public boolean addAccount(String name, String detail, double limit, String username, String password,int pinCode){
        if(isUserAvailable(username)){
        Account dummy = new Account(name,detail,10000,325,limit,username,password,pinCode);
        boolean flag = list.add(dummy);

        return flag;
        }else
            return false;

    }

    public boolean isUserAvailable(String s){
        for(Account a:list){
            if(a.getUsername().compareTo(s)==0)
                return false;
        }
        return true;
    }
    
    public boolean removeAccount(int id){
        for (Account a: list) {
            if(a.getId()==id)
                return list.remove(a);
        }
        return false;
    }

    public double totalDept(){
        double total=0;
        for (Account a:list) {
            total+=a.getDept();
        }
        return total;
    }

    public double totalMoney(){
        double total=0;
        for (Account a:list) {
            total+=a.getAmount();
        }
        return total;
    }

    public String getDetails(int id){
        for (Account a:list) {
            if(a.getId()==id)
                return a.toString();
        }
        return null;
    }

    public boolean payDept(int id,double amount){
        for (Account a:list) {
            if(a.getId()==id)
                return a.payDept(amount);
        }
        return false;
    }

    public Account getObj(int id){
        for (Account a:list) {
            if(a.getId()==id)
                return a;
        }
        return null;
    }
    
//    public Account getObj(String username,String password){
//        for (Account a:list){
//            if(a.getUsername().compareTo(username)==0 && a.getPassword().compareTo(password)==0)
//                return a;
//        }
//        return null;
//    }


    public int findAccount(String username){
        for (Account a:list) {
            if(a.getUsername().compareTo(username)==0)
                return a.getId();
        }
        return -1;
    }


    
    public boolean checkAccount(String username,String password){
        for(Account a:list){
            if(a.getUsername().compareTo(username)==0 && a.getPassword().compareTo(password)==0){
                app.currentid = a.getId();
                return true;
            }
                
        }
        return false;
    }
    
    public boolean withdraw(int accountId,double amount){
        for(Account a:list){
            if(a.getId()==accountId){
                 a.removeMoney(amount);
                 return true;
            }  
        }
        return false;
    }
    
    public boolean canPay(int accountid,double amount){
        for(Account a:list){
            if(a.getId()==accountid){
                 if(a.getAmount()>=amount)
                     return true;
            }  
        }
        return false;
    }
    
    public boolean transfer(int accountid2,int accountid1,double amount,String message){
        boolean flag=false;
        if(canPay(accountid1, amount)){
            for(Account a:list){
                if(a.getId()==accountid2){
                    a.addMoney(amount);
                    a.addStack(message);
                    flag=true;
                    for(Account b:list){
                        if(b.getId()==accountid1)
                            flag = b.removeMoney(amount);
                    }
                }
            }
        }
        return flag;
    }
}
