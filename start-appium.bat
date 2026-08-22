@echo off
set ANDROID_HOME=C:\Users\elumalayan\AppData\Local\Android\Sdk
set ANDROID_SDK_ROOT=%ANDROID_HOME%
set PATH=%ANDROID_HOME%\platform-tools;%ANDROID_HOME%\emulator;%PATH%

echo ========================================
echo Starting Appium Server
echo ANDROID_HOME=%ANDROID_HOME%
echo ========================================
echo.
echo Press Ctrl+C to stop the server
echo.

appium
