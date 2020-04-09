show databases;
CREATE DATABASE parking_lot_sys DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use parking_lot_sys;

CREATE TABLE parking_lot_info
(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    parking_lot_id CHAR(1) NOT NULL,
    parking_space_number INT NOT NULL
);

CREATE TABLE parking_space_info
(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    parking_lot_id CHAR(1) NOT NULL,
    parking_space_id INT NOT NULL,
    car_numbers VARCHAR(8) NOT NULL
);
