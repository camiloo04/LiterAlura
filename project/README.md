# LiterAlura Book Catalog

The academic content was in Spanish, but I developed the project in English

A Spring Boot console application that manages a book catalog using the Gutendex API.

## Requirements

- Java 17
- PostgreSQL
- Maven

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
