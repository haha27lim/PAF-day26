package sg.edu.nus.iss.app.day26.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.app.day26.services.TvShowService;

@Controller
@RequestMapping(path={"/", "/index.html"})
public class IndexController {
    
    @Autowired
    private TvShowService tvshowSvc;

    // Method handles GET requests for the root context path ("/") and returns the "Index" view to render
    @GetMapping
    public String getIndex(Model model) {
        // Add list of show types to the model, which can be used by the view to display the data
        List<String> showTypes = tvshowSvc.getAllTypes();
        model.addAttribute("showTypes", showTypes);
        return "index";
    }
}
