package pl.epiklp.metalgenres;

import java.util.ArrayList;

/**
 * Created by epiklp on 03.05.18.
 */

public class Genre {
    private String                  name        = null;
    private String                  describe    = null;
    private String                  url         = null;
    private String                  year        = null;
    private ArrayList<Genre>        mGenre      = null;

    public Genre(String name, String describe, String year, ArrayList<Genre> genres){
        this.name = name;
        this.describe = describe;
        this.year = year;
        mGenre = genres;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<Genre> getmGenre() {
        return mGenre;
    }
}
