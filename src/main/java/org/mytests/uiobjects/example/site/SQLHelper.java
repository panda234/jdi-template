package org.mytests.uiobjects.example.site;

import org.openqa.selenium.WebElement;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SQLHelper {

    public static ResultSet resultSet;
    private static Statement stmt = null;
    private static StringBuilder createRequest = null;
    private static StringBuilder updateRequest = null;
    private static String TableName;
    private static Connection conn = null;

    private static StringBuilder createTableRequest() {
        TableName = null;
        return createRequest = new StringBuilder("CREATE TABLE `")
                .append(getTableName())
                .append("` (")
                .append("`BankName` VARCHAR(45) NOT NULL,")
                .append("`Value` VARCHAR(45) NULL,")
                .append("`Course` VARCHAR(45) NULL,")
                .append("PRIMARY KEY (`BankName`));");
    }

    public static ResultSet getTableFromSQLTable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/121", "root", "");
        stmt = conn.createStatement();
        return stmt.executeQuery("select * from testdata");
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

    static void updateTable() throws SQLException {
        stmt.executeUpdate(String.valueOf(updateRequest));
    }

    static void prepareInsertRequest() {
        updateRequest = new StringBuilder("INSERT INTO `")
                .append(getTableName())
                .append("` (`BankName`, `Value`, `Course`) VALUES");
    }

    private static int cost = 0;
    private static int temp = 0;

    static void prepareInsertQuery(List<WebElement> bankName, List<WebElement> sum, List<WebElement> courseOfMoney) throws SQLException {

        if (courseOfMoney.size() == 2) {
            cost++;
        }
        for (int i = 0; i < bankName.size() - cost; i++) {
            if (cost == 1 & i > 0) {
                temp++;
            }
            updateRequest.append("('")
                    .append(bankName.get(i).getText())
                    .append("', '")
                    .append(sum.get(i).getText())
                    .append("', '")
                    .append(courseOfMoney.get(i - temp).getText())
                    .append("'),");
        }

        updateRequest.setLength(updateRequest.length() - 1);
        updateRequest.append(";");
    }

    public static void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static void closeSTMT() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ignored) {
            }
        }
    }
}
