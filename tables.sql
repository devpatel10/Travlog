use test;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS hotelbooking;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS travels;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS transportbooking;
DROP TABLE IF EXISTS trips;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    customer_id INT AUTO_INCREMENT,
    first_name VARCHAR(30) not null,
    last_name VARCHAR(30),
    gender VARCHAR(10) CHECK(gender IN ('Male', 'Female', 'Other')),
    address VARCHAR(100),
    mobile VARCHAR(10) not null CHECK(mobile NOT LIKE '%[^0-9]%'),
    emailid VARCHAR(50) not null CHECK(emailid LIKE '%_@_%._%'),
    password VARCHAR(30) not null,
    dob DATE,
    PRIMARY KEY(customer_id)
);

INSERT INTO customer(first_name, last_name, gender, address, mobile, emailid, password, dob)
VALUES ("Jake", "Peralta", "Male", "19/B Beach House, Mumbai", "1234567890", "jake@gmail.com", "password", "2000-06-15");


INSERT INTO customer(first_name, last_name, gender, address, mobile, emailid, password, dob)
VALUES ("Robin", "Hood", "Male", "23/B Rajiv Chowk, NCR Delhi", "9876543210", "robin@gmail.com", "password", "2014-06-15");


INSERT INTO customer(first_name, last_name, gender, address, mobile, emailid, password, dob)
VALUES ("Sakshi", "Vaidya", "Female", "21,Jodhpur Market, Jodhpur", "1456327890", "sakshi@gmail.com", "password", "2007-06-15");



CREATE TABLE trips(
    trip_id INT AUTO_INCREMENT,
    customer_id INT,
    title VARCHAR(30) not null,
    description VARCHAR(30),
    drive_link VARCHAR(100),
    start_date DATE not null,
    end_date DATE not null,
    PRIMARY KEY(trip_id),
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

INSERT INTO trips(title, description, start_date, end_date,customer_id)
VALUES ("Manali", "Trip to Manali", "2017-06-05", "2017-06-15","1");

INSERT INTO trips(title, description, start_date, end_date,customer_id)
VALUES ("Goa", "Trip to Goa", "2021-04-21", "2021-04-28","1");

INSERT INTO trips(title, description, start_date, end_date,customer_id)
VALUES ("Sikkim", "Trip to Sikkim", "2021-03-25", "2021-04-05","1");

INSERT INTO trips(title, description, start_date, end_date,customer_id)
VALUES ("Andaman", "Trip to Andaman", "2017-03-21", "2017-03-28","1");


CREATE TABLE transportbooking(
    tr_id INT AUTO_INCREMENT,
    trip_id INT,
    type VARCHAR(10) ,
    from_loc VARCHAR(30) NOT NULL,
    to_loc VARCHAR(30) NOT NULL,
    trans_name VARCHAR(50),
    cost INT DEFAULT 0,
    departure DATETIME NOT NULL,
    arrival DATETIME NOT NULL,
    PRIMARY KEY(tr_id),
    CHECK(type IN ('Bus', 'Train', 'Flight')),
    FOREIGN KEY(trip_id) REFERENCES trips(trip_id) ON DELETE CASCADE
);

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival)
VALUES ("1","Bus","Delhi","Dehradun","Redbus1","2000","2017-06-05 13:23:44", "2017-06-10 13:23:44");

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival)
VALUES ("1","Train","Dehradun","Manali","Shatabdi","2500","2017-06-10 13:23:44", "2017-06-15 13:23:44");

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival)
VALUES ("2","Flight","Ahmedabad","Mumbai","Spicejet","1000","2017-03-21 13:23:44", "2017-03-25 13:23:44");

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival)
VALUES ("2","Train","Mumbai","Goa","Indigo","2500","2017-03-25 13:23:44", "2017-03-28 13:23:44");

CREATE TABLE hotel(
    hotelid INT AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    rating INT DEFAULT 5,
    address VARCHAR(70),
    CHECK (rating>=0 AND rating<=5),
    PRIMARY KEY(hotelid)
);

INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Manali","3","1/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel2-Manali","4","2/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel3-Manali","5","3/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel4-Manali","5","4/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel5-Manali","5","12/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel6-Manali","1","12/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel7-Manali","2","12/Manali Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel8-Manali","3","12/Manali Road");

INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Goa","5","12/Vagator Beach");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel2-Goa","4","12/Kundle Beach");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel3-Goa","4","12/Palolem Beach");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel4-Goa","4","12/Baga Beach");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel6-Goa","2","12/Calangute Beach");

INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Andaman","5","Port Blair");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel2-Andaman","2","Port Blair");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel3-Andaman","4","Port Blair");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel4-Andaman","5","Port Blair");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel5-Andaman","1","Port Blair");

INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Sikkim","2","Gangtok");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel2-Sikkim","2","Gangtok");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel3-Sikkim","2","Gangtok");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel4-Sikkim","2","Gangtok");

INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Rishikesh","2","Gangtok");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Gwalior","3","Gwalior Main Road");
INSERT INTO hotel(name,rating,address)
VALUES ("Hotel1-Jodhpur","4","Near Fort");


CREATE TABLE hotelbooking(
    hotelbookid INT AUTO_INCREMENT,
    trip_id INT,
    hotelid INT,
    checkin DATETIME NOT NULL,
    checkout DATETIME NOT NULL,
    cost INT DEFAULT 0,
    PRIMARY KEY(hotelbookid),
    FOREIGN KEY(hotelid) REFERENCES hotel(hotelid) ON DELETE CASCADE,
    FOREIGN KEY(trip_id) REFERENCES trips(trip_id) ON DELETE CASCADE
);
