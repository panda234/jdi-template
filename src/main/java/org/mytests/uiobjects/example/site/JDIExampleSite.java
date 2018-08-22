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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JDIExampleSite extends WebSite {

    public static String TableName;

    public static ConvertPage convertPage;

    public static Statement stmt = null;

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
    public static void convertMoney(String money, String convertFromValue, String convertToValue) {
        setMoney.setValue(money);
        convertFrom.select(convertFromValue);
        convertTo.select(convertToValue);
        convert.click();
    }

    private static String getTableName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        if (TableName == null)
            TableName = dtf.format(now);
        return TableName;
    }

    private static StringBuilder request = null;

    private static StringBuilder createTable() {
       return request = new StringBuilder("CREATE TABLE `" + getTableName() + "` (" +
                "`BankName` VARCHAR(45) NOT NULL," +
                "`Value` VARCHAR(45) NULL," +
                "`Cours` VARCHAR(45) NULL," +
                "PRIMARY KEY (`BankName`));");
    }

    @Step
    public static void mainCours() throws SQLException {
        List<WebElement> bankNameList = bankName;
        List<WebElement> summList = summ;
        List<WebElement> courseOfMoneyList = courseOfMoney;

        int cost = 0;
        if (courseOfMoneyList.size() == 2) {
            cost++;
        }

        stmt.executeUpdate(String.valueOf(createTable()));

        for (int i = 0; i < bankNameList.size() - cost; i++) {
            int temp = 0;
            if (cost == 1 & i > 0) {
                temp++;
            }
            request = new StringBuilder("INSERT INTO `")
                    .append(TableName)
                    .append("` (`BankName`, `Value`, `Cours`) VALUES ('")
                    .append(bankNameList.get(i).getText())
                    .append("', '")
                    .append(summList.get(i).getText())
                    .append("', '")
                    .append(courseOfMoneyList.get(i - temp).getText())
                    .append("');");
            stmt.executeUpdate(String.valueOf(request));
        }
        TableName = null;
    }
}
