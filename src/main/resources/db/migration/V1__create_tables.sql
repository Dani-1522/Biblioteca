-- Tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(255) NOT NULL,
    rol ENUM('usuario', 'admin') DEFAULT 'usuario',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de libros
CREATE TABLE libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    genero VARCHAR(100),
    año_publicacion INT,
    descripcion TEXT,
    enlace_descarga VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de favoritos
CREATE TABLE favoritos (
    id_favorito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(id_usuario, id_libro),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE
);

-- Tabla de progreso de lectura
CREATE TABLE progreso_lectura (
    id_progreso INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    capitulo_actual INT,
    porcentaje_leido DECIMAL(5,2),
    ultima_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE(id_usuario, id_libro),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE
);

-- Tabla de calificaciones
CREATE TABLE calificaciones (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    puntuacion INT CHECK (puntuacion BETWEEN 1 AND 5),
    fecha_calificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(id_usuario, id_libro),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE
);

-- Tabla de reseñas
CREATE TABLE resenas (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    contenido TEXT,
    fecha_resena TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE
);
