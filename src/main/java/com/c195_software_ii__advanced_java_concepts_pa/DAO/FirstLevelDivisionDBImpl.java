package com.c195_software_ii__advanced_java_concepts_pa.DAO;

import com.c195_software_ii__advanced_java_concepts_pa.Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database Access Object for FirstLevelDivision Queries.
 * FirstLevelDivisions are fetched form the database and turned into FirstLevelDivision Objects for use in
 * ComboBoxes when adding or updating a Customer, and for displaying in TableViews.
 *
 * @author Kyle Heckard
 * @version 1.0
 * @see FirstLevelDivision
 */
public class FirstLevelDivisionDBImpl {

    static PreparedStatement  preparedStatement;
    static ResultSet          result;
    static FirstLevelDivision division;

    /**
     * Fetches all FirstLevelDivisions from the Database.
     * A FirstLevelDivision Object is created from each result in the query. Each FirstLevelDivision is added to
     * ObservableList divisions. The ObservableList is returned.
     *
     * @return <code>divisions</code>
     * @throws SQLException if Query fails
     */
    public static ObservableList<FirstLevelDivision> getFirstLevelDivisions() throws SQLException {
        try {
            // Setup Select query, ObservableList, then execute
            String sqlSelect = "SELECT * FROM first_level_divisions"; // Query
            ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList(); // List for storing FirstLevelDivision Objects
            divisions.clear(); // Clear list, prevents potential duplicates
            preparedStatement = JDBC.connection.prepareStatement(sqlSelect);
            result = preparedStatement.executeQuery();

            // Retrieve results, Create FirstLevelDivision Objects, then store in ObservableList
            while (result.next()) { // For each result in query

                // Store each value from result
                int    divisionID   = result.getInt   ("Division_ID");
                String divisionName = result.getString("Division");
                int    countryID    = result.getInt   ("Country_ID");

                // Create FirstLevelDivision Object using stored values and add to ObservableList
                division = new FirstLevelDivision(divisionID, divisionName, countryID);
                divisions.add(division);
            }
            // return ObservableList
            return divisions;
        }
        // return null upon exception or if no results
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}