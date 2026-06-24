# Guía de Despliegue en Railway

## Pasos para desplegar Paul Car's en Railway

### 1. Preparación Inicial

#### 1.1 Cuenta en Railway
- Ir a [railway.app](https://railway.app)
- Crear cuenta (puedes usar GitHub)
- Verificar email

#### 1.2 Preparar repositorio Git
```bash
git init
git add .
git commit -m "Inicial commit - Paul Car's Rental System"
git remote add origin <tu-repo-url>
git push -u origin main
```

### 2. Configuración en Railway

#### 2.1 Crear nuevo proyecto
1. En Railway dashboard, hacer clic en "+ New Project"
2. Seleccionar "Deploy from GitHub"
3. Conectar cuenta GitHub
4. Seleccionar el repositorio
5. Confirmar

#### 2.2 Agregar servicio MySQL
1. En el proyecto, hacer clic en "+ Add"
2. Buscar "MySQL"
3. Seleccionar "Provision PostgreSQL" (o MySQL si está disponible)
4. Esperar a que se provisione

#### 2.3 Configurar variables de entorno
En Railway, ir a "Variables" y agregar:

```
MYSQL_URL=mysql://user:password@host:3306/rental_db
MYSQL_USER=user
MYSQL_PASSWORD=password
SPRING_PROFILES_ACTIVE=prod
```

Para obtener las credenciales MySQL:
1. Ir al servicio MySQL en Railway
2. Copiar la "DATABASE_URL"
3. Extraer usuario, contraseña y host

#### 2.4 Inicializar base de datos
1. Conectar a MySQL en Railway (usar MySQL Workbench o línea de comandos)
2. Ejecutar el script de inicialización:
```bash
mysql -h <railway-host> -u <user> -p < database/schema.sql
```

O copiar el contenido de `database/schema.sql` y ejecutarlo directamente en Railway.

### 3. Desplegar

#### 3.1 Despliegue automático
1. En Railway, configurar rama principal
2. Cada push a main disparará un despliegue automático

#### 3.2 Despliegue manual
```bash
# Instalar Railway CLI
npm install -g @railway/cli

# Login
railway login

# Desplegar
railway up
```

### 4. Verificar despliegue

#### 4.1 Acceder a la aplicación
- Railway proporcionará una URL pública
- Formato: `https://proyecto-nombre.up.railway.app`

#### 4.2 Datos de acceso
- Usuario: `admin`
- Contraseña: `admin123`

### 5. Solucionar problemas

#### 5.1 Error: "Cannot connect to database"
```
Verificar que:
- Variables MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD están correctas
- MySQL está running en Railway
- Base de datos "rental_db" existe
```

#### 5.2 Error: "Table doesn't exist"
```
Solución: Ejecutar manualmente el script schema.sql en la BD
```

#### 5.3 Aplicación lenta
```
Verificar:
- Conexión a base de datos
- Pool de conexiones en application-prod.properties
- Logs de la aplicación
```

### 6. Monitoreo y Mantenimiento

#### 6.1 Ver logs
```bash
railway logs -s rental-system
```

#### 6.2 Reiniciar aplicación
```bash
railway restart -s rental-system
```

#### 6.3 Backups de BD
```
En Railway, MySQL proporciona backups automáticos diarios
```

### 7. Optimizaciones para producción

#### 7.1 Performance
- Habilitar caché en Thymeleaf: `spring.thymeleaf.cache=true`
- Usar pool de conexiones optimizado
- Implementar índices en base de datos

#### 7.2 Seguridad
- Cambiar usuario admin predeterminado
- Usar HTTPS (Railway lo proporciona automáticamente)
- Implementar JWT para API
- Agregar validación CSRF

#### 7.3 Base de datos
```sql
-- Agregar índices
CREATE INDEX idx_cliente_documento ON clientes(numero_documento);
CREATE INDEX idx_vehiculo_placa ON vehiculos(placa);
CREATE INDEX idx_alquiler_cliente ON alquileres(id_cliente);
CREATE INDEX idx_alquiler_estado ON alquileres(id_estado_alquiler);
CREATE INDEX idx_pago_alquiler ON pagos(id_alquiler);
```

### 8. Variables de entorno (Referencia completa)

```
# Base de datos
MYSQL_URL=mysql://usuario:password@host:puerto/rental_db
MYSQL_USER=usuario
MYSQL_PASSWORD=contraseña

# Spring
SPRING_PROFILES_ACTIVE=prod

# Aplicación
APP_NAME=Paul Car's Rental System
ENVIRONMENT=production
```

### 9. Escalabilidad

Para escalar a múltiples instancias:
1. En Railway, aumentar "CPU" y "Memory"
2. O crear múltiples réplicas (requiere balanceador)

### 10. URLs útiles

- Dashboard Railway: https://railway.app/dashboard
- Documentación Railway: https://docs.railway.app
- MySQL en Railway: https://railway.app/guides/mysql
- Spring Boot Deployment: https://spring.io/guides/gs/spring-boot/

---

**Nota**: Estos pasos asumen que estás usando Railway como proveedor de hosting. 
Otros proveedores (Heroku, AWS, Azure) requieren configuración diferente.

¡El sistema está listo para producción! 🚀
