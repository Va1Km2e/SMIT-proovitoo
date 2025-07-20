import { useState, useEffect, useCallback } from 'react';
import { getLocations, createLocation } from '../services/api';

export const useLocations = () => {
    const [locations, setLocations] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    // Andmete pärimine esmasel laadimisel
    useEffect(() => {
        const fetchLocations = async () => {
            try {
                setIsLoading(true);
                setError(null);
                const data = await getLocations();
                setLocations(data.features || []);
            } catch (err) {
                setError(err);
            } finally {
                setIsLoading(false);
            }
        };

        fetchLocations();
    }, []);

    // Funktsioon uue asukoha lisamiseks.
    const addLocation = useCallback(async (locationData) => {
        try {
            const newLocationFeature = await createLocation(locationData);

            // Uuendame olekut lokaalselt, vältides kõigi andmete uuesti pärimist
            setLocations(prevLocations => [...prevLocations, newLocationFeature]);
        } catch (err) {
            console.error("Failed to add location:", err);
            throw err;
        }
    }, []);

    return { locations, isLoading, error, addLocation };
};