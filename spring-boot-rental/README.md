# Paul Car's - Sistema de Gestión de Alquiler de Vehículos

## Descripción
Sistema completo de gestión de alquiler de vehículos desarrollado con Spring Boot, MySQL y Thymeleaf. Permite administrar vehículos, clientes, alquileres y pagos.

## Características
- 🚗 Gestión completa de vehículos
- 👥 Administración de clientes
- 📅 Control de alquileres
- 💰 Gestión de pagos
- 📊 Dashboard con estadísticas
- 🔐 Sistema de autenticación básico
- 📱 Interfaz responsiva con Bootstrap

## Requisitos
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- Git

## Instalación Local

### 1. Clonar el repositorio
```bash
git clone <repository-url>
cd spring-boot-rental
```

### 2. Crear la base de datos
```bash
mysql -u root -p < database/schema.sql
```

### 3. Configurar variables de entorno
Crear archivo `.env` o configurar en `application.properties`:
```properties
MYSQL_URL=jdbc:mysql://localhost:3306/rental_db
MYSQL_USER=root
MYSQL_PASSWORD=password
```

### 4. Compilar y ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## Credenciales de Acceso (Desarrollo)
- **Usuario:** admin
- **Contraseña:** admin123

## Despliegue en Railway

### 1. Crear proyecto en Railway
1. Ir a [Railway.app](https://railway.app)
2. Crear nueva aplicación
3. Conectar GitHub (o subir repositorio)

### 2. Configurar variables de entorno
En Railway, agregar las siguientes variables:
```
MYSQL_URL=jdbc:mysql://<mysql-host>:<mysql-port>/<database>
MYSQL_USER=<usuario>
MYSQL_PASSWORD=<contraseña>
```

### 3. Crear base de datos MySQL
1. Agregar servicio MySQL en Railway
2. Ejecutar script de inicialización:
```bash
mysql -h <host> -u <user> -p < database/schema.sql
```

### 4. Desplegar
Railway automáticamente detectará el `Dockerfile` y desplegará la aplicación.

## Estructura del Proyecto

```
spring-boot-rental/
├── src/main/java/com/paulcars/
│   ├── models/           # Entidades JPA
│   ├── repositories/     # Interfaces de acceso a datos
│   ├── services/         # Lógica de negocio
│   ├── controllers/      # Controladores web
│   └── config/           # Configuración de seguridad
├── src/main/resources/
│   ├── templates/        # Vistas Thymeleaf
│   ├── static/           # CSS, JS, imágenes
│   └── application.properties
├── database/
│   └── schema.sql        # Script de creación de BD
├── pom.xml              # Dependencias Maven
├── Dockerfile           # Para despliegue en contenedores
└── railway.toml         # Configuración para Railway
```

## Entidades Principales

### Marcas
Fabricantes de vehículos (Toyota, Honda, etc.)

### Modelos
Modelos específicos de cada marca

### Clientes
Información de clientes que alquilan vehículos

### Vehículos
Inventario de vehículos disponibles para alquiler

### Alquileres
Registro de alquileres de vehículos

### Pagos
Registro de pagos realizados

## API Endpoints

### Dashboard
- `GET /dashboard` - Resumen general del sistema

### Vehículos
- `GET /vehiculos` - Lista de vehículos
- `GET /vehiculos/nuevo` - Formulario nuevo vehículo
- `POST /vehiculos/guardar` - Guardar vehículo
- `GET /vehiculos/editar/{id}` - Editar vehículo
- `GET /vehiculos/eliminar/{id}` - Eliminar vehículo

### Clientes
- `GET /clientes` - Lista de clientes
- `GET /clientes/nuevo` - Formulario nuevo cliente
- `POST /clientes/guardar` - Guardar cliente
- `GET /clientes/editar/{id}` - Editar cliente
- `GET /clientes/eliminar/{id}` - Eliminar cliente

### Alquileres
- `GET /alquileres` - Lista de alquileres
- `GET /alquileres/nuevo` - Formulario nuevo alquiler
- `POST /alquileres/guardar` - Guardar alquiler
- `GET /alquileres/editar/{id}` - Editar alquiler

### Pagos
- `GET /pagos` - Lista de pagos
- `GET /pagos/nuevo` - Formulario nuevo pago
- `POST /pagos/guardar` - Guardar pago

## Seguridad

### Autenticación
- Actualmente implementado con autenticación básica en Spring Security
- Para producción, implementar OAuth2/JWT

### Base de Datos
- Contraseñas hasheadas con BCrypt
- Consultas parametrizadas para evitar SQL injection

## Problemas Comunes

### Error de conexión a base de datos
```
Verificar que MySQL está corriendo y las credenciales son correctas
```

### Puerto 8080 en uso
```bash
java -jar target/rental-system-1.0.0.jar --server.port=8081
```

### No se carga la página
```
Limpiar caché del navegador (Ctrl + Shift + Delete)
```

## Mejoras Futuras
- [ ] Autenticación con JWT/OAuth2
- [ ] Integración de reportes PDF
- [ ] Sistema de notificaciones por email
- [ ] API RESTful completa
- [ ] Pruebas unitarias
- [ ] Integración con servicios de pago
- [ ] Dashboard mejorado con gráficos dinámicos
- [ ] Sistema de backup automático

## Soporte
Para reportar problemas o sugerencias, contactar al equipo de desarrollo.

## Licencia
Todos los derechos reservados © 2026 Paul Car's
