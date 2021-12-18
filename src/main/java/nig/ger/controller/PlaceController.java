package nig.ger.controller;

import nig.ger.dao.PlaceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlaceController {
    private PlaceDAO placeDAO;

    public PlaceController(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    @GetMapping("/place/{placeId}")
    public ModelAndView getPlace(@PathVariable Long placeId) {
        return new ModelAndView("place", "niggerList", placeDAO.selectAllPlacesById(placeId));
    }
}
