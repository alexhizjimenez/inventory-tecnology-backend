# 🚀 Inventory Technology Backend

Sistema de Gestión de Inventario Tecnológico desarrollado con Java y Spring Boot para administrar activos tecnológicos dentro de una organización.

## 📖 Descripción

Inventory Technology Backend permite controlar el inventario de equipos y recursos tecnológicos, como laptops, monitores, licencias de software y otros activos corporativos, facilitando su asignación y devolución por parte de los colaboradores.

El sistema implementa reglas de negocio para garantizar la consistencia del inventario y evitar asignaciones cuando no existe disponibilidad de stock.

---

## ✨ Características

- Gestión de productos tecnológicos.
- Administración de colaboradores.
- Asignación y devolución de activos.
- Control de stock en tiempo real.
- Validaciones de negocio.
- Control de concurrencia.
- Arquitectura escalable y mantenible.
- API REST documentada con Swagger/OpenAPI.

---

## 🏗️ Arquitectura

El proyecto está diseñado siguiendo principios de:

- Arquitectura Hexagonal (Ports & Adapters)
- Clean Architecture
- SOLID
- Domain-Driven Design (DDD)

```text
src
├── domain
│   ├── model
│   ├── exception
│   └── event
├── application
│   └── port
│      └── in
│      └── out
│   └── services
├── infrastructure
│   └── adapter
│       └── in
│           ├── messaging
│           └── rest
│               └── dto
│       └── out
│           ├── messaging
│           └── persistence
│   └── config
└── InventoryTecnologyApplication
```

---

## 📊 Modelo de Datos

### Products

| Campo | Tipo                 |
|---------|----------------------|
| id | UUID                 |
| name | String               |
| description | String               |
| sku | String               |
| stock | Integer              |
| category | String               |
| createdAt | LocalDateTime        |

### Collaborators

| Campo     | Tipo          |
|-----------|---------------|
| id        | UUID          |
| fullName  | String        |
| code      | String        | 
| area      | String        |
| position  | String        |
| createdAt | LocalDateTime |

### Assignments

| Campo          | Tipo           |
|----------------|----------------|
| id             | UUID           |
| productId      | UUID           |
| collaboratorId | UUID           |
| deliveryDate   | LocalDateTime  |
| returnedDate   | LocalDateTime  |  
| status         | String         |
| createdAt      | LocalDateTime  |


### Users

| Campo     | Tipo          |
|-----------|---------------|
| id        | UUID          |
| fullName  | String        |
| email     | String        |
| password  | String        | 
| createdAt | LocalDateTime |


### Roles

| Campo          | Tipo          |
|----------------|---------------|
| id             | UUID          |
| name           | String        |

### user_roles

| Campo     | Tipo          |
|-----------|---------------|
| user_id   | UUID          |
| role_id   | UUID          |



---
#### Area
- HR, IT, SALES, MARKETING

#### Category
-   LAPTOP, DESKTOP, MONITOR, PERIPHERAL, NETWORK_DEVICE, SERVER, STORAGE, SOFTWARE_LICENSE, MOBILE_DEVICE, ACCESSORY

#### Position
-  EMPLOYEE, MANAGER, DIRECTOR, CEO

#### Status Assignment
- ACTIVE, RETURNED

#### Status Product
- ACTIVE, INACTIVE


---

## 🔄 Flujo de Negocio

1. Registrar productos en el inventario. [x]
2. Registrar colaboradores. [x]
3. Asignar productos disponibles. [x]
4. Reducir automáticamente el stock. [x]
5. Registrar devoluciones. [x]
6. Actualizar el inventario en tiempo real. [x]

---

## 📌 Reglas de Negocio

- No se puede asignar un producto sin stock disponible. [x]
- El stock nunca puede ser negativo. [x]
- Una asignación debe estar asociada a un colaborador válido. [x]
- Al devolver un producto, el stock aumenta automáticamente. [x]
- Todas las operaciones críticas son transaccionales. [x]

---

## ⚡ Funcionalidades Avanzadas
## 🔒 Control de Concurrencia

Para evitar inconsistencias en el inventario cuando múltiples 
usuarios intentan asignar simultáneamente el mismo producto, 
la aplicación puede implementar diferentes estrategias de control de 
concurrencia. 

### Estrategias disponibles

* **Optimistic Locking (`@Version`)** [✅ ]
* **Pessimistic Locking (`@Lock`)** [x]
* **Transacciones (`@Transactional`)** [x]

---

### 1. Optimistic Locking (`@Version`)

Permite detectar conflictos de actualización sin bloquear registros en la base de datos.

#### Implementación

