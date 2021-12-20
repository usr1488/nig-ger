package nig.ger.service;

import nig.ger.entity.Place;
import nig.ger.exception.PlaceNotFoundException;
import nig.ger.repository.PlaceRepository;
import nig.ger.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static nig.ger.util.Constants.IMAGE_FORMAT;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void savePlace(Place place, MultipartFile image) throws IOException {
        ImageIO.write(
                ImageIO.read(image.getInputStream()),
                IMAGE_FORMAT,
                new File(Constants.PLACE_IMAGE_LOCATION + placeRepository.save(place).getPlaceId() + "." + IMAGE_FORMAT)
        );
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place getPlaceById(long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() -> new PlaceNotFoundException(placeId));
    }
}
