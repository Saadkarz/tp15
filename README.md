# 🏦 Banque Service - GraphQL Banking Application

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![GraphQL](https://img.shields.io/badge/GraphQL-E10098?style=for-the-badge&logo=graphql&logoColor=white)
![H2](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

*A modern banking service built with Spring Boot and GraphQL*

**Author:** [Saad Karzouz](https://github.com/saadkarzouz)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Screenshots](#-screenshots)
- [Project Structure](#-project-structure)
- [Database Schema](#-database-schema)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 Overview

**Banque Service** is a comprehensive banking application that demonstrates modern software development practices using Spring Boot and GraphQL. The application provides a robust API for managing bank accounts and transactions, featuring real-time data queries, mutations, and statistics aggregation.

This project showcases:
- ✅ RESTful principles through GraphQL
- ✅ Clean architecture with separation of concerns
- ✅ JPA/Hibernate for database operations
- ✅ Real-time transaction management
- ✅ Statistical data analysis
- ✅ Exception handling and validation

---

## ✨ Features

### 🏦 Account Management
- Create and manage bank accounts (COURANT & EPARGNE)
- Query all accounts or specific accounts by ID
- Calculate aggregate statistics (total balance, count, average)
- Real-time balance tracking

### 💰 Transaction Management
- Record deposits (DEPOT) and withdrawals (RETRAIT)
- Track transaction history per account
- Global transaction statistics
- Date-based transaction tracking

### 📊 Analytics & Statistics
- **Account Statistics:**
  - Total number of accounts
  - Sum of all balances
  - Average balance across accounts
  
- **Transaction Statistics:**
  - Total transaction count
  - Sum of all deposits
  - Sum of all withdrawals

### 🛡️ Error Handling
- Custom GraphQL exception handler
- Clear error messages
- Validation for all operations

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Spring Boot** | 3.5.7 | Application framework |
| **Java** | 17 | Programming language |
| **Spring Data JPA** | - | Data persistence |
| **GraphQL** | - | API query language |
| **H2 Database** | - | In-memory database |
| **Lombok** | - | Boilerplate reduction |
| **Maven** | 3.9.11 | Build tool |
| **Hibernate** | 6.6.33 | ORM framework |

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.9+**
- **Git** (optional)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/saadkarzouz/banque-service.git
   cd banque-service
   ```

2. **Configure Java 17**
   ```bash
   # Windows PowerShell
   $env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
   
   # Linux/Mac
   export JAVA_HOME=/path/to/jdk-17
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the application**
   - 🌐 **GraphiQL Interface:** http://localhost:8082/graphiql
   - 🗄️ **H2 Console:** http://localhost:8082/h2-console
   - 📡 **GraphQL Endpoint:** http://localhost:8082/graphql

### H2 Database Configuration

When accessing the H2 console:
- **JDBC URL:** `jdbc:h2:mem:banque`
- **Username:** `sa`
- **Password:** *(leave empty)*

---

## 📚 API Documentation

### GraphQL Schema

#### Types

```graphql
enum TypeCompte {
    COURANT
    EPARGNE
}

enum TypeTransaction {
    DEPOT
    RETRAIT
}

type Compte {
    id: ID
    solde: Float
    dateCreation: String
    type: TypeCompte
}

type Transaction {
    id: ID
    montant: Float
    date: String
    type: TypeTransaction
    compte: Compte
}
```

#### Queries

##### Get All Accounts
```graphql
query {
    allComptes {
        id
        solde
        dateCreation
        type
    }
}
```

##### Get Account by ID
```graphql
query {
    compteById(id: 1) {
        id
        solde
        type
    }
}
```

##### Get Account Statistics
```graphql
query {
    totalSolde {
        count
        sum
        average
    }
}
```

##### Get Account Transactions
```graphql
query {
    compteTransactions(id: 1) {
        id
        montant
        date
        type
    }
}
```

##### Get All Transactions
```graphql
query {
    allTransactions {
        id
        montant
        date
        type
        compte {
            id
        }
    }
}
```

##### Get Transaction Statistics
```graphql
query {
    transactionStats {
        count
        sumDepots
        sumRetraits
    }
}
```

#### Mutations

##### Create Account
```graphql
mutation {
    saveCompte(compte: {
        solde: 1500.0
        dateCreation: "2024/11/18"
        type: COURANT
    }) {
        id
        solde
        type
    }
}
```

##### Add Transaction
```graphql
mutation {
    addTransaction(transaction: {
        compteId: 1
        montant: 500.0
        date: "2024/11/18"
        type: DEPOT
    }) {
        id
        montant
        type
        compte {
            id
        }
    }
}
```

---

## 📸 Screenshots

### 1. GraphiQL Interface - Query All Accounts
![Query All Accounts](Screenshots/Screenshot%202025-11-16%20201830.png)
*Testing the `allComptes` query to retrieve all bank accounts with their details including ID, balance, creation date, and account type.*

### 2. Query Account by ID
![Query by ID](Screenshots/Screenshot%202025-11-16%20202023.png)
*Fetching a specific account using the `compteById` query with parameterized ID input.*

### 3. Query with Variables
![Query with Variables](Screenshots/Screenshot%202025-11-16%20202047.png)
*Demonstrating GraphQL variables feature to make queries reusable and dynamic.*

### 4. Create New Account Mutation
![Create Account](Screenshots/Screenshot%202025-11-16%20202157.png)
*Using the `saveCompte` mutation to create a new bank account with initial balance and type.*

### 5. Account Statistics Query
![Account Statistics](Screenshots/Screenshot%202025-11-16%20202225.png)
*Retrieving aggregate statistics showing total count, sum of balances, and average balance across all accounts.*

### 6. Add Transaction Mutation
![Add Transaction](Screenshots/Screenshot%202025-11-16%20202309.png)
*Recording a new transaction (deposit or withdrawal) associated with an account.*

### 7. Transaction Statistics
![Transaction Statistics](Screenshots/Screenshot%202025-11-16%20202332.png)
*Analyzing global transaction data including count, total deposits, and total withdrawals.*

---

## 📁 Project Structure

```
banque-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── banque_service/
│   │   │       └── demo/
│   │   │           ├── controllers/
│   │   │           │   └── CompteControllerGraphQL.java
│   │   │           ├── dto/
│   │   │           │   └── TransactionRequest.java
│   │   │           ├── entities/
│   │   │           │   ├── Compte.java
│   │   │           │   ├── Transaction.java
│   │   │           │   ├── TypeCompte.java
│   │   │           │   └── TypeTransaction.java
│   │   │           ├── exceptions/
│   │   │           │   └── GraphQLExceptionHandler.java
│   │   │           ├── repositories/
│   │   │           │   ├── CompteRepository.java
│   │   │           │   └── TransactionRepository.java
│   │   │           └── DemoApplication.java
│   │   └── resources/
│   │       ├── graphql/
│   │       │   └── schema.graphqls
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── banque_service/
│               └── demo/
│                   └── DemoApplicationTests.java
├── Screenshots/
├── pom.xml
└── README.md
```

---

## 🗄️ Database Schema

### Tables

#### COMPTE
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Account identifier |
| solde | DOUBLE | Account balance |
| date_creation | DATE | Creation date |
| type | VARCHAR(50) | Account type (COURANT/EPARGNE) |

#### TRANSACTION
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Transaction identifier |
| montant | DOUBLE | Transaction amount |
| date | DATE | Transaction date |
| type | VARCHAR(50) | Transaction type (DEPOT/RETRAIT) |
| compte_id | BIGINT (FK) | Associated account |

### Relationships
- **Transaction** → **Compte**: Many-to-One (Multiple transactions can belong to one account)

---

## 🔧 Configuration

### Application Properties

```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:banque
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Server Configuration
server.port=8082

# GraphQL Configuration
spring.graphql.graphiql.enabled=true
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Saad Karzouz**

- GitHub: [@saadkarzouz](https://github.com/saadkarzouz)
- Email: saad.karzouz@example.com

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- GraphQL Foundation for the query language
- H2 Database for the lightweight in-memory database
- All contributors and supporters

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

Made with ❤️ by Saad Karzouz

</div>
