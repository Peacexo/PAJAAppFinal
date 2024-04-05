/**
 * Author: Peace Iyunade
 * Lab section: CST2335 022
 * Creation Date: 31st March 2024
 */
package algonquin.cst2335.pajaappfinal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class represents a model for a saved definition, which includes a search term and its associated definitions.
 * It defines the structure of the database table for saved definitions and provides type converters for converting
 * the list of definitions to and from JSON format.
 * Implements the Room annotations for defining the database table and type converters.
 * @author Peace Iyunade
 * @version March 31, 2024 (Final Version)
 */

@Entity(tableName = "saveddefinitions")
@TypeConverters(SavedDefinitionDic.DefinitionsConverter.class)
public class SavedDefinitionDic {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "search_term")
    private String searchTerm;

    @ColumnInfo(name = "definitions")
    private ArrayList<String> definitions;
    /**
     * Constructs a SavedDefinitionDic object with the specified search term and list of definitions.
     * @param searchTerm The search term.
     * @param definitions The list of definitions associated with the search term.
     */
    public SavedDefinitionDic(String searchTerm, ArrayList<String> definitions) {
        this.searchTerm = searchTerm;
        this.definitions = definitions;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }

    /**
     * This inner class provides TypeConverters for converting the list of definitions to and from JSON format.
     */
    public static class DefinitionsConverter {
        /**
         * Converts a JSON string to an ArrayList of strings.
         * @param value The JSON string representing the list of definitions.
         * @return The ArrayList of strings parsed from the JSON string.
         */
        @TypeConverter
        public static ArrayList<String> fromString(String value) {
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            return new Gson().fromJson(value, listType);
        }
        /**
         * Converts an ArrayList of strings to a JSON string.
         * @param list The ArrayList of strings to be converted.
         * @return The JSON string representation of the ArrayList.
         */
        @TypeConverter
        public static String fromArrayList(ArrayList<String> list) {
            return new Gson().toJson(list);
        }
    }
}