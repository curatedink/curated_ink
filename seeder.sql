CREATE DATABASE curated_ink_db;

USE curated_ink_db;

INSERT INTO users (biography, display_name, email, is_artist, password, studio_name, username, zipcode) VALUES
('artist here', 'bob', 'bob@bob.com', true, '123', 'Ghibli', 'bob', 123),
('canvas here', 'rob', 'rob@rob.com', false, '123', '', 'rob', 123);

INSERT INTO images (comment, credited_artist, image_url, is_canvas, is_profile_image, studio_name) VALUES
('nice pic', 'bob', 'https://images.pexels.com/photos/1645230/pexels-photo-1645230.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260', false, true, 'Ghibli'),
('cool pic', 'bob', 'https://images.pexels.com/photos/1527009/pexels-photo-1527009.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260', false, false, 'Ghibli'),
('', 'jen', 'https://images.pexels.com/photos/4360178/pexels-photo-4360178.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260', true, false, 'Ross Studio');

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
('Stick and Poke'),
('Surrealism'),
('Realism'),
('Tribal'),
('Trash Polka'),
('Watercolor');