@echo off
echo.
echo ========================================
echo   Running Appium Tests
echo ========================================
echo.
echo Make sure:
echo  1. Emulator is running
echo  2. Appium server is started (run start-appium.bat in another window)
echo.
pause
echo.
echo Starting tests...
echo.

cd frameworks\java
call mvn clean test

echo.
echo ========================================
echo   Tests Complete!
echo ========================================
echo.
pause
