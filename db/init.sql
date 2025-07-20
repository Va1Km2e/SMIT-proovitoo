CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE IF NOT EXISTS location (
  id SERIAL PRIMARY KEY,
  description VARCHAR(200) NOT NULL,
  geom geometry(Point, 4326)
);