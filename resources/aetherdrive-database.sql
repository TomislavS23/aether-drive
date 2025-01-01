-- CREATE DATABASE aetherdrive;

CREATE TABLE "user"
(
    id_user         SERIAL PRIMARY KEY,
    unique_id       UUID    DEFAULT gen_random_uuid(),
    username        VARCHAR(50) UNIQUE     NOT NULL,
    email           VARCHAR(100) UNIQUE    NOT NULL,
    hashed_password BYTEA                  NOT NULL,
    salt            BYTEA                  NOT NULL,
    iterations      INTEGER DEFAULT 700001 NOT NULL,
    created_at      DATE    DEFAULT CURRENT_DATE
);

CREATE TABLE folder
(
    id_folder   SERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    favourite   BOOLEAN DEFAULT FALSE,
    created_at  DATE    DEFAULT CURRENT_DATE,
    last_edited DATE    DEFAULT CURRENT_DATE,
    parent_id   INTEGER REFERENCES folder (id_folder) NULL,
    user_id     INTEGER REFERENCES "user" (id_user)
);

CREATE TABLE file
(
    id_file      SERIAL PRIMARY KEY,
    name         TEXT        NOT NULL,
    favourite    BOOLEAN DEFAULT FALSE,
    size         DECIMAL     NOT NULL,
    mimetype     TEXT        NOT NULL,
    storage_path TEXT UNIQUE NOT NULL,
    uploaded_at  DATE    DEFAULT CURRENT_DATE,
    last_edited DATE    DEFAULT CURRENT_DATE,
    folder_id    INTEGER REFERENCES folder (id_folder),
    user_id      INTEGER REFERENCES "user" (id_user)
);