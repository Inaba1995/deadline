package ru.netology.test.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class DashboardPage {
    private final SelenideElement headLabel;

    public DashboardPage() {
       headLabel =  $("[data-test-id=dashboard]").shouldBe(Condition.visible);
    }

    public String getHeadLabel() {
        return headLabel.getText().trim();
    }

}
