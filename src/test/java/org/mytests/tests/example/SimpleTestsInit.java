package org.mytests.tests.example;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.mytests.uiobjects.example.site.JDIExampleSite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.util.HashMap;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static org.mytests.uiobjects.example.site.SQLHelper.*;

public class SimpleTestsInit extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        WebSite.init(JDIExampleSite.class);
        logger.info("Run Tests");
    }

    @BeforeSuite
    @DataProvider(name = "TestData")
    public Object[][] SetUpConnection() throws SQLException, ClassNotFoundException {
        resultSet = getTableFromSQLTable();
        Object[][] objects = new Object[3][];

        int i = 0;
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(resultSet.getString(1), resultSet.getString(2));
            objects[i] = new Object[]{hashMap};
            i++;
        }
        return objects;
    }

    @AfterSuite
    public void CloseTheConnection() {
        closeResultSet();
        closeSTMT();
        closeConnection();
    }
}
