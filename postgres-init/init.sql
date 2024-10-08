CREATE TABLE vessels (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    class VARCHAR(255),
    captain VARCHAR(255),
    launched_year INT
);