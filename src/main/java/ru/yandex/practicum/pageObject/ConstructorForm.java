package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConstructorForm extends MainPage {

    private final By title = By.xpath("//*[text()='Соберите бургер']");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By currentTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]");

    public ConstructorForm(WebDriver driver) {
        super(driver);
    }

    @Step("Форма Соберите бургер")
    public String constructionTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Открываем вкладку 'Булки'")
    public void clickBuns() {
        driver.findElement(bunsTab).click();
    }

    @Step("Открываем вкладку 'Соусы'")
    public void clickSauces() {
        driver.findElement(saucesTab).click();

    }
    @Step("wait")
    public boolean wait(String name){
       return new WebDriverWait(driver, Duration.ofSeconds(9))
                .until(ExpectedConditions.textToBe(currentTab,name));
    }

    @Step("Открываем вкладку 'Начинки'")
    public void clickFillings() {
        driver.findElement(fillingsTab).click();
    }

}
