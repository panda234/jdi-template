package org.mytests.tests.example;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.mytests.uiobjects.example.site.JDIExampleSite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static org.mytests.uiobjects.example.site.JDIExampleSite.stmt;

public class SimpleTestsInit extends TestNGBase {

    private static ResultSet resultSet;

    private Connection conn = null;

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        WebSite.init(JDIExampleSite.class);
        logger.info("Run Tests");
    }

    @BeforeSuite
    @DataProvider(name = "TestData")
    public Object[][] SetUpConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/121", "root", "");
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("select * from testdata");

        Object[][] objects = new Object[3][];

        int i = 0;
        while (resultSet.next()) {
            HashMap hashMap = new HashMap();
            hashMap.put(resultSet.getString(1), resultSet.getString(2));
            objects[i] = new Object[]{hashMap};
            i++;
        }
        return objects;
    }

    @AfterSuite
    public void CloseTheConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception ignored) {
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception ignored) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ignored) {
            }
        }

    }
}
