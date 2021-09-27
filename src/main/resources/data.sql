DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS phone;
--DROP TABLE IF EXISTS address;
--DROP TABLE IF EXISTS name;

CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(25),
    middle_name VARCHAR(25),
    last_name VARCHAR(25) NOT NULL,
    street VARCHAR(75) NOT NULL,
    city VARCHAR(25) NOT NULL,
    state VARCHAR(15) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    email VARCHAR(250) NOT NULL
);

CREATE TABLE phone (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone_type VARCHAR(15),
    phone_number VARCHAR(15)
);

INSERT INTO contact (first_name, middle_name, last_name, street, city, state, zip, email) VALUES
    ('Robert', 'M', 'Holmes', '123 Whimsical St', 'Richmond', 'VA', '23227', 'test@email.com');

INSERT INTO phone (phone_type, phone_number) VALUES
    ('mobile', '888-555-1212');
