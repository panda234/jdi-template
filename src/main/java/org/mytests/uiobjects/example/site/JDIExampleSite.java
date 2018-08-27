package org.mytests.uiobjects.example.site;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Input;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import org.mytests.uiobjects.example.site.pages.ConvertPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import java.sql.SQLException;
import java.util.List;

import static org.mytests.uiobjects.example.site.SQLHelper.createTable;
import static org.mytests.uiobjects.example.site.SQLHelper.insertRequest;

public class JDIExampleSite extends WebSite {

    public static ConvertPage convertPage;

    @FindBy(css = ".converter-min_value--convert")
    public static Input setMoney;

    @FindBy(css = "#calculate")
    public static Button convert;

    @FindBy(css = ".default-table-finance.course-two-col:nth-child(1)>tbody>tr>td>a")
    public static List<WebElement> bankName;

    @FindBy(xpath = ".//*[@id='biznes']/div/div[1]/div/div[4]/table/tbody/tr/td[2]")
    public static List<WebElement> summ;

    @FindBy(xpath = ".//*[@id='biznes']/div/div[1]/div/div[4]/table/tbody/tr/td[3]/div")
    public static List<WebElement> courseOfMoney;

    @FindBy(css = ".converter-min_currency-list.rateC")
    public static Dropdown convertFrom;

    @FindBy(css = ".converter-min_currency-list.rateR")
    public static Dropdown convertTo;

    @Step
    public static void convertMoney(String convertToValue) {
        setMoney.setValue("1000");
        convertFrom.select("RUB российский рубль");
        convertTo.select(convertToValue);
        convert.click();
    }

    @Step
    public static void updateSQLDataBase() throws SQLException {
        createTable();
        insertRequest(bankName, summ, courseOfMoney);
    }
}
