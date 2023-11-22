# Frameworks and Languages Used
The Recipe Management System project is built using the following frameworks and languages:

    Spring Framework: The project is developed using the Spring Framework, which is a popular Java-based framework for building web applications.
    Jakarta Validation: Jakarta Validation is used for input validation and data validation.
    JPA (Java Persistence API): JPA is used for working with relational databases.
    Lombok
    Mysql-server
    Swagger

# Data Flow
The data flow in this project is divided into several components, including controllers, services, repositories, and database design.

## Controller
The controllers handle incoming HTTP requests and define the API endpoints for various operations related to users, recipes, and comments.

    UserController: Manages user-related operations such as sign-up, sign-in, sign-out, updating user details, getting all users, and getting a user by ID.
    RecipeController: Manages recipe-related operations such as creating recipes, deleting recipes, updating recipe details, getting all recipes, and getting a recipe by ID.
    CommentController: Manages comment-related operations such as adding a comment, deleting a comment, and updating a comment.

## Services
The services layer contains the business logic and interacts with the repositories to perform CRUD operations on data.

    UserService: Provides services related to users, such as sign-up, sign-in, sign-out, updating user details, getting all users, and getting a user by ID.
    RecipeService: Provides services related to recipes, including creating recipes, deleting recipes, updating recipe details, getting all recipes, and getting a recipe by ID.
    CommentService: Provides services related to comments, such as adding a comment, deleting a comment, and updating a comment.

## Repository
The repository layer interfaces with the database to perform data storage and retrieval operations. It uses JPA for database interaction.

    User Repository: Manages user data.
    Recipe Repository: Manages recipe data.
    Comment Repository: Manages comment data.
    AuthenticationToken Repository: Manages authentication token data.

## Database Design
    The project's database design includes tables for users, recipes, comments, and authentication tokens. These tables are interconnected to store and manage the data efficiently.

## Data Structure Used in this Project
The data structures used in this project include Java classes representing the database entities. These classes include User, Recipe, Comments, and AuthenticationToken. These classes define the structure of the data stored in the database and provide a Java object-oriented representation of the data.

# Project Summary
The Recipe Management System is a Java-based web application that allows users to manage recipes, comments, and user accounts. It provides a set of API endpoints for performing various operations, such as creating and managing recipes, adding and updating comments, and managing user accounts. The system uses a database to store user information, recipes, and comments, and it implements validation and authentication mechanisms to ensure data integrity and security.

The project is built using the Spring Framework, Jakarta Validation for input validation, and JPA for database interaction. It follows a layered architecture with controllers, services, and repositories to maintain a clear separation of concerns.