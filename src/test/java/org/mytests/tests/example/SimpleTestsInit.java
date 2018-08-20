package org.mytests.tests.example;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.mytests.uiobjects.example.site.JDIExampleSite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

public class SimpleTestsInit extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
       WebSite.init(JDIExampleSite.class);
        logger.info("Run Tests");
    }

    Connection conn = null;

    // Object of Statement. It is used to create a Statement to execute the query
    Statement stmt = null;

    //Object of ResultSet => 'It maintains a cursor that points to the current row in the result set'
    ResultSet resultSet = null;
    WebDriver driver;

    @BeforeTest
    public void SetUpConnection() throws SQLException, ClassNotFoundException {

        // Register JDBC driver (JDBC driver name and Database URL)
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/121", "root", "");

        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("select * from testdata");

        while (resultSet.next()){
            String temp = resultSet.getString(1);
            System.out.println(123);
        }

        System.setProperty("webdriver.chrome.driver", "<Path of Driver>\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        // Code to disable the popup of saved password
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.get("<URL>");
    }
}
