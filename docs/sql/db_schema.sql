CREATE DATABASE IF NOT EXISTS bank_db;

use bank_db;

CREATE TABLE USER ( 
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    email_verified_at TIMESTAMP NULL,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NULL,
    phone VARCHAR(20)
);

CREATE TABLE ACCOUNT (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    balance DECIMAL(15,2),
    account_number VARCHAR(20),
    status BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NULL,
    updated_at TIMESTAMP NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES USER(id)
        on delete CASCADE,
    Index idx_user_id(user_id)
);

CREATE TABLE TRANSACTION (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    account_id BIGINT UNSIGNED NOT NULL,
    amount DECIMAL(15,2),
    target_account_id BIGINT UNSIGNED NULL,
    type_of_transaction VARCHAR(50) CHECK (type_of_transaction IN ('deposit','withdrawal','transfer')),
    time_of_transaction TIMESTAMP,
    CONSTRAINT fk_account
        FOREIGN KEY (account_id)
        REFERENCES ACCOUNT(id),
    CONSTRAINT fk_target_account
        FOREIGN KEY (target_account_id)
        REFERENCES ACCOUNT(id),
    Index idx_account_id(account_id),
    Index idx_target_account_id(target_account_id)
);