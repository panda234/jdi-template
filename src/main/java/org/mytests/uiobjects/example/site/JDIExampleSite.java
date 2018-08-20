package org.mytests.uiobjects.example.site;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.mytests.uiobjects.example.entities.User;
import org.mytests.uiobjects.example.site.pages.HomePage;
import org.mytests.uiobjects.example.site.pages.TablePage;
import org.mytests.uiobjects.example.site.sections.LoginForm;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class JDIExampleSite extends WebSite {
    public static HomePage homePage;

    @JTable(
            root = @FindBy(xpath = "html/body/div[1]/div/main/div[2]")
    )
    public static JTable table;

    public static LoginForm loginForm;

    public static TablePage TablePage;

    @FindBy(css = ".login-block__submit-but>button")
    public static Button login;

    @Step
    public static void login() {
        loginForm.loginAs(new User());
        login.click();
    }

    @Step
    public static void selectCheckBox() {
        ICell ans = (ICell) table;
        System.out.println(ans + "--------------------------------------");
    }
}
