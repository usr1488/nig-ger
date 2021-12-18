package nig.ger.service;

import nig.ger.PlaceRepository;
import nig.ger.entity.Place;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void savePlace(Place place) {
        placeRepository.save(place);
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}
