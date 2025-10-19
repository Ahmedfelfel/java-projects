# SpringDataRestJopApp

A small example Spring Boot application demonstrating Spring Data JPA + REST and how to persist a List<String> as a single database column using a JPA AttributeConverter.

This project shows a minimal CRUD example with:
- Spring Boot (Spring Data JPA)
- MySQL (via HikariCP)
- A JPA entity `JobPost` with a JSON/text-backed list field `postTechStack`
- A custom `StringListConverter` that converts between `List<String>` and JSON text

This README documents how to run the project, the expected database schema, sample SQL inserts, and a detailed explanation of the converter and why it is needed.

---

## Quick start

Requirements:
- Java 17+ (as used by this project; check `pom.xml` if unsure)
- Maven
- MySQL server running locally (or update datasource URL accordingly)

1. Update database credentials in `src/main/resources/application.properties` if needed.

2. Start MySQL and ensure the database exists (or let the JDBC create it if `createDatabaseIfNotExist=true` is enabled in the URL).

3. Run the app from the project root:

```bash
mvnw.cmd spring-boot:run
```

On startup the app prints `Application Started Successfully` and lists all `JobPost` entities (see `SpringDataRestJopAppApplication.java`).

---

## application.properties notes

The project includes `src/main/resources/application.properties` with example settings:

- `spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver` — modern MySQL driver class.
- `spring.datasource.url=jdbc:mysql://localhost:3306/jopdb?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false`
  - `createDatabaseIfNotExist=true` will instruct the MySQL driver to create `jopdb` automatically if it does not exist. You can remove this and manually create the DB if you prefer.
- `spring.jpa.hibernate.ddl-auto=update` — Hibernate will update the schema as needed. For production, prefer `validate` or manage schema with migrations (Flyway/Liquibase).
- `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect` — Set a Hibernate 6-compatible dialect when using MySQL 8+.

---

## Database schema (example)

Example `job_post` table matching the `JobPost` entity mapping used in this project:

