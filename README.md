# Bookstore Management
This project provides a backend system to manage books, categories, users, and user reviews. It supports user registration and login, role-based access control (Guest, Registered User, Admin), integration with Google OAuth for external login, and an AI feature to analyze the sentiment of user reviews.

## Features
### CRUD Operations
- CRUD operations for:
    - Books
    - Categories
    - Users
    - Reviews
- Input validation and error handling

### Basic Login & Registration
  - Users can register and log in with email and password.
  - Passwords are securely hashed.

### JWT Authentication
- JSON Web Token (JWT) is used for secure session management.
- Role-based access control:
  - Guest
  - Registered User
  - Admin

### 3rd-Party Integrations
- **Google OAuth:** Users can log in using their Google accounts.
- To run the Front End ([Install NodeJS](https://nodejs.org/en) before run):
```shell
# Install HTTP Server (install once)
npm i -g http-server

# Run this when you want to run the Front End
http-server -p 3000
```
_Note:_ Run the script in the front-end folder

### AI Integration
- **NLP Sentiment Analysis:** Basic sentiment analysis using Python `TextBlob` library to automatically classify user reviews as positive or negative, providing reasoning based on text polarity and subjectivity.
- To run the Python API:
```shell
py -m uvicorn sentiment_analysis:app --reload
```
_Note:_ Run the script in the AI-feature folder

## Technologies Used
- Java 17+
- Spring Boot
- Spring Security
- JWT
- OAuth 2.0 (Google)
- JPA
- Maven
- Python `TextBlob` library and `FastAPI`