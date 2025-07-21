package ee.smit.mushroom_map.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a GeoJSON Feature.
 * A Feature contains a geometry and associated properties.
 */
@Data
public class GeoJsonFeatureDTO {

    @NotNull(message = "GeoJSON type must be 'Feature'")
    private String type = "Feature";

    @NotNull(message = "Geometry must be provided")
    private GeoJsonPointDTO geometry;

    private Properties properties;

    @Data
    public static class Properties {
        private Long id;
        private String description;
    }
}
