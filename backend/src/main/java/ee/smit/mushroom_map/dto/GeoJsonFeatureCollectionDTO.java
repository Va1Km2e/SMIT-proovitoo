package ee.smit.mushroom_map.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * Represents a GeoJSON FeatureCollection.
 * A FeatureCollection contains an array of features.
 */
@Data
public class GeoJsonFeatureCollectionDTO {

    /**
     * The type of GeoJSON object. For this application, it must always be "FeatureCollection".
     */
    @NotNull(message = "GeoJSON type must be 'FeatureCollection'")
    private String type = "FeatureCollection";

    /**
     * The list of features in the collection.
     */
    private List<GeoJsonFeatureDTO> features;
}
