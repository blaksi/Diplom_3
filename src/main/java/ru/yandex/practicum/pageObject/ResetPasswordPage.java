package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage extends LoginPage {
    private final By enterButton = By.xpath(".//a[text()='Войти']");

    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterfromResetPage() {
        driver.findElement(enterButton).click();
    }
}
