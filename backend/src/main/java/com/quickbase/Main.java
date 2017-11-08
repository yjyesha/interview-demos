package com.quickbase;

import com.quickbase.devint.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * The main method of the executable JAR generated from this repository. This is to let you
 * execute something from the command-line or IDE for the purposes of demonstration, but you can choose
 * to demonstrate in a different way (e.g. if you're using a framework)
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("Starting.");
        System.out.print("Getting DB Connection...");

        List<Pair<String, Integer>> populationDataSql;
        List<Pair<String, Integer>> populationDataApi;
        IStatService statService = new ConcreteStatService();
        IStatService statService2 = new ConcreteDBStatService();

        populationDataSql = statService2.GetCountryPopulations();
        populationDataApi = statService.GetCountryPopulations();
        DataMerger dataMerger = new DataMerger();
        populationDataSql = dataMerger.GetMergedData(populationDataSql,populationDataApi);
        for (Pair<String, Integer> aPopulationDataSql : populationDataSql) {
            System.out.println(aPopulationDataSql.getKey() + " " + populationDataSql.get(0).getValue());
        }
        System.out.println(populationDataSql.size());
    }
}