```sql
CREATE TABLE `job_post` (
  `post_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `post_profile` VARCHAR(255),
  `post_desc` TEXT,
  `req_experience` INT,
  `post_tech_stack` TEXT
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
```

Note: the column `post_tech_stack` is defined as `TEXT` in this example. If you prefer using MySQL's native `JSON` type, change the column definition accordingly and ensure your JDBC driver and Hibernate dialect support it.

### Sample inserts (5 rows)

```sql
INSERT INTO `job_post` (`post_profile`, `post_desc`, `req_experience`, `post_tech_stack`) VALUES
('Backend Developer', 'Work on REST APIs and business logic.', 3, '["Java", "Spring Boot", "MySQL"]'),
('Frontend Developer', 'Build UI components and SPA features.', 2, '["JavaScript", "React", "CSS"]'),
('Full Stack Engineer', 'End-to-end feature delivery.', 5, '["Java", "React", "Docker", "PostgreSQL"]'),
('DevOps Engineer', 'CI/CD, infra automation and monitoring.', 4, '["Docker", "Kubernetes", "Terraform"]'),
('Data Engineer', 'Design ETL pipelines and data warehouses.', 3, '["Python", "Spark", "Airflow"]');
```

---

## Files of interest

- `src/main/java/com/felfel/springdatarestjopapp/model/JobPost.java` — The JPA entity representing a job post.
- `src/main/java/com/felfel/springdatarestjopapp/model/StringListConverter.java` — JPA `AttributeConverter` that serializes/deserializes `List<String>` to/from JSON text.
- `src/main/java/com/felfel/springdatarestjopapp/Repository/JobRepo.java` — Spring Data JPA repository for CRUD operations.
- `src/main/java/com/felfel/springdatarestjopapp/SpringDataRestJopAppApplication.java` — Application entry point; prints repository contents on startup.

---

## Detailed explanation: `StringListConverter` (why and how it works)

Context
- JPA maps Java types to database column types. A `List<String>` is a collection and cannot be persisted to a single VARCHAR/TEXT column by default.
- Hibernate offers several approaches for collection mapping: separate join tables/element collections, custom user types, or storing a serialized representation in a single column.
- This project stores the `List<String>` as JSON text in a single column and uses a JPA `AttributeConverter` to convert between `List<String>` and `String` (the JSON text) on the fly.

Behavior (what the converter does)
- `StringListConverter` implements `AttributeConverter<List<String>, String>`.
- `convertToDatabaseColumn(List<String>)` is invoked by the JPA provider when persisting or updating an entity:
  - If the attribute is `null`, it returns `null` (so the DB column will be NULL).
  - Otherwise it uses Jackson's `ObjectMapper` to produce a JSON array string such as `["Java","Spring Boot"]`.
  - If serialization fails it throws `IllegalArgumentException` (so the JPA operation fails fast).
- `convertToEntityAttribute(String)` is invoked when reading the column value from the ResultSet:
  - If the DB value is `null` the method returns an empty list (`List.of()`), which prevents null-pointer surprises in business code.
  - Otherwise it uses Jackson to parse the JSON array string back into `List<String>`.
  - If parsing fails the current implementation swallows the exception and returns an empty list — this is a defensive choice to avoid runtime deserialization exceptions that would abort repository reads; you can change this to rethrow an exception if you prefer fail-fast behavior.

Column type and compatibility
- The converter stores JSON as a `String`. Map the column in SQL as `TEXT` or `VARCHAR` to hold the JSON string.
- If you prefer MySQL's `JSON` column type, you may change the `@Column(columnDefinition = "json")` in the entity or update the SQL schema to use `JSON`. Using the `json` column type will store typed JSON and enable JSON-specific indexing and operators in MySQL.
- The converter itself works the same for `TEXT` or `JSON` column types since the JDBC driver presents the value as a string for conversion.

Registration and usage
- The entity field is annotated with `@Convert(converter = StringListConverter.class)` so JPA will use the converter for this field.
- The converter has `@Converter(autoApply = false)` in the source. `autoApply = true` would register it globally for all `List<String>` attributes — that can be convenient but may be too broad for most apps.

Design choices and edge cases
- Nulls: Returning `null` for DB when the attribute is `null` preserves the original intent. Returning an empty list when DB value is `null` prevents null pointer exceptions on reads but means you cannot distinguish between an explicitly empty list and `NULL` in the DB. Change to `null` if you need that distinction.
- Error handling: Current implementation returns an empty list on parse error; alternative is to rethrow a runtime exception to make failures explicit. Choose based on whether data integrity or availability is more important for your use case.
- Validation: If your app accepts user-provided JSON via direct SQL or migrations, validate the stored JSON shape to ensure it is a JSON array of strings.

Security
- The converter uses Jackson `ObjectMapper` to parse untrusted JSON from the DB. Jackson by default is safe for simple types like arrays/strings; avoid enabling polymorphic features unnecessarily.

Example (what happens during persist/read)
- Persisting `JobPost` with `postTechStack = List.of("Java", "Spring")`:
  - JPA calls `convertToDatabaseColumn()` → `"[\"Java\",\"Spring\"]"` is stored in `post_tech_stack`.
- Loading the entity back:
  - JPA reads the column value (e.g. `'["Java","Spring"]'`) and calls `convertToEntityAttribute()` → returns `List.of("Java","Spring")`.

---

## Example usage (simple sanity check)

After starting the app, you can use the repository bean in the main class to print entities (already implemented). For more interactive testing, add a simple controller or use a JUnit test that autowires `JobRepo` and asserts repository behavior.

### cURL (if you add a REST controller)

When a REST controller exposing CRUD endpoints for `JobPost` is added, you could POST JSON like:

```json
{
  "postProfile": "Backend Developer",
  "postDesc": "Work on REST APIs",
  "reqExperience": 3,
  "postTechStack": ["Java","Spring Boot","MySQL"]
}
```

---

## Troubleshooting

- "Unknown database 'jopdb'": Create the database manually or enable `createDatabaseIfNotExist=true` in the JDBC URL.
- `could not deserialize` / `invalid stream header...`: This happens when the DB column contains Java-serialized binary data but the entity expects a converter or JSON text. The `StringListConverter` approach stores JSON text and prevents Hibernate from treating the column as a serialized Java blob.
- Dialect errors (e.g. `MySQL5Dialect` not found): Use a Hibernate 6-compatible dialect such as `org.hibernate.dialect.MySQL8Dialect`.

---

## Next steps / Improvements

- Add REST controllers and DTOs for a complete API example.
- Replace `spring.jpa.hibernate.ddl-auto=update` with Flyway-managed migrations for production-ready schema management.
- Make the converter `autoApply = true` only if you want the same conversion applied globally to all `List<String>` attributes.
- Add tests that verify persistence and the converter behavior (round-trip serialization).

---

If you want, I can also:
- Add a small integration test that persists and reads back `JobPost` entities, verifying the converter.
- Create SQL migration files (Flyway) with the schema and sample data.