```java
@Entity
@Table(name = "products")
public class ProductEntity {

    // Otros campos...

    @Version
    private Long version;
}
```

#### Comportamiento

* Dos usuarios leen el mismo producto.
* Ambos intentan actualizar el stock.
* El primer usuario actualiza correctamente.
* El segundo recibe una excepción:

```java
ObjectOptimisticLockingFailureException
```

Esta excepción puede ser gestionada desde el `GlobalExceptionHandler` para devolver un mensaje amigable al cliente.

---

### 2. Pessimistic Locking (`@Lock`)

Bloquea físicamente el registro durante la transacción para evitar modificaciones concurrentes.

#### Implementación

```java
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT p
        FROM ProductEntity p
        WHERE p.id = :id
    """)
    Optional<ProductEntity> findByIdWithLock(UUID id);
}
```

#### Comportamiento

* La fila queda bloqueada al iniciar la operación.
* Otras transacciones deberán esperar hasta que el bloqueo sea liberado.
* Garantiza consistencia inmediata a costa de un mayor tiempo de espera.

---

### 3. Uso de Transacciones (`@Transactional`)

Ambas estrategias requieren ejecutarse dentro de una transacción.

#### Ejemplo

```java
@Transactional
public Assignment create(Assignment assignment) {

    Product product = productRepositoryPort.findById(
            assignment.getProduct().getId())
        .orElseThrow(() ->
            new ProductNotFoundException("Product not found"));

    if (product.getStock() <= 0) {
        throw new ProductWithOutStockException(
            "No units available");
    }

    product.setStock(product.getStock() - 1);

    productRepositoryPort.save(product);

    return assignmentRepositoryPort.save(assignment);
}
```

#### Flujo

1. Obtener producto.
2. Validar stock disponible.
3. Descontar inventario.
4. Persistir cambios.
5. Crear asignación.
6. Confirmar transacción (COMMIT).

---

### Comparativa

| Característica          | Optimistic Locking    | Pessimistic Locking       |
| ----------------------- | --------------------- | ------------------------- |
| Bloquea registros       | ❌                     | ✅                         |
| Rendimiento             | Alto                  | Medio                     |
| Escalabilidad           | Alta                  | Media                     |
| Conflictos concurrentes | Detectados al guardar | Evitados mediante bloqueo |
| Complejidad             | Baja                  | Media                     |

---

### Recomendación

Para este proyecto de gestión de inventario tecnológico se recomienda utilizar **Optimistic Locking (`@Version`)**, ya que ofrece un mejor rendimiento y una implementación más sencilla para escenarios con concurrencia moderada.

---

### Eventos de Inventario

Cuando un producto alcanza un stock mínimo:

- Generar alertas. [✅]
- Publicar eventos. [✅]
- Enviar notificaciones. [✅]
- Integrarse con Kafka. [✅]
- Integrarse con RabbitMQ. [✅]

### 🚀 Implementación de Mensajería (Kafka & RabbitMQ)

Se ha implementado una arquitectura orientada a eventos para gestionar las alertas de stock bajo cuando se realiza una asignación de producto.

#### 1. Apache Kafka (Eventos de Dominio)
Se utiliza para publicar eventos de dominio que pueden ser consumidos por otros microservicios.
- **Tópico:** `product-low-stock`
- **Evento:** `ProductLowStockReached`
- **Datos enviados:** ID del producto, nombre, stock actual, umbral mínimo y fecha/hora.
- **Componentes:**
    - `KafkaConfig`: Configuración del productor y creación del tópico.
    - `KafkaEventAdapter`: Implementación del puerto de salida para enviar el evento.

#### 2. RabbitMQ (Notificaciones)
Se utiliza para el envío de notificaciones directas (alertas) de stock bajo.
- **Exchange:** `inventory-exchange` (Tipo Topic)
- **Cola:** `low-stock-notifications`
- **Routing Key:** `low.stock.key`
- **Mensaje:** Cadena de texto con el detalle de la alerta.
- **Componentes:**
    - `RabbitMQConfig`: Definición de Exchange, Queue y Binding.
    - `RabbitMQNotificationAdapter`: Implementación del puerto de salida para enviar la notificación.

#### Flujo de Trabajo
1. Al crear una `Assignment`, el sistema descuenta el stock del `Product`.
2. Si el stock alcanza el nivel mínimo (`hasReachedMinimumStock()`), se disparan ambos mecanismos:
    - Se publica un evento en **Kafka**.
    - Se envía una notificación a través de **RabbitMQ**.

---

## 🛠️ Tecnologías

