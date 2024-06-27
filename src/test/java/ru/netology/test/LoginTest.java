package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.test.data.DataHelper;
import ru.netology.test.data.DbHelper;
import ru.netology.test.page.DashboardPage;
import ru.netology.test.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @Test
    void shouldTransferMoneyCard1ToCard2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(DbHelper.getVerificationCode());
        assertEquals("Личный кабинет", dashboardPage.getHeadLabel());
    }
}
