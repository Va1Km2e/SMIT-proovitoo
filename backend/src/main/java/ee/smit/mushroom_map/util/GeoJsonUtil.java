package ee.smit.mushroom_map.util;

import ee.smit.mushroom_map.dto.GeoJsonFeatureDTO;
import ee.smit.mushroom_map.dto.GeoJsonPointDTO;
import ee.smit.mushroom_map.dto.LocationDTO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.Arrays;
import java.util.Map;

/**
 * This is a helper class for converting geographical point data.
 * It helps us switch between the GeoJSON format (used by our API)
 * and the JTS Point format (used internally for spatial operations).
 */
public class GeoJsonUtil {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    /**
     * Converts a GeoJSON Point DTO (data from the API) into a JTS Point object.
     *
     * @param geoJson The GeoJSON point data received from the API.
     * @return A JTS Point object representing the same geographical coordinates.
     * @throws IllegalArgumentException if the input GeoJSON is not a valid Point format.
     */
    public static Point convertToPoint(GeoJsonPointDTO geoJson) {
        if (!"Point".equals(geoJson.getType()) || geoJson.getCoordinates().size() != 2) {
            throw new IllegalArgumentException("Invalid GeoJSON Point format");
        }

        return geometryFactory.createPoint(new Coordinate(
                geoJson.getCoordinates().get(0), // Longitude
                geoJson.getCoordinates().get(1)  // Latitude
        ));
    }

    /**
     * Converts a JTS Point object (internal format) into a GeoJSON Point DTO (for the API).
     *
     * @param point The JTS Point object.
     * @return A GeoJSON Point DTO representing the same geographical coordinates.
     */
    public static GeoJsonPointDTO convertToGeoJson(Point point) {
        GeoJsonPointDTO dto = new GeoJsonPointDTO();
        dto.setType("Point"); // IMPORTANT: Added this line to ensure GeoJSON compliance
        dto.setCoordinates(Arrays.asList(point.getX(), point.getY()));
        return dto;
    }
}