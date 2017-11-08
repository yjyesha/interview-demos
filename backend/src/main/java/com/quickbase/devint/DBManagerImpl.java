package com.quickbase.devint;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This DBManager implementation provides a connection to the database containing population data.
 * <p>
 * Created by ckeswani on 9/16/15.
 */
public class DBManagerImpl implements DBManager {

    Connection c = null;
    Statement stmt = null;

    public Connection getConnection() {
        if (c == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:backend/resources/data/citystatecountry.db");
                System.out.println("Opened database successfully");

            } catch (ClassNotFoundException cnf) {
                System.out.println("could not load driver");
            } catch (SQLException sqle) {
                //System.out.println("sql exception:" + Arrays.toString(sqle.getStackTrace()));
                sqle.printStackTrace();
            }
        }
        return c;
    }

    //TODO: Add a method (signature of your choosing) to query the db for population data by country
    public List<Pair<String, Integer>> queryDb() {
        List<Pair<String, Integer>> populationData = new ArrayList<>();
        String sql = "SELECT Country.CountryName AS CountryName, SUM(Population) AS Population FROM Country\n" +
                "JOIN State ON Country.CountryId = State.CountryId\n" +
                "JOIN City ON State.StateId = City.StateId\n" +
                "GROUP BY Country.CountryId";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // looping through the result set
            while (rs.next()) {
                populationData.add(new ImmutablePair<>(rs.getString("CountryName"),
                        rs.getInt("Population")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return populationData;
    }
}