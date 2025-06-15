package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    protected final WebDriver driver;
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By loginButtonLK = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorLink = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("клик на кнопку логина")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("клик на кнопку Личный кабинет")
    public void clickLoginLK() {
        driver.findElement(loginButtonLK).click();
    }

    @Step("Загрузка главной страницы")
    public boolean isMainPageSuccessful() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Step("клик на вкладку Конструктор")
    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("клик на вкладку логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }


}
