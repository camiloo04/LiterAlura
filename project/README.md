# LiterAlura Book Catalog

A Spring Boot console application that manages a book catalog using the Gutendex API.

## Requirements

- Java 17
- PostgreSQL
- Maven

## Setup

1. Create a PostgreSQL database named `literalura`
2. Update database credentials in `src/main/resources/application.properties` if needed
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## Features

- Search books by title using Gutendex API
- Save books to local database
- List all registered books
- View unique authors
- Search books by language (EN, ES, FR, PT)

## Usage

The application presents a console menu with the following options:

1. Search Book by Title
2. List All Books
3. List All Authors
4. Search Books by Language
5. Exit

Follow the on-screen prompts to interact with the application.

## API Integration

The application integrates with the Gutendex API (https://gutendex.com) to search for books.
Endpoint used: `GET https://gutendex.com/books?search={query}`

## Database Schema

```sql
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    author VARCHAR(255) NOT NULL,
    language VARCHAR(2) NOT NULL,
    downloads INTEGER NOT NULL
);
```