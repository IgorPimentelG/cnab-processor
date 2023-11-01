CREATE TABLE IF NOT EXISTS transactions(
    id CHAR(36) PRIMARY KEY,
    type smallint NOT NULL,
    cpf CHAR(14) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    store_holder VARCHAR(255) NOT NULL,
    store_name VARCHAR(255) NOT NULL,
    registered_at DATETIME NOT NULL
);