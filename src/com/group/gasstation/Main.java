package com.group.gasstation;

import com.group.gasstation.db.DBManager;
import java.util.List;
import java.util.Map;

public class Main {   
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();

        // test query
        final String query = "SELECT * FROM employee";
        List<Map<String, Object>> list = dbManager.getList(query);
        System.out.println(list);
        
        GasStation gasStation = new GasStation();
        gasStation.db = dbManager;
        

    }
}
