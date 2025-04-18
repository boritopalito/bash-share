CREATE SEQUENCE IF NOT EXISTS file_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE file
(
    id            BIGINT  NOT NULL,
    name          VARCHAR(255),
    size          DECIMAL,
    uploaded_name VARCHAR(255),
    is_downloaded BOOLEAN NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_file PRIMARY KEY (id)
);