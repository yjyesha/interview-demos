package com.quickbase.devint;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
/**
 * Created by joshiy on 07/11/17.
 */
public class DataMerger implements IDataMerger{
    @Override
    /**
     * Returns an unordered list of countries and their populations merged from two different sources as lists
     */
    public List<Pair<String, Integer>> GetMergedData(List<Pair<String, Integer>> populationDataApi,
                                                     List<Pair<String, Integer>> populationDataSql) {
        for (Pair p : populationDataApi) {
            if (!populationDataSql.contains(p)) {
                populationDataSql.add(p);
            }
        }
        return populationDataSql;
    }
}
