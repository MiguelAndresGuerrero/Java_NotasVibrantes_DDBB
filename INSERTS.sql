use notasVibrantes;

-- tabla Clientes
INSERT INTO Clientes (ID, Nombre, Apellido, Correo, Telefono) VALUES
(1, 'Juan', 'Pérez', 'juan.perez@email.com', 123456789),
(2, 'Ana', 'López', 'ana.lopez@email.com', 987654321),
(3, 'Carlos', 'García', 'carlos.garcia@email.com', 456123789),
(4, 'María', 'Fernández', 'maria.fernandez@email.com', 741852963),
(5, 'Pedro', 'Martínez', 'pedro.martinez@email.com', 369258147);

-- tabla Concierto
INSERT INTO Concierto (ID, Nombre, Artista, Fecha, Lugar, PrecioBase) VALUES
(1, 'Rock Fest', 'The Rockers', '2025-06-15', 'Estadio Nacional', 50),
(2, 'Pop Explosion', 'MegaPop', '2025-07-20', 'Arena Ciudad', 60),
(3, 'Indie Nights', 'The Indie Stars', '2025-08-10', 'Teatro Metropolitano', 40),
(4, 'Electro Wave', 'DJ Thunder', '2025-09-05', 'Club Nocturno X', 70),
(5, 'Jazz & Blues Night', 'Blue Notes Band', '2025-10-12', 'Auditorio Jazz', 55);

-- tabla Zona
INSERT INTO Zona (ID, NombreZona, Capacidad, PrecioAdicional) VALUES
(1, 'VIP', 200, 30),
(2, 'General', 500, 0),
(3, 'Preferencial', 300, 15),
(4, 'Palco', 100, 50),
(5, 'Balcón', 150, 20);

-- tabla Ticket
INSERT INTO Ticket (ID, ID_Cliente, ID_Concierto, Zona, PrecioFinal, FechaCompra) VALUES
(1, 1, 1, 'VIP', 80, '2025-05-01'),
(2, 2, 2, 'General', 60, '2025-06-02'),
(3, 3, 3, 'Preferencial', 55, '2025-07-05'),
(4, 4, 4, 'Palco', 120, '2025-08-10'),
(5, 5, 5, 'Balcón', 75, '2025-09-15');
