DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS phone;

CREATE TABLE contact (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
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
    phone_id INT AUTO_INCREMENT PRIMARY KEY,
    phone_type VARCHAR(15),
    phone_number VARCHAR(15),
    contact_id INT NOT NULL,
    CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`)
);

INSERT INTO contact (first_name, middle_name, last_name, street, city, state, zip, email) VALUES
    ('Robert', 'M', 'Holmes', '123 Whimsical St', 'Richmond', 'VA', '23227', 'test@email.com');

INSERT INTO phone (phone_type, phone_number, contact_id) VALUES
    ('mobile', '888-555-1212', 1),
    ('work', '888-111-5555', 1);
