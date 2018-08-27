package org.mytests.uiobjects.example.site;

import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SQLHelper {

    public static Statement stmt = null;
    private static StringBuilder request = null;
    private static String TableName;

    private static StringBuilder createTableRequest() {
        return request = new StringBuilder("CREATE TABLE `" + getTableName() + "` (" +
                "`BankName` VARCHAR(45) NOT NULL," +
                "`Value` VARCHAR(45) NULL," +
                "`Cours` VARCHAR(45) NULL," +
                "PRIMARY KEY (`BankName`));");
    }

    private static String getTableName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        if (TableName == null)
            TableName = dtf.format(now);
        return TableName;
    }

    static void createTable() throws SQLException {
        stmt.executeUpdate(String.valueOf(createTableRequest()));
    }

    static void insertRequest(List<WebElement> bankName, List<WebElement> sum, List<WebElement> courseOfMoney) throws SQLException {

        int cost = 0;
        if (courseOfMoney.size() == 2) {
            cost++;
        }

        for (int i = 0; i < bankName.size() - cost; i++) {
            int temp = 0;
            if (cost == 1 & i > 0) {
                temp++;
            }
            request = new StringBuilder("INSERT INTO `")
                    .append(TableName)
                    .append("` (`BankName`, `Value`, `Cours`) VALUES ('")
                    .append(bankName.get(i).getText())
                    .append("', '")
                    .append(sum.get(i).getText())
                    .append("', '")
                    .append(courseOfMoney.get(i - temp).getText())
                    .append("');");
            stmt.executeUpdate(String.valueOf(request));
        }
        TableName = null;
    }
}
