package sg.edu.nus.iss.app.day26.repositories;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static sg.edu.nus.iss.app.day26.Constants.*;


@Repository
public class TvShowsRepository {


    @Autowired
    private MongoTemplate template;

    /*
     * db.tv.find({ langage: "English" })
     */
    public List<Document> findTvShowsByLanguage(String lang) {
        // Create a criteria - { language: { $regex: 'english', $options: 'i' } }
        Criteria criterial = Criteria.where(FIELD_LANGUAGE).regex(lang, "i");

        // Create a query using the criteria
        Query query = Query.query(criterial);

        // Perform the query and return the results as a list of documents
        return template.find(query, Document.class, COLLECTION_TVSHOWS);
    }

    
    /*
     * db.tv.distinct('type')
     */
    public List<String> getTypes() {
        List<String> result = template.findDistinct(new Query(), FIELD_TYPE, COLLECTION_TVSHOWS, String.class);
        Collections.sort(result);
        return result;
    }


    /*
	    db..find({
			type: { $regex: 'Animation', $options: 'i' }
		})
		.sort({ "rating.average": -1 })
		.projection({ _id: 0, id: 1, name: 1, "rating.average": 1 })
		.limit(20)
	  */
    public List<Document> getShowsByType(String type) {
        return getShowsByType(type, 20, 0);
    }
    public List<Document> getShowsByType(String type, int limit, int skip) {

        // Build a criteria based on the type parameter
        Criteria criteria = Criteria.where(FIELD_TYPE)
                .regex(type, "i");

        // Build a query using the criteria, sorting by average rating and applying limit/skip
        Query query = Query.query(criteria)
            .with(Sort.by(Direction.ASC, FIELD_RATING_AVERAGE))
            .limit(limit)
            .skip(skip);

        // Specify the fields to include/exclude in the query results
        query.fields()
            .exclude(FIELD_UNDERSCORE_ID)
            .include(FIELD_ID, FIELD_NAME, FIELD_RATING_AVERAGE);

        // Execute the query and return the results as a list of documents
        return template.find(query, Document.class, COLLECTION_TVSHOWS);            
    }
    
}
