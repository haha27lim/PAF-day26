package sg.edu.nus.iss.app.day26.models;

import static sg.edu.nus.iss.app.day26.Constants.*;

import org.bson.Document;

public class TvShow {
    
    private int id;
    private String name;
    private String url;
    private Object rating;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Object getRating() {
        return rating;
    }
    public void setRating(Object rating) {
        this.rating = rating;
    }

    public static TvShow create(Document doc) {
        TvShow tvShow = new TvShow();
        tvShow.setId(doc.getInteger(FIELD_ID));
        tvShow.setName(doc.getString(FIELD_NAME));
        tvShow.setUrl(doc.getString(FIELD_URL));
        return tvShow;
    }
    
    public static TvShow createSummary(Document doc) {
        TvShow tvShow = new TvShow();
        tvShow.setId(doc.getInteger(FIELD_ID));
        tvShow.setName(doc.getString(FIELD_NAME));
		  Document d = (Document)doc.get(FIELD_RATING);
          tvShow.setRating(d.getDouble(FIELD_AVERAGE));       
        return tvShow;
    }
}
