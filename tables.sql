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
    status INT  DEFAULT 0, 
    PRIMARY KEY(trip_id),
    CHECK (status in (0,1)),
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

INSERT INTO trips(title, description, start_date, end_date,customer_id,status)
VALUES ("Manali", "Trip to Manali", "2017-06-05", "2017-06-15","1","1");

INSERT INTO trips(title, description, start_date, end_date,customer_id,status)
VALUES ("Goa", "Trip to Goa", "2021-04-21", "2021-04-28","1","0");

INSERT INTO trips(title, description, start_date, end_date,customer_id,status)
VALUES ("Sikkim", "Trip to Sikkim", "2021-03-25", "2021-04-05","1","0");

INSERT INTO trips(title, description, start_date, end_date,customer_id,status)
VALUES ("Andaman", "Trip to Andaman", "2017-03-21", "2017-03-28","1","1");


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
    status INT  DEFAULT 0, 
    ticket VARCHAR(255),
    PRIMARY KEY(tr_id),
    CHECK (status in (0,1)),
    CHECK(type IN ('Bus', 'Train', 'Flight')),
    FOREIGN KEY(trip_id) REFERENCES trips(trip_id) ON DELETE CASCADE
);

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival,status)
VALUES ("1","Bus","Delhi","Dehradun","Redbus1","2000","2017-06-05 13:23:44", "2017-06-10 13:23:44","1");

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival,status)
VALUES ("1","Train","Dehradun","Manali","Shatabdi","2500","2017-06-10 13:23:44", "2017-06-15 13:23:44","1");

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival,status)
VALUES ("2","Flight","Ahmedabad","Mumbai","Spicejet","1000","2017-03-21 13:23:44", "2017-03-25 13:23:44","1");

INSERT INTO transportbooking(trip_id, type, from_loc, to_loc,trans_name,cost,departure,arrival,status)
VALUES ("2","Train","Mumbai","Goa","Indigo","2500","2017-03-25 13:23:44", "2017-03-28 13:23:44","1");

CREATE TABLE location(
    location_id INT AUTO_INCREMENT,
    place_name VARCHAR(40) NOT NULL,
    PRIMARY KEY(location_id)
);

INSERT INTO location(place_name)
VALUES ("Manali");

INSERT INTO location(place_name)
VALUES ("Goa");

INSERT INTO location(place_name)
VALUES ("Andaman");

INSERT INTO location(place_name)
VALUES ("Sikkim");

INSERT INTO location(place_name)
VALUES ("Rishikesh");

INSERT INTO location(place_name)
VALUES ("Gwalior");

INSERT INTO location(place_name)
VALUES ("Jodhpur");

CREATE TABLE travels(
    location_id INT ,
    trip_id INT ,
    FOREIGN KEY(location_id) REFERENCES location(location_id) ON DELETE CASCADE,
    FOREIGN KEY(trip_id) REFERENCES trips(trip_id) ON DELETE CASCADE
);

INSERT INTO travels(location_id,trip_id)
VALUES ("1","1");

INSERT INTO travels(location_id,trip_id)
VALUES ("3","1");

INSERT INTO travels(location_id,trip_id)
VALUES ("5","1");

INSERT INTO travels(location_id,trip_id)
VALUES ("2","2");

INSERT INTO travels(location_id,trip_id)
VALUES ("3","2");

INSERT INTO travels(location_id,trip_id)
VALUES ("1","2");

INSERT INTO travels(location_id,trip_id)
VALUES ("3","3");

INSERT INTO travels(location_id,trip_id)
VALUES ("1","3");

INSERT INTO travels(location_id,trip_id)
VALUES ("4","4");


CREATE TABLE hotel(
    hotelid INT AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    location_id INT,
    rating INT DEFAULT 5,
    address VARCHAR(70),
    CHECK (rating>=0 AND rating<=5),
    PRIMARY KEY(hotelid),
    FOREIGN KEY(location_id) REFERENCES location(location_id) ON DELETE CASCADE
);

INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Manali","1","3","1/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel2-Manali","1","4","2/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel3-Manali","1","5","3/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel4-Manali","1","5","4/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel5-Manali","1","5","12/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel6-Manali","1","1","12/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel7-Manali","1","2","12/Manali Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel8-Manali","1","3","12/Manali Road");

INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Goa","2","5","12/Vagator Beach");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel2-Goa","2","4","12/Kundle Beach");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel3-Goa","2","4","12/Palolem Beach");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel4-Goa","2","4","12/Baga Beach");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel6-Goa","2","2","12/Calangute Beach");

INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Andaman","3","5","Port Blair");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel2-Andaman","3","2","Port Blair");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel3-Andaman","3","4","Port Blair");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel4-Andaman","3","5","Port Blair");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel5-Andaman","3","1","Port Blair");

INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Sikkim","4","2","Gangtok");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel2-Sikkim","4","2","Gangtok");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel3-Sikkim","4","2","Gangtok");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel4-Sikkim","4","2","Gangtok");

INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Rishikesh","5","2","Gangtok");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Gwalior","6","3","Gwalior Main Road");
INSERT INTO hotel(name,location_id,rating,address)
VALUES ("Hotel1-Jodhpur","7","4","Near Fort");


CREATE TABLE hotelbooking(
    hotelbookid INT AUTO_INCREMENT,
    trip_id INT,
    hotelid INT,
    checkin DATETIME NOT NULL,
    checkout DATETIME NOT NULL,
    cost INT DEFAULT 0,
    id_card VARCHAR(255),
    booking_doc VARCHAR(255),
    status INT  DEFAULT 0, 
    CHECK (status in (0,1)),
    PRIMARY KEY(hotelbookid),
    FOREIGN KEY(hotelid) REFERENCES hotel(hotelid) ON DELETE CASCADE,
    FOREIGN KEY(trip_id) REFERENCES trips(trip_id) ON DELETE CASCADE
);
CREATE TABLE notifications(
    note_id INT AUTO_INCREMENT,
    trip_id INT,
    category INT NOT NULL,
    time_ DATETIME NOT NULL,
    CHECK (category<=10),
    PRIMARY KEY(note_id),
    FOREIGN KEY(trip_id) REFERENCES trips(trip_id) ON DELETE CASCADE

);