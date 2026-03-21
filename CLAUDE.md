# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Start PostgreSQL (required before running the app)
docker-compose up -d

# Build all modules
mvn clean install

# Run the application
mvn -pl infrastructure spring-boot:run

# Run tests
mvn test

# Build specific module
mvn -pl domain install
mvn -pl application install
mvn -pl infrastructure install
```

## Architecture

This project implements **Hexagonal Architecture (Ports & Adapters)** as a Maven multi-module project. The dependency direction flows inward: `infrastructure` → `application` → `domain`.

### Module Responsibilities

**`domain/`** — Pure business logic, no framework dependencies (except JPA annotations on entity)
- `aggregate/` — Domain aggregates (e.g., `Product`)
- `command/` — Sealed interfaces with nested records for write operations
- `valueobject/` — Immutable value types (`BaseId<T>`, `Brand`, `ProductState` enum)
- `entity/` — JPA persistence entities (kept separate from domain aggregates)

**`application/`** — Use cases and port definitions; depends only on `domain`
- `ports/input/` — Interfaces defining what the application exposes (driven/primary ports)
- `ports/output/` — Interfaces defining what the application needs from infrastructure (driving/secondary ports)
- `usecase/` — Implementations of input ports; orchestrate domain logic via output ports
- `query/` — Sealed interfaces with nested records for read operations
- `response/` — DTOs returned from use cases

**`infrastructure/`** — All external concerns; depends on `application`
- `adapters/input/rest/` — REST controllers that translate HTTP → commands/queries → use cases
- `adapters/output/postgres/` — JPA adapter implementing output ports
- `request/` — HTTP request DTOs (validation layer at boundary)

### Key Design Decisions

- **Domain model vs. JPA entity are separate classes.** `Product` (aggregate) is never a JPA entity; `ProductJpaEntity` is the persistence model. Mapping happens in the application layer (`ProductInputPort`).
- **Commands and Queries use sealed interfaces.** All write operations go through `ProductCommand` (sealed), read operations through `ProductQuery` (sealed) — enables exhaustive pattern matching.
- **Output port is injected into the use case.** `ProductInputPort` receives `ProductPostgresOutputPort` via constructor injection — the application layer never imports infrastructure classes directly.
- **Transactions are owned by the output adapter.** `ProductPostgresAdapter` marks read methods with `@Transactional(readOnly = true)`.

### Database

PostgreSQL 15 running via Docker Compose on port 5432. Connection config in `infrastructure/src/main/resources/application.yml`:
- Database: `productdb`, user: `can`, password: `can`
- Hibernate DDL: `update` (schema auto-managed)
