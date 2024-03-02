INSERT INTO hotel (nombre, descripcion, categoria, piscina, localidad)
VALUES('Hotel Mont-Sant', 'Situado en la montaña del castillo de Xátiva',
       '5 estrellas', false, 'Xàtiva');
INSERT INTO hotel (nombre, descripcion, categoria, piscina, localidad)
VALUES('La Galiana Golf Resort', 'Situado en la Barraca de Aguas Vivas',
       '4 estrellas', true, 'Aguas Vivas');
INSERT INTO hotel (nombre, descripcion, categoria, piscina, localidad)
VALUES('Hotel MR Costa Blanca', 'Situado en el encantador puerto pesquero',
       '3 estrellas', false, 'Denia');

INSERT INTO habitacion (tamano, precio, desayuno, ocupada, id_hotel)
VALUES(2, 75.50, true, false, 1);
INSERT INTO habitacion (tamano, precio, desayuno, ocupada, id_hotel)
VALUES(1, 50.99, true, false, 1);
INSERT INTO habitacion (tamano, precio, desayuno, ocupada, id_hotel)
VALUES(2, 175.50, true, false, 2);
