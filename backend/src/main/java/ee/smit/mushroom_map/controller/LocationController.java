package ee.smit.mushroom_map.controller;

import ee.smit.mushroom_map.dto.LocationDTO;
import ee.smit.mushroom_map.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Import this

import java.util.List;

/**
 * This class handles all the web requests for mushroom locations.
 * It's like the main entry point for users to interact with our mushroom map API.
 */
@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    /**
     * This is how we talk to the part of our app that handles the actual mushroom location logic.
     */
    private final LocationService locationService;

    /**
     * Creates a new mushroom location.
     * When you send a POST request to /api/location with location details, this method saves it.
     *
     * @param locationDTO The details of the new mushroom location (description, coordinates).
     * It must be valid according to our rules (e.g., description can't be empty).
     * @return The saved location with its new ID.
     */
    @PostMapping
    public LocationDTO create(@Valid @RequestBody LocationDTO locationDTO) {
        return locationService.createLocation(locationDTO);
    }

    /**
     * Gets all the mushroom locations we have saved.
     * When you send a GET request to /api/location, this method gives you a list of all locations.
     *
     * @return A list of all mushroom locations. It might be empty if there are no locations yet.
     */
    @GetMapping
    public List<LocationDTO> getAll() {
        return locationService.getAllLocations();
    }

    /**
     * Deletes a mushroom location.
     * If you send a DELETE request to /api/location/{id}, this method removes the location with that ID.
     *
     * @param id The ID of the location you want to delete.
     * @return The ID of the location that was deleted.
     * (Note: If the location isn't found, it currently throws a generic error. A specific "not found" error would be better.)
     */
    @DeleteMapping("/{id}")
    public Long deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }

    /**
     * Updates an existing mushroom location.
     * If you send a PUT request to /api/location/{id} with new details, this method updates that location.
     *
     * @param id The ID of the location you want to update.
     * @param dto The new details for the location (description, coordinates).
     * It must be valid according to our rules.
     * @return The updated location.
     * (Note: If the location isn't found, it currently throws a generic error. A specific "not found" error would be better.)
     */
    @PutMapping("/{id}")
    public LocationDTO update(@PathVariable Long id, @Valid @RequestBody LocationDTO dto) {
        return locationService.updateLocation(id, dto);
    }
}