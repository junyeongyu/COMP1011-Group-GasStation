package com.group.gasstation;

import com.group.gasstation.db.DBManager;

public class Main {   
    public static void main(String[] args) {
        DBManager dBManager = new DBManager();

        // test query
        final String query = "SELECT * FROM EMPLOYEE";
        
        GasStation gasStation = new GasStation();
        gasStation.db = dBManager;
        
        dBManager.getList(query);
    }
}
