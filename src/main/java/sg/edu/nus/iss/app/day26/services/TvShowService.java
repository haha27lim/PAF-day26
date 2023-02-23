package sg.edu.nus.iss.app.day26.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.day26.models.TvShow;
import sg.edu.nus.iss.app.day26.repositories.TvShowsRepository;

@Service
public class TvShowService {
    
    @Autowired
    private TvShowsRepository tvShowRepo;

    // Retrieves a list of TV shows with the given language, and returns the full details of each TV show.
    public List<TvShow> findAllTvShowsByLanguage(String lang) {
        return tvShowRepo.findTvShowsByLanguage(lang)
            .stream()
            .map(v -> TvShow.create(v))
            .toList();
     }
    
    
    // Retrieves a list of all TV show types.
    public List<String> getAllTypes() {
        return tvShowRepo.getTypes();
    }


    // Retrieves a list of TV shows with the given type, and returns a summary of each TV show.
    public List<TvShow> getShowsByType(String type) {
        return tvShowRepo.getShowsByType(type)
            .stream()
            .map(v -> TvShow.createSummary(v))
            .toList();
    }
    
}
