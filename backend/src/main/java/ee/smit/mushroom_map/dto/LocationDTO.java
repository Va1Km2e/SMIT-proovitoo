package ee.smit.mushroom_map.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the data for a mushroom location.
 * This DTO (Data Transfer Object) is used to send and receive
 * mushroom location details between the API and the client.
 */
@Data
@Builder
public class LocationDTO {

    /**
     * The unique ID for this mushroom location.
     * This is set by the system when a new location is created.
     */
    private Long id;

    /**
     * A text description of the mushroom location.
     * This is where you can write about what kind of mushrooms are found here.
     * It must not be empty and should be between 2 and 100 characters long.
     */
    @NotNull(message = "Description is required")
    @Size(min = 2, max = 100)
    private String description;

    /**
     * The geographical coordinates of the mushroom location.
     * This uses the GeoJSON Point format to specify the exact spot on the map.
     * This field is required.
     */
    @NotNull(message = "Location is required")
    private GeoJsonPointDTO location;
}