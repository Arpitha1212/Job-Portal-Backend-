-- Create companies table
CREATE TABLE IF NOT EXISTS companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    logo VARCHAR(500),
    industry VARCHAR(100) NOT NULL,
    size VARCHAR(50) NOT NULL,
    rating DECIMAL(3,2) NOT NULL,
    locations VARCHAR(1000),
    founded INT NOT NULL,
    description TEXT,
    employees INT,
    website VARCHAR(500),
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)  NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
    );


    -- Create users table
CREATE TABLE IF NOT EXISTS users (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255) NOT NULL,
         email VARCHAR(255) NOT NULL UNIQUE,
         password_hash VARCHAR(500) NOT NULL,
         mobile_number VARCHAR(20) UNIQUE,
         role_id BIGINT NOT NULL,
         company_id BIGINT NULL,
         created_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
         created_by    VARCHAR(20)  NOT NULL,
         updated_at    TIMESTAMP   DEFAULT NULL,
         updated_by    VARCHAR(20) DEFAULT NULL,
         CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id),
         CONSTRAINT fk_users_company FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE SET NULL
);