- Java 25
- Spring Boot 4.x
- Spring Data JPA
- Hibernate
- PostgreSQL o H2 DATABASE
- Kafka
- RabbitMQ
- Docker
- Swagger/OpenAPI
- JUnit 5
- Mockito
- Testcontainers
- Maven

---

## 🚀 Ejecución Local

### Infraestructura con Docker

El proyecto incluye un archivo `docker-compose.yaml` para levantar los servicios necesarios (Kafka, RabbitMQ, etc.).

1. **Levantar servicios:**
   ```bash
   docker-compose up -d
   ```

2. **Servicios incluidos:**
    - **Kafka:** Puerto `9092`
    - **RabbitMQ:** Puerto `5672` (AMQP) y `15672` (Management UI)
    - **Kafka UI:** Puerto `8081` (Para visualizar tópicos y mensajes)
    - **Zookeeper:** Gestión de Kafka.

3. **Accesos:**
    - **RabbitMQ UI:** [http://localhost:15672](http://localhost:15672) (guest/guest)
    - **Kafka UI:** [http://localhost:8081](http://localhost:8081)

### Ejecutar aplicación

```bash
./mvnw spring-boot:run
```

---

## 📚 Documentación API

Una vez iniciada la aplicación:

```text
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Pruebas

Ejecutar pruebas unitarias:

```bash
./mvnw test
```

---

## 📈 Roadmap

- [ ] JWT Authentication
- [ ] Roles y permisos
- [ ] Auditoría de cambios
- [ ] Kafka Events
- [ ] Dashboard de métricas
- [ ] Reportes PDF/Excel
- [ ] Notificaciones por correo
- [ ] Kubernetes Deployment

---

## 🛠️ Guía de Desarrollo y Estructura (Paso a Paso)

Esta sección explica cómo se construyó el proyecto siguiendo la **Arquitectura Hexagonal**, detallando la función de cada capa y el orden de implementación recomendado.

### 1. Capa de Dominio (`domain`)
Es el núcleo del sistema, contiene la lógica de negocio pura y es totalmente independiente de frameworks (Spring, Hibernate, etc.).

- **`model/`**: Contiene las entidades de dominio (POJOs). Representan los conceptos del negocio (Product, Assignment, etc.).
- **`exception/`**: Excepciones personalizadas que describen errores de negocio (ej. `ProductWithOutStockException`).
- **`event/`**: Definición de eventos que ocurren en el dominio (ej. `ProductLowStockReached`).

**¿Por qué primero?** Porque el negocio define qué hace la aplicación, no la base de datos ni la API.

### 2. Capa de Aplicación (`application`)
Orquestra el flujo de los datos y las reglas de negocio.

- **`port/in/` (Input Ports)**: Interfaces que definen qué puede hacer el usuario con el sistema (Casos de Uso).
- **`port/out/` (Output Ports)**: Interfaces que definen qué necesita el sistema de herramientas externas (Persistencia, Mensajería).
- **`services/`**: Implementación de los casos de uso. Aquí se coordina el dominio y los puertos de salida.

**Paso a paso:** 
1. Definir la interfaz del Caso de Uso (In Port).
2. Definir la interfaz del Repositorio (Out Port).
3. Implementar el Servicio que usa ambas.

### 3. Capa de Infraestructura (`infrastructure`)
Contiene los detalles técnicos y las implementaciones de los puertos.

- **`adapter/in/rest/`**: Controladores que exponen la API. Transforman JSON a objetos de dominio.
- **`adapter/out/persistence/`**: Implementación de los repositorios usando Spring Data JPA.
    - **`Entity`**: Clase que mapea a la tabla de la BD.
    - **`Mapper`**: Clase que convierte de `Domain Model` a `Entity` y viceversa (aislamiento total).
    - **`Adapter`**: Implementa el `Output Port` usando el repositorio de JPA.
- **`adapter/out/messaging/`**: Implementaciones para enviar mensajes a Kafka o RabbitMQ.
- **`config/`**: Configuraciones de Spring (Bean beans, Security, Swagger).

---

---

## 🔐 Implementación de Spring Security & JWT (Paso a Paso)

Para proteger la API y gestionar la autenticación de forma stateless, seguimos estos pasos:

### 1. Dependencias Necesarias (`pom.xml`)
Añadir `spring-boot-starter-security` y las librerías de `jjwt` (api, impl, jackson).

### 2. Definir el Puerto de Salida (`application/port/out`)
Creamos `PasswordEncoderPort` para que el dominio no dependa directamente de `BCrypt`.

### 3. Implementar el Servicio de Tokens (`infrastructure/adapter/out/security`)
- **`JwtService`**: Responsable de generar, extraer claims y validar la vigencia de los tokens JWT usando una clave secreta.

### 4. Cargar Usuarios de la BD (`infrastructure/adapter/out/security`)
- **`UserAuthenticated`**: Implementa `UserDetails` de Spring para envolver nuestra entidad de dominio `User`.
- **`UserDetailsServiceImpl`**: Implementa `UserDetailsService`. Busca al usuario en la base de datos a través del `UserRepositoryPort`.

### 5. El Filtro de Autenticación (`infrastructure/adapter/in/security`)
- **`JwtAuthenticationFilter`**: Un filtro que extiende de `OncePerRequestFilter`.
    - Intercepta cada petición.
    - Extrae el token del header `Authorization: Bearer <token>`.
    - Si el token es válido, establece la autenticación en el `SecurityContextHolder`.

### 6. Configuración Global (`infrastructure/config/SecurityConfig`)
Aquí se une todo:
- Se deshabilita CSRF (ya que usamos JWT).
- Se define la política de sesión como `STATELESS`.
- Se configuran los `requestMatchers` para permitir acceso público a `/auth/**` y Swagger.
- Se añade el `JwtAuthenticationFilter` antes del filtro estándar de Spring.

### 7. Controlador de Autenticación (`infrastructure/adapter/in/rest`)
- **`AuthController`**: Expone los endpoints `/auth/register` y `/auth/login`.
- Llama al `AuthService`, el cual valida las credenciales y genera el token usando `JwtService`.

#### 📂 Desglose de Archivos de Seguridad

| Archivo | Ubicación | Propósito |
|---|---|---|
| `SecurityConfig` | `infrastructure/config` | Configuración central: reglas de acceso, filtros y beans de auth. |
| `JwtAuthenticationFilter` | `infrastructure/adapter/in/security` | Filtro que valida el token en cada petición HTTP. |
| `JwtService` | `infrastructure/adapter/out/security` | Lógica pura de JWT (generar, firmar y leer tokens). |
| `UserAuthenticated` | `infrastructure/adapter/out/security` | Adaptador que convierte nuestro `User` en un `UserDetails` de Spring. |
| `UserDetailsServiceImpl` | `infrastructure/adapter/out/security` | Servicio que conecta Spring Security con nuestro `UserRepositoryPort`. |
| `BCryptPasswordEncoderAdapter` | `infrastructure/adapter/out/security` | Implementación del puerto de cifrado usando BCrypt. |
| `AuthController` | `infrastructure/adapter/in/rest` | Controlador REST para procesos de login y registro. |
| `AuthService` | `application/services` | Orquestador de la lógica de autenticación y registro. |

---

## 📂 Desglose de Archivos y su Propósito

| Carpeta / Archivo | Propósito |
|---|---|
| `domain/model` | Objetos de negocio. No tienen anotaciones de JPA. |
| `application/port/in` | Contratos de los casos de uso (ej. `CreateProductUseCase`). |
| `application/port/out` | Contratos para salida de datos (ej. `ProductRepositoryPort`). |
| `application/services` | Lógica de orquestación y transaccionalidad. |
| `infrastructure/adapter/in/rest` | Puntos de entrada HTTP (Controllers y DTOs). |
| `infrastructure/adapter/out/persistence` | Adaptadores de base de datos. Aquí viven las `@Entity`. |
| `infrastructure/adapter/out/persistence/mapper` | Crucial para mantener el dominio limpio de JPA. |
| `infrastructure/config/SecurityConfig` | Seguridad del sistema (JWT, permisos). |
| `infrastructure/config/KafkaConfig` / `RabbitMQConfig` | Configuración de infraestructura de mensajería. |

---

## 🚀 Flujo de una Petición (Ejemplo: Crear Asignación)

1. **Client**: Envía un JSON al `AssignmentController`.
2. **Controller**: Convierte el `RequestDTO` a un objeto `Assignment` (dominio).
3. **Service**: Recibe el objeto, valida reglas de negocio (ej. ¿hay stock?) usando los puertos de salida.
4. **Persistence Adapter**: El servicio llama al `AssignmentRepositoryPort`. El adaptador mapea el dominio a una entidad de BD y la guarda.
5. **Messaging Adapter**: Si el stock es bajo, el servicio dispara un evento a través de `ProductEventPublisherPort`.
6. **Response**: El `Controller` recibe el resultado del servicio y devuelve un `ResponseDTO`.

---

## 👨‍💻 Autor

Desarrollado como proyecto de portafolio para demostrar conocimientos en:

- Java 25
- Spring Boot 4x.
- Arquitectura Hexagonal
- PostgreSQL o h2 Database
- Docker
- Testing
- Buenas prácticas de desarrollo backend