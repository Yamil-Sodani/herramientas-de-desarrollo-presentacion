# 🚗 Paul Car's - Inicio Rápido

## ⚡ 5 Minutos para empezar

### Requisitos previos
- ✅ Java 17+ instalado
- ✅ Maven instalado
- ✅ MySQL 8.0+ corriendo

### Pasos

#### 1️⃣ Configurar Base de Datos
```bash
# Crear base de datos (ejecutar en MySQL)
mysql -u root -p < database/schema.sql
```

#### 2️⃣ Configurar credenciales
Editar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rental_db
spring.datasource.username=root
spring.datasource.password=password
```

#### 3️⃣ Compilar y ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

#### 4️⃣ Acceder a la aplicación
- Abrir navegador: `http://localhost:8080`
- Usuario: `admin`
- Contraseña: `admin123`

---

## 📊 Dashboard Principal

Al iniciar sesión verás:
- 📈 Alquileres activos
- 🚗 Vehículos disponibles
- 💰 Ingresos totales
- 👥 Clientes registrados
- 📊 Gráficos de estadísticas

---

## 🎯 Funcionalidades principales

### 1. Gestión de Vehículos
```
Dashboard → Vehículos
- Ver todos los vehículos
- Agregar nuevo vehículo
- Editar información
- Eliminar vehículos
```

### 2. Gestión de Clientes
```
Dashboard → Clientes
- Registro de clientes
- Información de contacto
- Datos de documentos
- Búsqueda y edición
```

### 3. Alquileres
```
Dashboard → Alquileres
- Crear nuevo alquiler
- Seguimiento de alquileres
- Gestión de fechas
- Estados de alquiler
```

### 4. Pagos
```
Dashboard → Pagos
- Registro de pagos
- Métodos de pago
- Ingresos totales
- Estados de pago
```

---

## 🐛 Solución de problemas

### ❌ "Cannot connect to database"
```bash
# Verificar que MySQL está corriendo
mysql -u root -p

# Si no funciona, reiniciar MySQL:
# En Windows: net start MySQL80
# En Linux: sudo service mysql restart
# En Mac: brew services restart mysql
```

### ❌ "Java 17 not found"
```bash
java -version  # Verificar versión
# Instalar Java 17 desde oracle.com
```

### ❌ "Maven not found"
```bash
mvn -version  # Verificar
# Si no está, descargarlo desde maven.apache.org
```

### ❌ Puerto 8080 en uso
```bash
# Usar otro puerto:
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

---

## 📁 Estructura rápida

```
src/main/java/com/paulcars/
├── models/         → Tablas de BD (Vehículo, Cliente, etc)
├── repositories/   → Acceso a datos (SQL)
├── services/       → Lógica de negocio
├── controllers/    → Páginas web
└── config/         → Seguridad

src/main/resources/
├── templates/      → Archivos HTML (Thymeleaf)
├── static/         → CSS, JS
└── application.properties → Configuración
```

---

## 🔐 Seguridad por defecto

| Elemento | Valor |
|----------|-------|
| Usuario Admin | admin |
| Contraseña | admin123 |
| Base de Datos | rental_db |
| Servidor | localhost:8080 |

⚠️ **Cambiar en producción!**

---

## 🚀 Próximos pasos

### ✨ Si funciona todo:
1. Agregar datos de prueba
2. Explorar todas las funciones
3. Personalizar según necesidades

### 🔧 Configuración recomendada:

1. **Cambiar credenciales de admin**
   - Editar la clase de configuración

2. **Agregar más usuarios**
   - Implementar registro de usuarios

3. **Agregar validaciones**
   - Validar datos en formularios

4. **Mejorar seguridad**
   - Implementar JWT/OAuth2

---

## 📚 Documentación completa

- 📖 [README.md](./README.md) - Documentación completa
- 🚀 [DEPLOYMENT.md](./DEPLOYMENT.md) - Despliegue en Railway

---

## 💡 Tips útiles

### Compilar sin ejecutar
```bash
mvn clean install
```

### Ejecutar tests
```bash
mvn test
```

### Ver logs en tiempo real
```bash
tail -f application.log
```

### Limpiar caché de Maven
```bash
mvn clean
```

---

¡Listo! 🎉 Tu sistema de alquiler de vehículos está funcionando.

**¿Necesitas ayuda?** Revisa los archivos de documentación incluidos.
