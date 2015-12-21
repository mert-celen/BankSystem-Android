/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mert.sena.banksystem;


/**
 *
 * @author mertcelen
 */

public class app {
    public static AccountList accounts;
    public static int currentid;
    public static boolean flag = true;
    public static void loadApp(){
        accounts = new AccountList();
        currentid = 0;
    }
}
