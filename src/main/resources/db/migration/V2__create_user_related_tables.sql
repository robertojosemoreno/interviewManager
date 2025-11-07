CREATE TABLE IF NOT EXISTS users(
    id UUID NOT NULL PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

ALTER TABLE interview
DROP COLUMN company_id;

ALTER TABLE interview
ADD CONSTRAINT position_id_fk FOREIGN KEY (position_id) REFERENCES open_position (id);

ALTER TABLE open_position
ADD COLUMN company_id UUID NOT NULL;

ALTER TABLE open_position
ADD CONSTRAINT company_id_fk FOREIGN KEY (company_id) REFERENCES company (id);


CREATE TABLE IF NOT EXISTS position_candidates(
    id UUID NOT NULL PRIMARY KEY,
    position_id UUID NOT NULL,
    user_id  UUID NOT NULL
);

ALTER TABLE position_candidates
ADD CONSTRAINT pc_position_id_fk FOREIGN KEY (position_id) REFERENCES open_position(id);

ALTER TABLE position_candidates
ADD CONSTRAINT pc_user_id_fk FOREIGN KEY (user_id) REFERENCES users(id);


