package com.quickbase.devint;

import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.util.List;

public class ConcreteDBStatService implements IStatService{

    /**
     * Returns an unordered list of countries and their populations from a specific sources
     */
    @Override
    public List<Pair<String, Integer>> GetCountryPopulations() {
        DBManager dbm = new DBManagerImpl();
        Connection c = dbm.getConnection();
        if (null == c) {
            System.out.println("failed.");
            System.exit(1);
        }
        return dbm.queryDb();
    }
}
