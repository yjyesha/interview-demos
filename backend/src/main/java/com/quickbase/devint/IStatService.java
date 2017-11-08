package com.quickbase.devint;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
/**
 * Created by joshiy on 07/11/17.
 */
public interface IStatService {
	/**
	 * Returns an unordered list of countries and their populations from a specific sources
	 */
	List<Pair<String, Integer>> GetCountryPopulations();
}
