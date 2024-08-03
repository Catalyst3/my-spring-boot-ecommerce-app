# E-Commerce App

This is an e-commerce application built with Spring Boot for managing and selling products online. The application supports user authentication, product listing, cart management, and order processing.

## Demo Video

Here is a demo video showcasing how the application works:

[![Watch the video](https://img.youtube.com/vi/Cn-kQg_6LDM/mqdefault.jpg)](https://youtu.be/Cn-kQg_6LDM?si=4SpbJnY7zBRSA0S5)
## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Configuration](#configuration)
- [Contributing](#contributing)
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

## Technologies Used
* Java
* Spring Boot
* Spring Security
* OAuth2
* H2 Database
* Thymeleaf (or your chosen template engine)
* Maven

## Configuration
```bash
# Example configuration for H2 database
spring.datasource.url=jdbc:h2:mem:database_name
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=User_Name
spring.datasource.password=User_Password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# OAuth2 Configuration for Google login
spring.security.oauth2.client.registration.google.client-id=add_your_ID
spring.security.oauth2.client.registration.google.client-secret=add_your_secret
```

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows the project’s coding standards and includes relevant tests.

## Contact
If you have any questions or suggestions, feel free to contact me at sawantaditya350@gmail.com.
### or
<p align="left">
<a href="https://twitter.com/adicatalyst45" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/twitter.svg" alt="adicatalyst45" height="30" width="40" /></a>
<a href="https://linkedin.com/in/sawantaditya350" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="sawantaditya350" height="30" width="40" /></a>
<a href="https://instagram.com/a_d_i_t_y_a_45" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/instagram.svg" alt="a_d_i_t_y_a_45" height="30" width="40" /></a>
<a href="https://www.leetcode.com/catalyst45" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/leet-code.svg" alt="catalyst45" height="30" width="40" /></a>
</p>
