@echo off
echo Library Management System
echo ========================
echo.

echo Compiling Java files...
javac -cp "lib/*;src/*" lib/*.java
if %errorlevel% neq 0 (
    echo Compilation failed! Please check for errors.
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo.
echo Starting Library Management System...
echo.
java -cp "lib;src/*" LibraryManagement

pause
