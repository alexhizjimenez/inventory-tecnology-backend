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
│   └── exception
├── application
│   └── port
│      └── in
│      └── out
│   └── services
├── infrastructure
│   └── adapter
│       └── in
│           └── rest
│               └── dto
│       └── out
│           └── persistence
│
└── InventoryTecnologyApplication
```

---

## 📊 Modelo de Datos

### Products

| Campo | Tipo |
|---------|---------|
| id | UUID |
| name | String |
| description | String |
| sku | String |
| stock | Integer |
| category | String |
| createdAt | LocalDateTime |

### Collaborators

| Campo | Tipo |
|---------|---------|
| id | UUID |
| fullName | String |
| area | String |
| position | String |
| createdAt | LocalDateTime |

### Assignments

| Campo | Tipo |
|---------|---------|
| id | UUID |
| productId | UUID |
| collaboratorId | UUID |
| deliveryDate | LocalDate |
| status | ACTIVE / RETURNED |
| createdAt | LocalDateTime |



---

## 🔄 Flujo de Negocio

1. Registrar productos en el inventario.
2. Registrar colaboradores.
3. Asignar productos disponibles.
4. Reducir automáticamente el stock.
5. Registrar devoluciones.
6. Actualizar el inventario en tiempo real.

---

## 📌 Reglas de Negocio

- No se puede asignar un producto sin stock disponible.
- El stock nunca puede ser negativo.
- Una asignación debe estar asociada a un colaborador válido.
- Al devolver un producto, el stock aumenta automáticamente.
- Todas las operaciones críticas son transaccionales.

---

## ⚡ Funcionalidades Avanzadas

### Control de Concurrencia

Evita que dos usuarios asignen simultáneamente el último producto disponible.

Implementaciones sugeridas:

- Optimistic Locking (`@Version`)
- Pessimistic Locking
- Transacciones con Spring

### Eventos de Inventario

Cuando un producto alcanza un stock mínimo:

- Generar alertas.
- Publicar eventos.
- Enviar notificaciones.
- Integrarse con Kafka.

---

## 🛠️ Tecnologías

- Java 25
- Spring Boot 4.x
- Spring Data JPA
- Hibernate
- PostgreSQL o H2 DATABASE
- Docker
- Swagger/OpenAPI
- JUnit 5
- Mockito
- Testcontainers
- Maven

---

## 🚀 Ejecución Local

### Clonar repositorio

```bash
git clone https://github.com/alexhizjimenez/inventory-technology-backend.git
```

### Entrar al proyecto

```bash
cd inventory-technology-backend
```

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

## 👨‍💻 Autor

Desarrollado como proyecto de portafolio para demostrar conocimientos en:

- Java 25
- Spring Boot 4x.
- Arquitectura Hexagonal
- PostgreSQL o h2 Database
- Docker
- Testing
- Buenas prácticas de desarrollo backend