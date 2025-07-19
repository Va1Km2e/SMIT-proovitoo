package ee.smit.mushroom_map.mapper;

import ee.smit.mushroom_map.dto.LocationDTO;
import ee.smit.mushroom_map.entity.LocationEntity;
import org.springframework.stereotype.Component;
import ee.smit.mushroom_map.util.GeoJsonUtil;


/**
 * This class helps convert between our database format (LocationEntity)
 * and the format we use for sending data over the API (LocationDTO).
 */
@Component
public class LocationMapper {

    /**
     * Converts a database entity (LocationEntity) into an API data object (LocationDTO).
     * This is used when we read data from the database and want to send it to a user.
     *
     * @param entity The mushroom location data from the database.
     * @return The mushroom location data ready to be sent via the API.
     */
    public LocationDTO toDTO(LocationEntity entity) {
        return LocationDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .location(GeoJsonUtil.convertToGeoJson(entity.getGeom()))
                .build();
    }

    /**
     * Converts an API data object (LocationDTO) into a database entity (LocationEntity).
     * This is used when we receive data from a user and want to save it to the database.
     *
     * @param dto The mushroom location data received from the API.
     * @return The mushroom location data ready to be saved in the database.
     */
    public LocationEntity toEntity(LocationDTO dto) {
        return LocationEntity.builder()
                .description(dto.getDescription())
                .geom(GeoJsonUtil.convertToPoint(dto.getLocation()))
                .build();
    }
}