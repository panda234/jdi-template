package org.mytests.tests.example;

import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.mytests.uiobjects.example.site.JDIExampleSite.*;

public class SimpleTest extends SimpleTestsInit {

    @Test(dataProvider = "TestData")
    public void loginTest(HashMap<String, String> data) throws SQLException {
        convertPage.open();
        convertMoney("1000", "RUB российский рубль", getValueFromMap(data));
        mainCours();
    }

    private String getValueFromMap(HashMap<String, String> data) {
        Map.Entry<String, String> entry = data.entrySet().iterator().next();
        return entry.getKey() + " " + entry.getValue();
    }
}
