CREATE TABLE employee (
    id SERIAL NOT NULL PRIMARY KEY,
    full_name  VARCHAR(256),
    short_name VARCHAR(64),
    age integer,
    city VARCHAR(64)
);

CREATE TABLE users (
    id SERIAL NOT NULL PRIMARY KEY,
    "name"  VARCHAR(64),
    password VARCHAR(64),
    active integer,
    employee_id integer references employee
);

CREATE TABLE skills (
    id SERIAL NOT NULL PRIMARY KEY,
    employee_id integer references employee,
    "name"  VARCHAR(64),
    description VARCHAR(1000)   
);

CREATE INDEX i_users_empl ON users  (employee_id);
CREATE INDEX i_skill_empl ON skills (employee_id);