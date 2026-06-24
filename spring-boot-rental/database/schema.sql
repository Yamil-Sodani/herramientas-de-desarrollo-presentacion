-- Creación de la Base de Datos
CREATE DATABASE IF NOT EXISTS rental_db;
USE rental_db;

-- Tabla: marcas
CREATE TABLE IF NOT EXISTS marcas (
    id_marca INT NOT NULL AUTO_INCREMENT,
    nombre_marca VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_marca)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: modelos
CREATE TABLE IF NOT EXISTS modelos (
    id_modelo INT NOT NULL AUTO_INCREMENT,
    id_marca INT NOT NULL,
    nombre_modelo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_modelo),
    FOREIGN KEY (id_marca) REFERENCES marcas(id_marca)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: tiposdocumento
CREATE TABLE IF NOT EXISTS tiposdocumento (
    id_tipo_doc INT NOT NULL AUTO_INCREMENT,
    nombre_tipo_doc VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_tipo_doc)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: clientes
CREATE TABLE IF NOT EXISTS clientes (
    id_cliente INT NOT NULL AUTO_INCREMENT,
    tipo_documento VARCHAR(20) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_cliente)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: estadosvehiculo
CREATE TABLE IF NOT EXISTS estadosvehiculo (
    id_estado INT NOT NULL AUTO_INCREMENT,
    nombre_estado VARCHAR(30) NOT NULL,
    PRIMARY KEY (id_estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: tiposvehiculo
CREATE TABLE IF NOT EXISTS tiposvehiculo (
    id_tipo INT NOT NULL AUTO_INCREMENT,
    nombre_tipo_vehiculo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: combustibles
CREATE TABLE IF NOT EXISTS combustibles (
    id_combustible INT NOT NULL AUTO_INCREMENT,
    nombre_combustible VARCHAR(30) NOT NULL,
    PRIMARY KEY (id_combustible)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: vehiculos
CREATE TABLE IF NOT EXISTS vehiculos (
    id_vehiculo INT NOT NULL AUTO_INCREMENT,
    placa VARCHAR(15) NOT NULL UNIQUE,
    color VARCHAR(30) NOT NULL,
    anio INT NOT NULL,
    numero_motor VARCHAR(50) NOT NULL,
    numero_vin VARCHAR(17) NOT NULL UNIQUE,
    precio_dia DECIMAL(10, 2) NOT NULL,
    precio_hora DECIMAL(10, 2) NOT NULL,
    id_modelo INT NOT NULL,
    id_tipo INT NOT NULL,
    id_combustible INT NOT NULL,
    id_estado INT NOT NULL,
    PRIMARY KEY (id_vehiculo),
    FOREIGN KEY (id_modelo) REFERENCES modelos(id_modelo),
    FOREIGN KEY (id_tipo) REFERENCES tiposvehiculo(id_tipo),
    FOREIGN KEY (id_combustible) REFERENCES combustibles(id_combustible),
    FOREIGN KEY (id_estado) REFERENCES estadosvehiculo(id_estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: estadosalquiler
CREATE TABLE IF NOT EXISTS estadosalquiler (
    id_estado_alquiler INT NOT NULL AUTO_INCREMENT,
    nombre_estado VARCHAR(30) NOT NULL,
    PRIMARY KEY (id_estado_alquiler)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: alquileres
CREATE TABLE IF NOT EXISTS alquileres (
    id_alquiler INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_vehiculo INT NOT NULL,
    licencia_conducir VARCHAR(20) NOT NULL,
    fecha_reserva DATE NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin_estimada DATE NOT NULL,
    fecha_fin_real DATE,
    id_estado_alquiler INT NOT NULL,
    PRIMARY KEY (id_alquiler),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculos(id_vehiculo),
    FOREIGN KEY (id_estado_alquiler) REFERENCES estadosalquiler(id_estado_alquiler)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: metodospago
CREATE TABLE IF NOT EXISTS metodospago (
    id_metodo_pago INT NOT NULL AUTO_INCREMENT,
    nombre_metodo VARCHAR(30) NOT NULL,
    PRIMARY KEY (id_metodo_pago)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: pagos
CREATE TABLE IF NOT EXISTS pagos (
    id_pago INT NOT NULL AUTO_INCREMENT,
    id_alquiler INT NOT NULL,
    fecha_pago DATE NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    id_metodo_pago INT NOT NULL,
    estado_pago VARCHAR(30) NOT NULL,
    comentarios TEXT,
    PRIMARY KEY (id_pago),
    FOREIGN KEY (id_alquiler) REFERENCES alquileres(id_alquiler),
    FOREIGN KEY (id_metodo_pago) REFERENCES metodospago(id_metodo_pago)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Datos de prueba

-- Estados de Vehículos
INSERT INTO estadosvehiculo (nombre_estado) VALUES ('Disponible'), ('Ocupado'), ('Mantenimiento');

-- Tipos de Vehículos
INSERT INTO tiposvehiculo (nombre_tipo_vehiculo) VALUES ('SUV'), ('Sedan'), ('Deportivo'), ('Camioneta');

-- Combustibles
INSERT INTO combustibles (nombre_combustible) VALUES ('Gasolina'), ('Diésel'), ('Hibrido'), ('Eléctrico');

-- Marcas
INSERT INTO marcas (nombre_marca) VALUES ('Toyota'), ('Honda'), ('Hyundai'), ('Nissan'), ('Chevrolet');

-- Modelos
INSERT INTO modelos (id_marca, nombre_modelo) VALUES 
(1, 'Highlander'), (1, 'Corolla'), (1, 'RAV4'),
(2, 'CR-V'), (2, 'Civic'), (2, 'Accord'),
(3, 'Tucson'), (3, 'Elantra'), (3, 'Santa Fe'),
(4, 'Rogue'), (4, 'Altima'), (4, 'Sentra'),
(5, 'Silverado'), (5, 'Equinox'), (5, 'Malibu');

-- Estados de Alquiler
INSERT INTO estadosalquiler (nombre_estado) VALUES ('Activo'), ('Completado'), ('Cancelado');

-- Métodos de Pago
INSERT INTO metodospago (nombre_metodo) VALUES ('Tarjeta Crédito'), ('Tarjeta Débito'), ('Efectivo'), ('Transferencia');

-- Vehículos
INSERT INTO vehiculos (placa, color, anio, numero_motor, numero_vin, precio_dia, precio_hora, id_modelo, id_tipo, id_combustible, id_estado) VALUES
('ABC-123', 'Blanco', 2023, 'MOT123456', 'VIN123456789012345', 85.00, 12.00, 1, 1, 1, 1),
('DEF-456', 'Negro', 2022, 'MOT234567', 'VIN234567890123456', 65.00, 10.00, 2, 2, 1, 1),
('GHI-789', 'Rojo', 2023, 'MOT345678', 'VIN345678901234567', 95.00, 14.00, 3, 3, 1, 1),
('JKL-012', 'Plata', 2022, 'MOT456789', 'VIN456789012345678', 75.00, 11.00, 4, 1, 2, 1),
('MNO-345', 'Azul', 2023, 'MOT567890', 'VIN567890123456789', 70.00, 10.00, 5, 2, 1, 1),
('PQR-678', 'Gris', 2022, 'MOT678901', 'VIN678901234567890', 80.00, 12.00, 6, 2, 1, 1),
('STU-901', 'Blanco', 2023, 'MOT789012', 'VIN789012345678901', 90.00, 13.00, 7, 1, 2, 1),
('VWX-234', 'Negro', 2022, 'MOT890123', 'VIN890123456789012', 100.00, 15.00, 13, 4, 2, 1),
('YZA-567', 'Rojo', 2023, 'MOT901234', 'VIN901234567890123', 72.00, 11.00, 8, 2, 1, 1),
('BCD-890', 'Azul', 2022, 'MOT012345', 'VIN012345678901234', 88.00, 13.00, 14, 1, 1, 1);

-- Clientes
INSERT INTO clientes (tipo_documento, numero_documento, nombres, apellidos, email, telefono) VALUES
('Cédula', '1234567890', 'Juan', 'Pérez', 'juan.perez@email.com', '555-1234'),
('Cédula', '0987654321', 'María', 'González', 'maria.gonzalez@email.com', '555-5678'),
('Pasaporte', 'ABC123456', 'Carlos', 'López', 'carlos.lopez@email.com', '555-9101'),
('Cédula', '1122334455', 'Ana', 'Martínez', 'ana.martinez@email.com', '555-1121'),
('Cédula', '5566778899', 'Roberto', 'Rodríguez', 'roberto.rodriguez@email.com', '555-3141'),
('Pasaporte', 'XYZ789012', 'Linda', 'Sánchez', 'linda.sanchez@email.com', '555-5161'),
('Cédula', '9988776655', 'Pedro', 'Díaz', 'pedro.diaz@email.com', '555-7181'),
('Cédula', '4433221100', 'Sofia', 'Morales', 'sofia.morales@email.com', '555-9202');

-- Alquileres
INSERT INTO alquileres (id_cliente, id_vehiculo, licencia_conducir, fecha_reserva, fecha_inicio, fecha_fin_estimada, fecha_fin_real, id_estado_alquiler) VALUES
(1, 1, 'LIC001', '2026-06-01', '2026-06-05', '2026-06-10', NULL, 1),
(2, 2, 'LIC002', '2026-06-02', '2026-06-06', '2026-06-08', '2026-06-08', 2),
(3, 3, 'LIC003', '2026-06-03', '2026-06-07', '2026-06-14', NULL, 1),
(4, 4, 'LIC004', '2026-06-04', '2026-06-08', '2026-06-12', NULL, 1),
(5, 5, 'LIC005', '2026-06-05', '2026-06-10', '2026-06-15', NULL, 1);

-- Pagos
INSERT INTO pagos (id_alquiler, fecha_pago, monto_total, id_metodo_pago, estado_pago, comentarios) VALUES
(2, '2026-06-08', 130.00, 1, 'Pagado', 'Pago completo por alquiler'),
(3, '2026-06-10', 150.00, 2, 'Pagado', 'Anticipo del alquiler'),
(4, '2026-06-12', 200.00, 1, 'Pagado', 'Pago total'),
(1, '2026-06-20', 300.00, 3, 'Pendiente', 'Pendiente de pago'),
(5, '2026-06-25', 250.00, 1, 'Pagado', 'Pago completado');
