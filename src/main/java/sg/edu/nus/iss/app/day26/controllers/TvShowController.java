package sg.edu.nus.iss.app.day26.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.day26.models.TvShow;
import sg.edu.nus.iss.app.day26.services.TvShowService;

@Controller
@RequestMapping(path="/tvshow")
public class TvShowController { 

    @Autowired
    private TvShowService tvshowSvc;


    // GET /tvshow?lang=English
    @GetMapping
    public String getTvShowByLanguage(@RequestParam(defaultValue="English") String lang, Model model) {
        
        // Retrieves list of TV shows matching the specified language and adds them to the model for use by the view
        List<TvShow> results = tvshowSvc.findAllTvShowsByLanguage(lang);
        model.addAttribute("tvshows", results);
        model.addAttribute("language", lang);

        return "tvshows";
    }


    // GET /tvshow/type/{type}
    @GetMapping(path="/type/{type}")
    public String getTvShowByType(@PathVariable String type, Model model) {

        // Retrieves list of TV shows matching the specified type and adds them to the model for use by the view
	    List<TvShow> results = tvshowSvc.getShowsByType(type);
	    model.addAttribute("tvshows", results);
	    model.addAttribute("showType", type);

	    return "tvshows-listing";
	}

}
