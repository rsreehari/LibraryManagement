# 📚 Library Management System

A complete Java-based console application for managing library books, members, and book issues/returns. Perfect for group projects and learning database management with Java.

## ✨ Features

- **📖 Book Management**: Add, display, search, delete, and update book records
- **👥 Member Management**: Add, display, search, delete, and update member records  
- **📋 Issue/Return System**: Issue books to members and track returns
- **🗄️ MySQL Database**: Persistent storage with proper database relationships
- **🖥️ Cross-Platform**: Works on Windows, macOS, and Linux

## 🛠️ Requirements

- **Java JDK 8 or higher** (OpenJDK or Oracle JDK)
- **MySQL Server 5.7 or higher**
- **MySQL Connector/J 9.4.0** (included in `src/` folder)
- **Git** (for cloning the repository)

## 🚀 Complete Setup Guide

### Step 1: Install Java Development Kit (JDK)

#### Windows:
1. Go to [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
2. Download JDK 8 or higher for Windows x64
3. Run the installer and follow the setup wizard
4. **Important**: Add Java to your PATH environment variable
   - Right-click on "This PC" → Properties → Advanced system settings
   - Click "Environment Variables"
   - Under "System Variables", find "Path" and click "Edit"
   - Click "New" and add: `C:\Program Files\Java\jdk-version\bin`
   - Replace "version" with your actual JDK version (e.g., `jdk-17.0.2`)
5. Verify installation: Open Command Prompt and type:
   ```cmd
   java -version
   javac -version
   ```

#### macOS:
1. **Option A - Using Homebrew (Recommended):**
   ```bash
   brew install openjdk@17
   ```
   
   **Option B - Manual Installation:**
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Install the .dmg file
   
2. Verify installation:
   ```bash
   java -version
   javac -version
   ```



### Step 2: Install MySQL Server

#### Windows:
1. Go to [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
2. Download "MySQL Installer for Windows"
3. Run the installer as Administrator
4. Choose "Developer Default" or "Server only"
5. Follow the setup wizard
6. **IMPORTANT**: Remember your root password! (You'll need this later)
7. Make sure MySQL service is running:
   - Press `Win + R`, type `services.msc`
   - Find "MySQL80" or "MySQL" service
   - Make sure it's "Running" and "Automatic"

#### macOS:
1. **Option A - Using Homebrew (Recommended):**
   ```bash
   brew install mysql
   brew services start mysql
   ```
   
   **Option B - Manual Installation:**
   - Download from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
   - Install the .dmg file
   - Start MySQL from System Preferences

2. Set root password (first time only):
   ```bash
   mysql_secure_installation
   ```



### Step 3: Clone and Setup the Project

1. **Clone the repository:**
   ```bash
   git clone https://github.com/rsreehari/LibraryManagment.git
   cd LibraryManagment
   ```

2. **Verify project structure:**
   ```
   LibraryManagment/
   ├── lib/                          # Java source files
   │   ├── Book.java                # Book management operations
   │   ├── DatabaseConnection.java   # Database connection handling
   │   ├── Issue.java               # Issue/return operations
   │   ├── LibraryManagement.java   # Main application entry point
   │   ├── Member.java              # Member management operations
   │   └── MenuLib.java             # Menu system
   ├── src/                          # Dependencies
   │   └── mysql-connector-j-9.4.0.jar  # MySQL driver
   ├── database_setup.sql            # Database setup script
   ├── run.bat                       # Windows run script
   ├── run.sh                        # Linux/Mac run script
   └── README.md                     # This file
   ```

### Step 4: Setup Database

1. **Start MySQL and connect as root:**
   
   **Windows:**
   ```cmd
   mysql -u root -p
   ```
   
   **macOS/Linux:**
   ```bash
   mysql -u root -p
   ```
   
   Enter your root password when prompted.

2. **Run the database setup script:**
   ```sql
   source database_setup.sql;
   ```
   
   **Alternative method (copy-paste):**
   - Open `database_setup.sql` in a text editor
   - Copy all the SQL commands
   - Paste them into your MySQL command line
   - Press Enter to execute

3. **Verify database creation:**
   ```sql
   SHOW DATABASES;
   USE Library;
   SHOW TABLES;
   ```

4. **Check table structures:**
   ```sql
   DESCRIBE bookrecord;
   DESCRIBE member;
   DESCRIBE issue;
   ```

5. **View sample data:**
   ```sql
   SELECT * FROM bookrecord;
   SELECT * FROM member;
   ```

6. **Exit MySQL:**
   ```sql
   EXIT;
   ```

### Step 5: Update Database Connection

1. **Open `lib/DatabaseConnection.java` in any text editor**
2. **Find this line:**
   ```java
   private static final String PASSWORD = "SREEHARI@123";
   ```
3. **Change it to your MySQL root password:**
   ```java
   private static final String PASSWORD = "YOUR_ACTUAL_PASSWORD_HERE";
   ```
4. **Save the file**

### Step 6: Compile and Run

#### Windows Users:
1. **Double-click `run.bat`** OR
2. **Open Command Prompt in the project folder and run:**
   ```cmd
   javac -cp "lib/*;src/*" lib/*.java
   java -cp "lib;src/*" LibraryManagement
   ```

#### macOS Users:
**Run manually:**
```bash
javac -cp "lib/*:src/*" lib/*.java
java -cp "lib:src/*" LibraryManagement
```

## 🎯 How to Use the Application

### First Run:
1. The system will test your database connection
2. If successful, you'll see: "Database connected successfully!"
3. If failed, check your MySQL password in `DatabaseConnection.java`

### Main Menu Options:
- **1. Book Management** - Manage all book records
- **2. Member Management** - Manage all member records
- **3. Issue/Return Book** - Handle book lending and returns
- **4. Exit** - Close the application

### Book Management:
- **Add Book**: Enter book details (code, name, author, price, publisher, quantity, purchase date)
- **Display Books**: View all books in the library
- **Search Book**: Find books by book code
- **Delete Book**: Remove books by book code
- **Update Book**: Modify existing book information

### Member Management:
- **Add Member**: Enter member details (code, name, mobile, membership date, address)
- **Display Members**: View all library members
- **Search Member**: Find members by name
- **Delete Member**: Remove members by member code
- **Update Member**: Modify existing member information

### Issue/Return System:
- **Issue Book**: Assign a book to a member with issue date
- **Display Issued Books**: View all currently issued books
- **Return Book**: Mark a book as returned with return date

## 🔧 Troubleshooting

### Common Issues and Solutions:

#### 1. "Database connection failed" Error:
- **Check MySQL is running:**
  - Windows: `services.msc` → MySQL service
  - macOS: `brew services list` or System Preferences
  - Linux: `sudo systemctl status mysql`
- **Verify password in `DatabaseConnection.java`**
- **Check MySQL port (default: 3306)**

#### 2. "ClassNotFoundException" Error:
- **Recompile the project:**
  ```bash
  javac -cp "lib/*;src/*" lib/*.java
  ```
- **Check classpath includes both `lib` and `src` folders**

#### 3. "Access denied for user 'root'" Error:
- **Verify MySQL root password**
- **Check if MySQL allows root connections from localhost**
- **Try connecting manually: `mysql -u root -p`**

#### 4. "Table doesn't exist" Error:
- **Run the database setup script again**
- **Check if you're using the correct database: `USE Library;`**

#### 5. Java not found:
- **Verify Java installation: `java -version`**
- **Check PATH environment variable**
- **Restart Command Prompt/Terminal after installing Java**

### Platform-Specific Issues:

#### Windows:
- Use `run.bat` for easy execution
- Make sure MySQL service is running
- Check Windows Defender firewall settings

#### macOS:
- Use `run.sh` for easy execution
- If using Homebrew, ensure MySQL is started: `brew services start mysql`
- Check System Preferences → Security & Privacy for any blocked applications



## 📁 Project Structure Explained

```
lib/
├── Book.java              # Handles all book-related database operations
├── DatabaseConnection.java # Manages MySQL database connections
├── Issue.java             # Manages book issue/return operations
├── LibraryManagement.java # Main application with menu system
├── Member.java            # Handles all member-related database operations
└── MenuLib.java           # Sub-menu management for different sections

src/
└── mysql-connector-j-9.4.0.jar  # MySQL JDBC driver (required for database access)

database_setup.sql         # Complete database creation script
run.bat                    # Windows batch file for easy execution
.gitignore                 # Git ignore file (excludes compiled classes)
README.md                  # This comprehensive guide
```

## 🤝 Contributing to the Project

### For Group Members:
1. **Fork the repository** to your GitHub account
2. **Create a feature branch** for your work:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes** and test thoroughly
4. **Commit your changes:**
   ```bash
   git add .
   git commit -m "Add: description of your changes"
   ```
5. **Push to your branch:**
   ```bash
   git push origin feature/your-feature-name
   ```
6. **Create a Pull Request** to merge your changes

### Development Workflow:
1. **Always pull latest changes** before starting work:
   ```bash
   git pull origin main
   ```
2. **Test your changes** before committing
3. **Write clear commit messages**
4. **Update documentation** if you add new features

## 📚 Learning Resources

### Java:
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [W3Schools Java](https://www.w3schools.com/java/)
- [Java Code Examples](https://www.programiz.com/java-programming/examples)

### MySQL:
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [MySQL Tutorial](https://www.mysqltutorial.org/)
- [SQL Basics](https://www.w3schools.com/sql/)

### Database Design:
- [Database Design Principles](https://www.guru99.com/database-design.html)
- [Normalization](https://www.guru99.com/database-normalization.html)
- [ER Diagrams](https://www.lucidchart.com/pages/er-diagrams)

## 🎉 Success Checklist

Before considering the project complete, ensure:

- [ ] Java JDK is installed and working (`java -version`)
- [ ] MySQL Server is running and accessible
- [ ] Database "Library" is created with all tables
- [ ] Application compiles without errors
- [ ] Application connects to database successfully
- [ ] All menu options work correctly
- [ ] Sample data is inserted and viewable
- [ ] README.md is updated with your group information
- [ ] All group members can run the application

## 📞 Support

### For Group Members:
- Check this README first
- Review the troubleshooting section
- Ask questions in your group chat
- Use GitHub Issues for bug reports

### Common Questions:
- **Q: Can I change the database name?**
  - A: Yes, update both the SQL script and `DatabaseConnection.java`
  
- **Q: What if I forget my MySQL password?**
  - A: Reset it using MySQL's password reset procedures
  
- **Q: Can I use a different database (PostgreSQL, etc.)?**
  - A: Yes, but you'll need to modify the connection code and SQL syntax

## 📄 License

This project is created for educational purposes and group collaboration. Feel free to modify and extend it for your learning needs.

---

**Happy Coding! 🚀**

*If you found this README helpful, give it a ⭐ star on GitHub!*
