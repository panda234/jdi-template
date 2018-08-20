package org.mytests.uiobjects.example.site;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Input;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import org.mytests.uiobjects.example.site.pages.ConvertPage;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class JDIExampleSite extends WebSite {
    public static ConvertPage convertPage;

    @FindBy(css = ".converter-min_value--convert")
    public static Input setMoney;

    @FindBy(css = ".converter-min_value--readonly")
    public static Input getMoney;

    @FindBy(css = "#calculate")
    public static Button convert;

    @FindBy(css = ".default-table-finance.course-two-col:nth-child(1)")
    public static Table nbu;

    @FindBy(css = ".default-table-finance.course-two-col:nth-child(2)")
    public static Table notNbu;

    @FindBy(css = ".converter-min_currency-list.rateC")
    public static Dropdown convertFrom;

    @FindBy(css = ".converter-min_currency-list.rateR")
    public static Dropdown convertTo;

    @Step
    public static void convertMoney(String money, String convertFromValue, String convertToValue) {
        setMoney.setValue(money);
        convertFrom.select(convertFromValue);
        convertTo.select(convertToValue);
        convert.click();
    }
}
