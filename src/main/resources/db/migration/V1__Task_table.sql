CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(300) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    due_date DATE
);
