package com.group.gasstation;

import com.group.gasstation.db.DBManager;

/**
 * Bootstrap to provide initial point.
 */
public class Main
{   
    public static void main(String[] args)
    {
        new GasStation().setDBManager(new DBManager());
    }
}
