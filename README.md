# Appium Test Automation Framework

Production-grade Appium mobile test automation framework for Android using Java, TestNG, and Allure.

## Prerequisites

- Java 17+
- Maven 3.9+
- Appium 2.x (`npm install -g appium`)
- UiAutomator2 driver (`appium driver install uiautomator2`)
- Android SDK with emulator or physical device
- Allure CLI (optional, for reports)

## Project Structure

```
├── shared/
│   ├── configs/          # Environment configs (dev, staging, prod)
│   ├── test-data/        # Shared test data (users, navigation)
│   └── apps/             # APK files (not committed)
├── frameworks/java/      # Java + TestNG + Allure framework
│   ├── src/main/java/    # Page objects, utilities, config, driver
│   ├── src/test/java/    # Test classes
│   └── src/test/resources/ # Log4j2, Allure config, env overrides
├── docker-compose.yml    # Appium server + Android emulator
└── .github/workflows/    # CI/CD pipelines
```

## Quick Start

1. Start Android emulator or connect a device:
   ```bash
   emulator -avd Pixel_6_API_34
   ```

2. Start Appium server:
   ```bash
   appium --base-path /wd/hub
   ```

3. Place your APK in `shared/apps/`:
   ```bash
   cp your-app.apk shared/apps/app-debug.apk
   ```

4. Run tests:
   ```bash
   cd frameworks/java
   mvn clean test
   ```

## Environment Selection

Set `TEST_ENV` environment variable to switch environments:

```bash
# Dev (default)
TEST_ENV=dev mvn clean test

# Staging
TEST_ENV=staging mvn clean test

# Production
TEST_ENV=prod mvn clean test
```

## Reports

Generate Allure report after test execution:

```bash
cd frameworks/java
mvn allure:serve
```

## Docker

Run tests with Docker (Appium server + Android emulator):

```bash
docker-compose up -d
cd frameworks/java
mvn clean test -DTEST_ENV=dev
```

## CI/CD

GitHub Actions workflows are provided in `.github/workflows/`:
- `ci-java.yml` - Runs Java tests with emulator
