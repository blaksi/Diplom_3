package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends LoginPage {

    private final By logoutBatton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("клик по кнопке Выход")
    public void clickLogout() {
        driver.findElement(logoutBatton).click();
    }

    @Step("Загрузка страницы Личный кабинет")
    public boolean isPageLKSuccessful() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
    }
}
