CREATE DATABASE curated_ink_db;

USE curated_ink_db;

INSERT INTO users (biography, display_name, email, is_artist, password, studio_name, username, zipcode) VALUES
('artist here', 'bob', 'bob@bob.com', true, '123', 'Ghibli', 'bob', 123),
('canvas here', 'rob', 'rob@rob.com', false, '123', '', 'rob', 123);

INSERT INTO images (comment, credited_artist, image_url, is_canvas, is_profile_image, studio_name) VALUES
('nice pic', 'bob', 'https://i.imgur.com/oFIc11c.jpeg', false, true, 'Ghibli'),
('cool pic', 'bob', 'https://i.imgur.com/FiuVx2J.jpeg', false, false, 'Ghibli'),
('', 'jen', 'https://i.imgur.com/W9jF6Zw.jpeg', true, false, 'Ross Studio');

INSERT INTO styles (style) VALUES
('Bio-mechanical'),
('Black and Grey'),
('Blackwork'),
('Fineline'),
('Geometric'),
('Illustrative'),
('Japanese (Irezumi)'),
('Lettering'),
('Portraiture'),
('Neo Traditional'),
('New School'),
('Old School (Traditional)'),
('Other'),
('Surrealism'),
('Realism'),
('Tribal'),
('Trash Polka'),
('Watercolor');