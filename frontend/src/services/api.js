const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

/**
 * Abifunktsioon, et käsitleda fetch päringu vastuseid.
 * Kontrollib, kas vastus oli edukas, ja viskab vea, kui ei olnud.
 * @param {Response} response - Fetch API vastus
 * @returns {Promise<any>} - JSON-iks parsitud vastus
 */
const handleResponse = async (response) => {
    if (!response.ok) {
        const error = await response.json().catch(() => ({ message: response.statusText }));
        throw new Error(error.message || 'Midagi läks valesti');
    }
    return response.json();
};

/**
 * Pärineb kõik asukohad API-st.
 * @returns {Promise<GeoJSON.FeatureCollection>} - Asukohtade GeoJSON FeatureCollection
 */
export const getLocations = async () => {
    const response = await fetch(`${API_BASE_URL}/location`);
    return handleResponse(response);
};

/**
 * Loob uue asukoha.
 * @param {object} locationData - Uue asukoha andmed (kirjeldus ja asukoht)
 * @returns {Promise<GeoJSON.Feature>} - API poolt tagastatud loodud asukoht
 */
export const createLocation = async (locationData) => {
    const response = await fetch(`${API_BASE_URL}/location`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(locationData),
    });
    return handleResponse(response);
};