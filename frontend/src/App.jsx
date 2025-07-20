import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from "react-leaflet";
import L from "leaflet";
import "leaflet/dist/leaflet.css";
import "./App.css";

import { useLocations } from "./hooks/useLocations";

// Markerite ikoonide parandus
L.Icon.Default.mergeOptions({
    iconRetinaUrl: "/leaflet/marker-icon-2x.png",
    iconUrl: "/leaflet/marker-icon.png",
    shadowUrl: "/leaflet/marker-shadow.png",
});

const bounds = [
    [57.5, 21.8],
    [59.7, 28.3]
];

function MapComponent() {
    // Kasutame oma hook'i, et saada andmed ja funktsioonid
    const { locations, isLoading, error, addLocation } = useLocations();

    const handleMapClick = async (e) => {
        const { lat, lng } = e.latlng;

        const southWest = L.latLng(bounds[0]);
        const northEast = L.latLng(bounds[1]);
        const mapBounds = L.latLngBounds(southWest, northEast);

        if (!mapBounds.contains([lat, lng])) {
            alert("Uut markerit ei saa lisada väljaspool lubatud ala.");
            return;
        }

        const description = prompt("Sisesta asukoha kirjeldus:");
        if (!description) return;

        const newLocationData = {
            description: description,
            location: {
                type: "Point",
                coordinates: [lng, lat],
            },
        };

        try {
            await addLocation(newLocationData);
        } catch (err) {
            alert(`Viga asukoha lisamisel: ${err.message}`);
        }
    };

    function ClickHandler() {
        useMapEvents({ click: handleMapClick });
        return null;
    }

    // Kuvame laadimise ja vea olekuid kasutajale
    if (isLoading) return <div className="status-overlay">Laen andmeid...</div>;
    if (error) return <div className="status-overlay">Viga andmete laadimisel: {error.message}</div>;

    return (
        <MapContainer
            center={[58.5953, 25.0136]}
            zoom={8}
            minZoom={8}
            style={{ height: "100%", width: "100%" }}
            maxBounds={bounds}
            maxBoundsViscosity={1.0}
        >
            <TileLayer
                attribution="© OpenStreetMap contributors"
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <ClickHandler />
            {locations.map((feature) => {
                const [lng, lat] = feature.geometry.coordinates;
                return (
                    <Marker key={feature.properties.id} position={[lat, lng]}>
                        <Popup>
                            <strong>{feature.properties.description}</strong>
                        </Popup>
                    </Marker>
                );
            })}
        </MapContainer>
    );
}

function App() {
    return (
        <div style={{ height: "100vh", width: "100vw" }}>
            <MapComponent />
        </div>
    );
}

export default App;