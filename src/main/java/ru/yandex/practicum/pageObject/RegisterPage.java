package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends MainPage {

    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By enterButton = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Регистрация пользователя")
    public void regiserUser(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("ожидание загрузки страницы авторизации")
    public RegisterPage waiting() {
        new WebDriverWait(driver, Duration.ofSeconds(9))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Вход']")));
        return new RegisterPage(driver);
    }

    @Step("Получение ошибки при коротком пароле")
    public String getErrorPassword(String name, String email, String password) {
        regiserUser(name, email, password);
        return driver.findElement(errorPassword).getText();
    }

    @Step("Клик по кнопке Войти")
    public void clickEnter() {
        driver.findElement(enterButton).click();
    }
}
