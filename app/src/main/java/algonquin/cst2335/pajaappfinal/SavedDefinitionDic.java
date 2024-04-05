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

@Entity(tableName = "saveddefinitions")
@TypeConverters(SavedDefinitionDic.DefinitionsConverter.class)
public class SavedDefinitionDic {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "search_term")
    private String searchTerm;

    @ColumnInfo(name = "definitions")
    private ArrayList<String> definitions;

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


    public static class DefinitionsConverter {
        @TypeConverter
        public static ArrayList<String> fromString(String value) {
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public static String fromArrayList(ArrayList<String> list) {
            return new Gson().toJson(list);
        }
    }
}