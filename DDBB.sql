create database NotasVibrantes;

use NotasVibrantes;

-- Crear la tabla Clientes
create table Clientes (
    ID int primary key,
    Nombre varchar(30),
    Apellido varchar(30),
    Correo varchar(80),
    Telefono int
);

-- Crear la tabla Concierto
create table Concierto (
    ID int primary key,
    Nombre varchar(80),
    Artista varchar(80),
    Fecha date,
    Lugar varchar(80),
    PrecioBase int
);

-- Crear la tabla Zona
create table Zona (
    ID int primary key,
    NombreZona varchar(100),
    Capacidad int,
    PrecioAdicional int
);

-- Crear la tabla Ticket
create table Ticket (
    ID int primary key,
    ID_Cliente int,
    ID_Concierto int,
    Zona varchar(50),
    PrecioFinal int,
    FechaCompra date,
    foreign key (ID_Cliente) references Clientes(ID),
    foreign key (ID_Concierto) references Concierto(ID)
);