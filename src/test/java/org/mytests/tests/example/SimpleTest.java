package org.mytests.tests.example;

import org.testng.annotations.Test;

import static org.mytests.uiobjects.example.site.JDIExampleSite.convertMoney;
import static org.mytests.uiobjects.example.site.JDIExampleSite.convertPage;

public class SimpleTest extends SimpleTestsInit {

    @Test
    public void loginTest() throws InterruptedException {
        convertPage.open();
        convertMoney("1000", "IRR иранский риал", "HKD гонконгский доллар");
    }
}
