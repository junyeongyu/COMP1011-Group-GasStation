package com.group.gasstation;

import com.group.gasstation.db.DBManager;

public class Main
{   
    public static void main(String[] args)
    {
        new GasStation().setDBManager(new DBManager());
    }
}
