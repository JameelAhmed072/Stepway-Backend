--liquibase formatted sql
--changeset jameel:version1

-- Insert Dummy User Data
            INSERT INTO user (first_name, last_name, email, password, phone_number, role)
            VALUES ('Jameel', 'Ahmed', 'jameel.ahmed@example.com', 'password123', '1234567890', 'Student');

            INSERT INTO user (first_name, last_name, email, password, phone_number, role)
            VALUES ('Waji', 'ul Hassan', 'waji.ul.hassan@example.com', 'password456', '9876543210', 'ADMIN');

-- Insert Dummy Profile Data
            INSERT INTO profile (bio, user_id)
            VALUES ('I am Java Developer', 1);

            INSERT INTO profile (bio, user_id)
            VALUES ('I am Data Scientist', 2);
