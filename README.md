# E-Commerce App

This is an e-commerce application built with Spring Boot for managing and selling products online. The application supports user authentication, product listing, cart management, and order processing.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/e-commerce-app.git

2. Navigate to the project directory:
   ```bash
   cd e-commerce-app

3. Install dependencies:
   ```bash
   mvn clean install

4. Set up the database:
   ```bash
   The application uses an H2 database by default. No additional setup is required for H2. If you want to use a different database, update the "application.properties" file.

5. Run the application:
   ```bash
   mvn spring-boot:run

## Usage

Once the application is running, open your browser and navigate to http://localhost:8080. You can register as a new user, browse products, add items to your cart, and place orders.

## Features

* User authentication and authorization
* OAuth2 login (Google)
* Product listing and search
* Cart management
* Order processing
* Admin dashboard for managing products and orders
