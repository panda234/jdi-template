package org.mytests.tests.example;

import org.testng.annotations.Test;

import static org.mytests.uiobjects.example.site.JDIExampleSite.*;

//@Listeners({ReportPortalTestNGListener.class})
public class SimpleTest extends SimpleTestsInit {

    @Test
    public void loginTest() throws InterruptedException {
        homePage.open();
        login();
    }

    @Test
    public void tableTest() {
        TablePage.open();
        selectCheckBox();
    }
}
