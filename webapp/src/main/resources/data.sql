
-- Insert 5 random rows into the patient table with famous movie heroes
INSERT INTO patient (ni_number, name, reg_date, surname)
VALUES
    (111, 'Luke', '2023-08-30', 'Skywalker'),
    (222, 'Harry', '2023-08-29', 'Potter'),
    (333, 'Indiana', '2023-08-28', 'Jones'),
    (444, 'Ellen', '2023-08-27', 'Ripley'),
    (555, 'Neo', '2023-08-26', 'Anderson');

-- Insert 5 random rows into the dentist table with famous movie villains
INSERT INTO dentist (license_number, name, surname)
VALUES
    (12345, 'Darth', 'Vader'),
    (54321, 'Hannibal', 'Lecter'),
    (98765, 'Joker', 'Nicholson'),
    (67890, 'Lord', 'Voldemort'),
    (13579, 'Darth', 'Maul');

-- Insert 5 random rows into the appointment table
INSERT INTO appointment (dentist_id, patient_id, date)
VALUES
    (2, 3, '2023/09/01 - 12:30'),
    (1, 2, '2023/09/02 - 14:30'),
    (3, 1, '2023/09/03 - 12:00'),
    (4, 5, '2023/09/04 - 16:00'),
    (2, 4, '2023/09/05 - 12:30');

-- Insert 5 random rows into the address table for patients with department code
INSERT INTO address (floor, street_num, patient_id, department, street_name)
VALUES
    (3, 123, 1, 'A', 'Main Street'),
    (2, 456, 2, 'B', 'First Avenue'),
    (5, 789, 3, 'C', 'Park Road'),
    (1, 987, 4, 'D', 'Elm Street'),
    (4, 567, 5, 'E', 'Broadway');

-- Insert 5 random rows into the usr table with famous movie hackers
INSERT INTO usr (admin, email, name, password, surname)
VALUES
    (true, 'admin@example.com', 'Neo', 'admin123', 'Matrix'),
    (true, 'admin2@example.com', 'Elliott', 'admin234', 'Alderson'),
    (false, 'user1@example.com', 'Trinity', 'userpass1', 'Matrix'),
    (false, 'user2@example.com', 'Lisbeth', 'userpass2', 'Salander'),
    (false, 'user3@example.com', 'Morpheus', 'userpass3', 'Matrix');
