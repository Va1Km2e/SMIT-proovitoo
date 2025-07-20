package ee.smit.mushroom_map.service;

import ee.smit.mushroom_map.exception.ResourceNotFoundException;
import ee.smit.mushroom_map.dto.GeoJsonFeatureCollectionDTO;
import ee.smit.mushroom_map.dto.GeoJsonFeatureDTO;
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
    public GeoJsonFeatureCollectionDTO getAllAsGeoJson() {
        List<LocationDTO> locations = getAllLocations();
        List<GeoJsonFeatureDTO> features = locations.stream()
                .map(locationMapper::toGeoJsonFeature)
                .collect(Collectors.toList());

        GeoJsonFeatureCollectionDTO collection = new GeoJsonFeatureCollectionDTO();
        collection.setFeatures(features);
        return collection;
    }

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
    public GeoJsonFeatureDTO createLocation(LocationDTO dto) {
        LocationEntity entity = locationMapper.toEntity(dto);
        LocationEntity savedEntity = locationRepository.save(entity);
        LocationDTO savedDto = locationMapper.toDTO(savedEntity);
        return locationMapper.toGeoJsonFeature(savedDto);
    }


    /**
     * Deletes a mushroom location from the database using its ID.
     *
     * @param id The ID of the location to delete.
     * @return The ID of the deleted location.
     * @throws ResourceNotFoundException if no location with the given ID is found.
     */
    public Long deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Location with ID " + id + " not found");
        }
        locationRepository.deleteById(id);
        return id;
    }

    /**
     * Updates an existing mushroom location in the database.
     *
     * @param id The ID of the location to update.
     * @param dto The new data for the location.
     * @return The updated location.
     * @throws ResourceNotFoundException if no location with the given ID is found.
     */
    public LocationDTO updateLocation(Long id, LocationDTO dto) {
        LocationEntity entity = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + id + " not found")); // MUUDETUD RIDA

        entity.setDescription(dto.getDescription());
        entity.setGeom(GeoJsonUtil.convertToPoint(dto.getLocation()));

        return locationMapper.toDTO(locationRepository.save(entity));
    }
}