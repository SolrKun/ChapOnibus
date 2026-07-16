<h1 align="center">
  ChapOnibus

 ![Java](https://img.shields.io/badge/Java-17-orange) ![Maven](https://img.shields.io/badge/Maven-3.9-red) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue) ![PostGIS](https://img.shields.io/badge/PostGIS-3-green)
</h1>

ChapOnibus is a desktop application developed to improve access to public transportation information in **Chapecó, Santa Catarina (Brazil)**. The project provides an intuitive interface for searching bus routes, stops, and transit information, while also including a dedicated administrative application for managing transportation data.

Developed as part of the **Young Programmer Program (Programa Jovem Programador)** at **SENAC (National Commercial Learning Service)**, the project applies software engineering principles, object-oriented programming, database integration, and desktop application development to provide a centralized solution for accessing and managing public transportation information.

---

## Features

### User Application

* Search bus routes and stops
* View available transportation information
* Interactive map visualization using geospatial data
* User-friendly desktop interface
* Fast access to route information

### Administrative Application

* Manage buses
* Manage routes
* Manage bus stops
* Manage users
* Maintain transportation data
* Database integration for CRUD operations

---

## Technologies

* Java 17
* Maven
* PostgreSQL
* PostGIS
* Java Swing
* FlatLaf
* JXMapViewer2
* OpenStreetMap
* JDBC
* Git

---

## Project Structure

The project consists of two independent desktop applications that share a common PostgreSQL database and follow the same layered architecture.

### ChapOnibus

The client application used by end users to search bus routes, stops, and transportation information through an intuitive graphical interface.

### ChapOnibus-admin

The administrative application used to manage transportation data, including buses, routes, stops, and users.

Both modules follow the same layered architecture:

```text
ChapOnibus/
├── database/
│   ├── schema.sql
│   └── data.sql
│
├── ChapOnibus/
│   ├── pom.xml
│   └── src/
│       ├── java/
│       │    └── .../
│       │         ├── connection/
│       │         │   └── Database connection and configuration
│       │         ├── dao/
│       │         │   └── Data Access Objects (CRUD operations)
│       │         ├── model/
│       │         │   └── Business entities
│       │         ├── view/
│       │         │   └── User interface components
│       │         └── ChapOnibus.java
│       │             └── Application entry point
│       │
│       └── resources/
│           └── assets/
│               ├── Images
│               ├── Icons
│               └── Other static resources
│
├── ChapOnibus-admin/
│   ├── pom.xml
│   └── src/
│       ├── java/
│       │   └── .../
│       │       ├── connection/
│       │       ├── dao/
│       │       ├── model/
│       │       ├── view/
│       │       └── ChapOnibusAdmin.java
│       │
│       └── resources/
│           └── assets/
│               ├── Images
│               ├── Icons
│               └── Other static resources
│
└── README.md

```

### Architecture Overview

* **Connection** – Centralizes the database connection and configuration.
* **DAO** – Handles database communication and CRUD operations.
* **Model** – Represents the application's business entities.
* **View** – Implements the graphical user interface.
* **Main** – Contains the startup classes responsible for launching each desktop application.
* **Resources** – Stores static assets such as images, icons, and other application resources.

This layered architecture separates presentation, business entities, and data access, improving maintainability, readability, and scalability while simplifying future feature development.

---

## Database

ChapOnibus uses **PostgreSQL** with the **PostGIS** extension for spatial data management.

The project includes SQL scripts responsible for creating and configuring the database structure.

The database stores and manages transportation-related information, including:

* Bus information
* Routes
* Bus stops
* Users
* Geographic coordinates and spatial data

PostGIS enables the application to handle geospatial information, supporting map visualization and location-based transportation features.

---

## Interactive Maps

The application integrates JXMapViewer2 for map visualization, uses OpenStreetMap as the cartographic data source, and relies on PostGIS for storing and querying geographic information related to bus routes and stops.

---

## User Interface

The desktop interface was built with **Java Swing** and styled using **FlatLaf**, providing a cleaner and more modern user experience than the default Swing look and feel.

---

## Getting Started

Follow the steps below to configure and run ChapOnibus locally.

### Prerequisites

Before running the application, make sure you have installed:

* Java 17+
* Maven
* PostgreSQL 16+
* PostGIS extension

### Installation

Clone the repository:

```bash
git clone https://github.com/SolrKun/ChapOnibus.git
```

Navigate to the project directory:

```bash
cd ChapOnibus
```

### Database Configuration

Create the PostgreSQL database and enable the PostGIS extension:

```sql
CREATE DATABASE chaponibus;
```

Connect to the chaponibus database using your preferred PostgreSQL client and enable the PostGIS extension:

```sql
CREATE EXTENSION postgis;
```

Execute the SQL scripts available in the `database` directory in the following order:

1. `schema.sql` - Creates the database structure.
2. `data.sql` - Inserts the initial application data.

Configure the database connection in the application's connection configuration file with your local PostgreSQL credentials.

Example configuration:

- Host: localhost
- Port: 5432
- Database: chaponibus
- Username: your PostgreSQL username
- Password: your PostgreSQL password

### Build

Build each application separately using Maven.

For the user application:

```bash
cd ChapOnibus/ChapOnibus
mvn clean install
```

For the administrative application:

```bash
cd ../../ChapOnibus-admin
mvn clean install
```

### Run

Run the desired application module from your IDE or execute the generated JAR file.

The repository contains two independent desktop applications:

* **ChapOnibus** – User application for searching routes, stops, and transportation information.
* **ChapOnibus-admin** – Administrative application for managing buses, routes, stops, users, and transportation data.

---

## Screenshots

Screenshots showcasing the main application features will be added once the documentation is complete.

* Login
* Home Screen
* Route Search
* Interactive Map
* Administrative Panel

---

## Technical Highlights

This project strengthened my experience with:

* Object-Oriented Programming (OOP)
* Java Desktop Development
* Layered Software Architecture
* Database Design
* PostgreSQL
* Geospatial Data Processing with PostGIS
* JDBC
* Maven
* Version Control with Git
* Team Collaboration
* Software Engineering Best Practices

---

## Author

**Carlos Eduardo da Silva**

* LinkedIn: https://www.linkedin.com/in/carloseddasilva
* GitHub: https://github.com/SolrKun

**Alex Guimarães**

* GitHub: https://github.com/alexguimaa
