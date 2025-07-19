package ee.smit.mushroom_map.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * Represents a geographical point in GeoJSON format.
 * This DTO is used to transfer location coordinates and type information
 * for mushroom finding spots.
 */
@Data
public class GeoJsonPointDTO {

    /**
     * The type of GeoJSON geometry.
     * For this application, it must always be "Point".
     */
    @NotNull(message = "GeoJSON type must be 'Point'")
    private String type;

    /**
     * The coordinates of the point.
     * This list contains exactly two double values: [longitude, latitude].
     */
    @NotNull(message = "Coordinates must be provided")
    @Size(min = 2, max = 2, message = "Coordinates must contain exactly two values: [longitude, latitude]")
    private List<@NotNull Double> coordinates;
}