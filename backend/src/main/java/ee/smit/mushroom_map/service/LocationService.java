package ee.smit.mushroom_map.service;

import ee.smit.mushroom_map.dto.LocationDTO;
import ee.smit.mushroom_map.entity.LocationEntity;
import ee.smit.mushroom_map.mapper.LocationMapper;
import ee.smit.mushroom_map.repository.LocationRepository;
import ee.smit.mushroom_map.util.GeoJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the main logic for our mushroom locations.
 */
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    /**
     * Gets all the mushroom locations from the database.
     *
     * @return A list of all mushroom locations, converted to the API-friendly format.
     */
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new mushroom location and saves it to the database.
     *
     * @param dto The mushroom location data received from the API.
     * @return The newly created location, including its ID from the database.
     */
    public LocationDTO createLocation(LocationDTO dto) {
        LocationEntity entity = locationMapper.toEntity(dto);
        locationRepository.save(entity);
        return locationMapper.toDTO(entity);
    }

    /**
     * Deletes a mushroom location from the database using its ID.
     *
     * @param id The ID of the location to delete.
     * @return The ID of the deleted location.
     * (Note: If the ID doesn't exist, it will currently cause an error. Better error handling would be good here.)
     */
    public Long deleteLocation(Long id) {
        locationRepository.deleteById(id);
        return id;
    }

    /**
     * Updates an existing mushroom location in the database.
     *
     * @param id The ID of the location to update.
     * @param dto The new data for the location.
     * @return The updated location.
     * @throws RuntimeException if no location with the given ID is found.
     * (Note: A more specific custom exception would be better for clarity.)
     */
    public LocationDTO updateLocation(Long id, LocationDTO dto) {

        LocationEntity entity = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        entity.setDescription(dto.getDescription());
        entity.setGeom(GeoJsonUtil.convertToPoint(dto.getLocation()));

        return locationMapper.toDTO(locationRepository.save(entity));
    }
}