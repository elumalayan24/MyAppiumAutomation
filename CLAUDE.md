# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build System

This project has a dual build setup:
- **Root level**: Minimal Gradle wrapper for Android Studio compatibility
- **Test framework**: Maven-based (in `frameworks/java/`)

Most development commands use Maven from the `frameworks/java/` directory.

## Commands

### Running Tests

From root directory:
```bash
./gradlew test
```

From `frameworks/java/` directory (preferred):
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=PracticeAppTest

# Run specific test method
mvn test -Dtest=PracticeAppTest#testAppLaunches
```

### Reports

Generate and view Allure reports:
```bash
cd frameworks/java
mvn allure:serve
```

### Docker Environment

Start Appium server + Android emulator:
```bash
docker-compose up -d
```

Access emulator VNC at http://localhost:6080

### Local Setup

Before running tests locally:
1. Start Android emulator: `emulator -avd <your_avd_name>`
2. Start Appium server: `appium --base-path /wd/hub`
3. Ensure the Practice App (`com.expandtesting.practice`) is installed on the emulator

## Architecture

### Driver Management (ThreadLocal Pattern)

**DriverManager** (`com.automation.driver.DriverManager`) uses ThreadLocal storage for parallel test execution:
- `initDriver()` - Creates driver for current thread
- `getDriver()` - Retrieves driver for current thread
- `quitDriver()` - Cleans up driver for current thread

**DriverFactory** (`com.automation.driver.DriverFactory`) builds AndroidDriver from configuration.

All driver access goes through DriverManager - never instantiate AndroidDriver directly.

### Configuration System

**ConfigManager** (`com.automation.config.ConfigManager`) is a thread-safe singleton that:
1. Reads `TEST_ENV` environment variable (defaults to `dev`)
2. Loads config from `frameworks/java/src/test/resources/environments/{env}.json` (local override)
3. Falls back to `shared/configs/{env}.json` (shared config)

Configuration classes:
- **AppiumConfig**: Appium server connection (host, port, basePath)
- **EnvironmentConfig**: Device capabilities, timeouts, app info
- **ConfigManager**: Singleton loader with environment resolution

Access via `ConfigManager.getInstance().getAppiumConfig()` etc.

### Page Object Pattern

**BasePage** (`com.automation.pages.BasePage`):
- All page objects extend this abstract class
- Constructor initializes driver and WebDriverWait from ConfigManager timeouts
- Uses Appium PageFactory with AppiumFieldDecorator
- Provides common methods: `click()`, `type()`, `getText()`, `isDisplayed()`, `waitForVisible()`, `waitForClickable()`
- All methods annotated with `@Step` for Allure reporting

**PracticeAppPage** (`com.automation.pages.PracticeAppPage`):
- Page object for the Practice App countries list
- Uses `@AndroidFindBy` annotations for element locators
- Methods: `isCountriesListDisplayed()`, `getCountryItemCount()`, `getCountryNames()`, `clickCountryAtIndex()`, `clickFirstCountry()`

### Test Base Class

**BaseTest** (`com.automation.tests.BaseTest`):
- All test classes extend this
- `@BeforeMethod`: Calls `DriverManager.initDriver()` before each test
- `@AfterMethod`: Calls `DriverManager.quitDriver()` after each test
- Provides SLF4J logger instance

### TestNG Configuration

**testng.xml** defines:
- Suite runs in parallel (`parallel="tests"`, `thread-count="1"`)
- TestListener attached for screenshot capture on failure, Allure integration
- Currently runs: PracticeAppTest

### Utilities

- **WaitUtils**: Custom wait conditions beyond WebDriverWait
- **GestureUtils**: Swipe, scroll, tap gestures using W3C Actions API
- **ScreenshotUtils**: Capture screenshots, attach to Allure reports
- **LoggerUtil**: Standardized logging wrapper around SLF4J
- **TestDataUtil**: Load JSON test data from classpath or shared directory

### Listeners

**TestListener** (`com.automation.listeners.TestListener`):
- Captures screenshots on test failure
- Attaches screenshots to Allure reports
- Logs test lifecycle events

**RetryAnalyzer** (`com.automation.listeners.RetryAnalyzer`):
- Retries failed tests up to 2 times
- Set on test methods via `@Test(retryAnalyzer = RetryAnalyzer.class)`

## File Locations

- **Framework code**: `frameworks/java/src/main/java/com/automation/`
- **Test code**: `frameworks/java/src/test/java/com/automation/tests/`
- **Page objects**: `frameworks/java/src/main/java/com/automation/pages/`
- **Test resources**: `frameworks/java/src/test/resources/` (log4j2.xml, allure.properties)
- **Environment configs**: `frameworks/java/src/test/resources/environments/dev.json`
- **Shared configs**: `shared/configs/dev.json`
- **APK files**: `shared/apps/` (not committed to git)

## Adding New Tests

1. Create page object extending `BasePage` in `frameworks/java/src/main/java/com/automation/pages/`
2. Create test class extending `BaseTest` in `frameworks/java/src/test/java/com/automation/tests/`
3. Add test class to `testng.xml`
4. Use `@Test` annotation on test methods with Allure annotations (`@Epic`, `@Feature`, `@Story`, `@Severity`)
5. Access elements through page objects, not raw driver calls

## CI/CD

GitHub Actions workflow (`.github/workflows/ci-java.yml`):
- Triggers on push/PR to main affecting `frameworks/java/` or `shared/`
- Sets up Java 17, Maven, Appium, Android emulator (API 34)
- Runs `mvn clean test -DTEST_ENV=dev`
- Uploads Allure results and logs as artifacts

## Prerequisites

- Java 17+
- Maven 3.9+
- Appium 2.x with UiAutomator2 driver
- Android SDK with emulator or physical device
- Allure CLI (optional, for local reports)
