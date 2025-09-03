# 📚 Library Management System

A complete Java-based console application for managing library books, members, and book issues/returns. Optimized for Windows users. Perfect for group projects and learning database management with Java.

## ✨ Features

- **📖 Book Management**: Add, display, search, delete, and update book records
- **👥 Member Management**: Add, display, search, delete, and update member records  
- **📋 Issue/Return System**: Issue books to members and track returns
- **🗄️ MySQL Database**: Persistent storage with proper database relationships
- **🖥️ Windows**: Optimized for Windows users

## 🛠️ Requirements

- **Java JDK 8 or higher** (OpenJDK or Oracle JDK)
- **MySQL Server 5.7 or higher**
- **MySQL Connector/J 9.4.0** (included in `src/` folder)
- **Git** (for cloning the repository)

## 🚀 Complete Setup Guide

### Step 1: Install Java Development Kit (JDK)

#### Windows:
1. **Open your web browser** (Chrome, Firefox, Edge, etc.)
2. **Go to this website**: [https://openjdk.org/](https://openjdk.org/)
3. **Click on "Latest LTS Release"** (it will say something like "OpenJDK 17 LTS")
4. **Scroll down and find "Windows x64"** 
5. **Click the download link** (it will download a .msi file)
6. **Find the downloaded file** (usually in Downloads folder)
7. **Double-click the .msi file** to start installation
8. **Click "Next"** through all the installation steps
9. **Click "Install"** when ready
10. **Wait for installation to complete**
11. **Click "Finish"**

**IMPORTANT - Add Java to PATH:**
1. **Press Windows key + R** on your keyboard
2. **Type "sysdm.cpl"** and press Enter
3. **Click "Environment Variables"** button
4. **Under "System Variables", find "Path"** and click "Edit"
5. **Click "New"** button
6. **Type this**: `C:\Program Files\Java\jdk-17\bin` (or whatever version you installed)
7. **Click "OK" on all windows**
8. **Restart your computer**

**Test if Java works:**
1. **Press Windows key + R**
2. **Type "cmd"** and press Enter
3. **Type these commands one by one:**
   ```cmd
   java -version
   javac -version
   ```
4. **If you see version numbers, Java is working!**



### Step 2: Install MySQL Server

#### Windows:
1. **Open your web browser**
2. **Go to**: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
3. **Click "Download"** for "MySQL Installer for Windows"
4. **Click "Download"** again (ignore the "Sign up" option)
5. **Find the downloaded file** (usually in Downloads folder)
6. **Right-click the file** and select "Run as administrator"
7. **Click "Yes"** if Windows asks for permission
8. **Choose "Developer Default"** and click "Next"
9. **Click "Next"** through all the setup steps
10. **When it asks for a password, create one** (write it down somewhere safe!)
11. **Click "Next"** and "Execute" to finish installation
12. **Click "Finish"**

**Make sure MySQL is running:**
1. **Press Windows key + R**
2. **Type "services.msc"** and press Enter
3. **Find "MySQL80"** in the list
4. **Make sure it says "Running"** next to it
5. **If not, right-click it and select "Start"**



### Step 3: Get the Project Files

1. **Open Command Prompt** (press Windows key + R, type "cmd", press Enter)

2. **Go to a folder where you want to put the project:**
   - **Example**: `cd C:\Users\YourName\Desktop`

3. **Copy and paste this command:**
   ```bash
   git clone https://github.com/rsreehari/LibraryManagement.git
   ```

4. **Press Enter and wait for it to download**

5. **Go into the project folder:**
   ```bash
   cd LibraryManagement
   ```

6. **Check what files you have:**
   - **Windows**: `dir`
   - **macOS**: `ls`

### Step 4: Setup Database

1. **Open Command Prompt** (press Windows key + R, type "cmd", press Enter)

2. **Connect to MySQL:**
   ```cmd
   mysql -u root -p
   ```

3. **Type your MySQL password** (the one you created earlier) and press Enter

4. **Copy and paste this command:**
   ```sql
   source database_setup.sql;
   ```

5. **Press Enter** - you should see tables being created

6. **Check if it worked:**
   ```sql
   SHOW DATABASES;
   USE Library;
   SHOW TABLES;
   ```

7. **Exit MySQL:**
   ```sql
   EXIT;
   ```

### Step 5: Update Database Password

1. **Open File Explorer** (press Windows key + E)

2. **Go to the LibraryManagement folder**

3. **Go to the "lib" folder**

4. **Right-click on "DatabaseConnection.java"**

5. **Select "Open with" → "Notepad"**

6. **Find this line:**
   ```java
   private static final String PASSWORD = "SREEHARI@123";
   ```

7. **Change "SREEHARI@123" to your MySQL password**

8. **Save the file** (press Ctrl+S)

### Step 6: Run the Application

#### Windows Users:
**EASY WAY:**
1. **Double-click on "run.bat"** in the LibraryManagement folder
2. **Wait for it to compile and run**

**MANUAL WAY:**
1. **Open Command Prompt** in the LibraryManagement folder
2. **Type this command:**
   ```cmd
   javac -cp "lib/*;src/*" lib/*.java
   ```
3. **Press Enter and wait**
4. **Type this command:**
   ```cmd
   java -cp "lib;src/*" LibraryManagement
   ```
5. **Press Enter to run**



## 🎯 How to Use the Application

### First Time Running:
1. **The program will test your database connection**
2. **If successful**: You'll see "Database connected successfully!"
3. **If failed**: Check your password in DatabaseConnection.java

### Main Menu:
- **Type 1** and press Enter → Book Management
- **Type 2** and press Enter → Member Management  
- **Type 3** and press Enter → Issue/Return Book
- **Type 4** and press Enter → Exit

### Book Management:
- **Type 1** → Add a new book
- **Type 2** → See all books
- **Type 3** → Find a specific book
- **Type 4** → Delete a book
- **Type 5** → Change book information
- **Type 6** → Go back to main menu

### Member Management:
- **Type 1** → Add a new member
- **Type 2** → See all members
- **Type 3** → Find a specific member
- **Type 4** → Delete a member
- **Type 5** → Change member information
- **Type 6** → Go back to main menu

### Issue/Return:
- **Type 1** → Give a book to someone
- **Type 2** → See all borrowed books
- **Type 3** → Mark a book as returned
- **Type 4** → Go back to main menu

## 🔧 If Something Goes Wrong

### "Java not found" Error:
- **Reinstall Java** following Step 1
- **Restart your computer** after installing Java

### "MySQL connection failed" Error:
- **Check if MySQL is running** (Step 2)
- **Check your password** in DatabaseConnection.java (Step 5)

### "Table doesn't exist" Error:
- **Run the database setup again** (Step 4)

### "ClassNotFoundException" Error:
- **Recompile the project** (Step 6)

## 📁 What Each File Does

```
lib/
├── Book.java              # Handles all book operations
├── DatabaseConnection.java # Connects to MySQL database
├── Issue.java             # Handles book lending/returns
├── LibraryManagement.java # Main program (start here!)
├── Member.java            # Handles all member operations
└── MenuLib.java           # Shows all the menus

src/
└── mysql-connector-j-9.4.0.jar  # MySQL driver (don't delete!)

database_setup.sql         # Creates the database (run this first)
run.bat                    # Double-click to run (Windows only)
.gitignore                 # Git file (don't worry about this)
README.md                  # This file you're reading now
```

## 🎉 Success Checklist

Before you're done, make sure:
- [ ] Java works (`java -version` shows a version number)
- [ ] MySQL is running
- [ ] Database "Library" exists with tables
- [ ] Application starts without errors
- [ ] You can see the main menu
- [ ] Database connection test passes

## 📞 Need Help?

1. **Check this README first** - most problems are solved here
2. **Ask your group members** - they might have the same issue
3. **Check the troubleshooting section** above
4. **Make sure you followed every step exactly**

## 🚀 Ready to Start?

1. **Follow Steps 1-6 above**
2. **Run the application**
3. **Test the database connection**
4. **Try adding a book or member**
5. **You're now a Library Management System expert!**

---

**Happy Coding! 🚀**

*If this README helped you, give the project a ⭐ star on GitHub!*
