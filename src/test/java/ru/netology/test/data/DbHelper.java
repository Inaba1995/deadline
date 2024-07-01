package ru.netology.test.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private final static QueryRunner runner = new QueryRunner();

    public DbHelper() {
    }

    public static String getVerificationCode() {
        val codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes);";
        String verifiCode = "";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val code = runner.query(conn, codeSQL, new ScalarHandler<>());
            verifiCode = (String) code;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verifiCode;
    }

    public static void clearTables() {
        val codeSQLClearAuth = "TRUNCATE auth_codes;";
        val codeSQLClearCards = "TRUNCATE cards;";
        val codeSQLClearTransactions = "TRUNCATE card_transactions;";
        val codeSQLClearUsers = "DELETE FROM users;";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.execute(conn, codeSQLClearAuth);
            runner.execute(conn, codeSQLClearCards);
            runner.execute(conn, codeSQLClearTransactions);
            runner.execute(conn, codeSQLClearUsers);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}