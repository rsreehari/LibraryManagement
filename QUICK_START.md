# 🚀 Quick Start Guide

**For experienced users who want to get running fast!**

## ⚡ 5-Minute Setup

### 1. Install Java & MySQL
```bash
# Windows: Download from openjdk.org
# macOS: brew install openjdk@17 mysql
```

### 2. Clone & Setup
```bash
git clone https://github.com/rsreehari/LibraryManagement.git
cd LibraryManagement
```

### 3. Database Setup
```bash
mysql -u root -p
source database_setup.sql;
exit;
```

### 4. Update Password
Edit `lib/DatabaseConnection.java` - change password to yours

### 5. Run!
```bash
# Windows: Double-click run.bat
# macOS: javac -cp "lib/*:src/*" lib/*.java && java -cp "lib:src/*" LibraryManagement
```

## 🎯 That's it! 

If you get errors, check the full README.md for detailed troubleshooting.

**Need help?** → Check the main README.md file!
