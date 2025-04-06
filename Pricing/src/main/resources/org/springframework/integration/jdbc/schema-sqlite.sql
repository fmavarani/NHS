-- Table for User entity
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);

-- Table for Alert entity
CREATE TABLE alert (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_url TEXT NOT NULL,
    cron TEXT NOT NULL,
    desired_price REAL NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);