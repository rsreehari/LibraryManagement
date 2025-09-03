-- Library Management System Database Setup
-- Run this file in MySQL to create the required database and tables

-- Create database
CREATE DATABASE IF NOT EXISTS Library;

-- Use database
USE Library;

-- Create bookrecord table
CREATE TABLE IF NOT EXISTS bookrecord (
    bno VARCHAR(20) PRIMARY KEY,
    bname VARCHAR(100) NOT NULL,
    auth VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    pub VARCHAR(100) NOT NULL,
    qty INT NOT NULL,
    dop DATE NOT NULL
);

-- Create member table
CREATE TABLE IF NOT EXISTS member (
    mno INT PRIMARY KEY,
    mname VARCHAR(100) NOT NULL,
    mob VARCHAR(15) NOT NULL,
    dop DATE NOT NULL,
    addr TEXT NOT NULL
);

-- Create issue table
CREATE TABLE IF NOT EXISTS issue (
    bno VARCHAR(20),
    mno INT,
    d_o_issue DATE NOT NULL,
    d_o_ret DATE,
    PRIMARY KEY (bno, mno, d_o_issue),
    FOREIGN KEY (bno) REFERENCES bookrecord(bno) ON DELETE CASCADE,
    FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);

-- Insert sample data (optional)
INSERT INTO bookrecord VALUES 
('B001', 'Java Programming', 'John Doe', 45, 'Tech Books Inc', 10, '2024-01-15'),
('B002', 'Database Design', 'Jane Smith', 35, 'Data Press', 8, '2024-01-20'),
('B003', 'Web Development', 'Mike Johnson', 40, 'Web Publishers', 12, '2024-02-01');

INSERT INTO member VALUES 
(1001, 'Alice Brown', '555-0101', '2024-01-10', '123 Main St, City'),
(1002, 'Bob Wilson', '555-0102', '2024-01-12', '456 Oak Ave, Town'),
(1003, 'Carol Davis', '555-0103', '2024-01-15', '789 Pine Rd, Village');

-- Show created tables
SHOW TABLES;

-- Show table structures
DESCRIBE bookrecord;
DESCRIBE member;
DESCRIBE issue;

-- Show sample data
SELECT * FROM bookrecord;
SELECT * FROM member;
