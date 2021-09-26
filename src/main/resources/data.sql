--DROP TABLE IF EXISTS address
DROP TABLE IF EXISTS contact;
--DROP TABLE IF EXISTS name
--DROP TABLE IF EXISTS phone

--CREATE TABLE address {}

CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    address VARCHAR(250) NOT NULL,
    phone VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL
);

INSERT INTO contact (name, address, phone, email) VALUES
    ('Matt Homles', '123 Seminary', '8885551212', 'test@testermail.com'),
    ('Cary Hogan', '990 Whatabout Street', '123999878', 'newWhat@testermail.com');
--
--CREATE TABLE name {}
--
--CREATE TABLE phone {}