-- Insertando usuarios
INSERT INTO usuario (username, email, password) VALUES
('juanperez', 'juan@example.com', 'password123'),
('maria123', 'maria@example.com', 'securepass'),
('carlosg', 'carlos@example.com', 'hashpass');

-- Insertando establecimientos
INSERT INTO establecimiento (nombre, descripcion, ubicacion, puntuacion) VALUES
('Café del Sol', 'Un acogedor café con los mejores desayunos.', 'Calle 123, Ciudad', 4.5),
('Panadería La Abuela', 'Pan fresco y café artesanal.', 'Avenida Central, Ciudad', 4.8),
('Brunch & Coffee', 'Delicioso brunch con opciones saludables.', 'Plaza Mayor, Ciudad', 4.7);

-- Insertando desayunos
INSERT INTO desayuno (nombre, precio, imagen, puntuacion, id_establecimiento) VALUES
('Tostadas con Aguacate', 5.99, 'img1.jpg', 4.6, 1),
('Café y Croissant', 3.50, 'img2.jpg', 4.3, 1),
('Desayuno Americano', 7.99, 'img3.jpg', 4.8, 2),
('Pancakes con Miel', 6.25, 'img4.jpg', 4.7, 2),
('Omelette de Espinaca', 6.75, 'img5.jpg', 4.5, 3);

-- Insertando reviews
INSERT INTO review (id_usuario, id_desayuno, fecha, precio, imagen, puntuacion, comentarios) VALUES
(1, 1, '2024-02-08 08:30:00', 5.99, 'review1.jpg', 3, 'Delicioso y saludable!'),
(2, 2, '2024-02-08 09:15:00', 3.50, 'review2.jpg', 1, 'Buen café, pero el croissant podría mejorar.'),
(3, 3, '2024-02-07 10:00:00', 7.99, 'review3.jpg', 2, 'Excelente combinación de sabores.'),
(1, 4, '2024-02-06 08:45:00', 6.25, 'review4.jpg', 4, 'Muy buenos pancakes, un poco dulces.'),
(2, 5, '2024-02-05 07:30:00', 6.75, 'review5.jpg', 5, 'Perfecto para un desayuno ligero y nutritivo.');