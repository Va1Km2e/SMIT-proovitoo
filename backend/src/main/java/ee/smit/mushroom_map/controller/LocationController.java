package ee.smit.mushroom_map.controller;

import ee.smit.mushroom_map.dto.LocationDTO;
import ee.smit.mushroom_map.dto.GeoJsonFeatureDTO;
import ee.smit.mushroom_map.dto.GeoJsonFeatureCollectionDTO;
import ee.smit.mushroom_map.dto.GeoJsonPointDTO;
import ee.smit.mushroom_map.mapper.LocationMapper;
import ee.smit.mushroom_map.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles all the web requests for mushroom locations.
 */
@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public GeoJsonFeatureDTO create(@Valid @RequestBody LocationDTO locationDTO) {
        return locationService.createLocation(locationDTO);
    }

    @GetMapping
    public GeoJsonFeatureCollectionDTO getAll() {
        return locationService.getAllAsGeoJson();
    }


    @DeleteMapping("/{id}")
    public Long deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }

    @PutMapping("/{id}")
    public GeoJsonFeatureDTO update(@PathVariable Long id, @Valid @RequestBody LocationDTO dto) {
        return locationService.updateLocation(id, dto);
    }
}