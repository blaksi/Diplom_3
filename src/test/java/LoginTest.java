import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pageObject.LoginPage;
import ru.yandex.practicum.pageObject.RegisterPage;
import ru.yandex.practicum.pageObject.ResetPasswordPage;
import ru.yandex.practicum.utils.UserApi;

import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.driver.WebDriverCreator.createWebDriver;

public class LoginTest {
    private final String email = "new" + System.currentTimeMillis() + "@sdfgh.ru";
    private final String password = "test123";
    private final String name = "Test";
    private WebDriver driver;
    private String accessToken;


    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site");

        UserApi user = new UserApi(name, password, email);
        accessToken = user.registerNewUser();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @Description("Проверка входа через главную страницу")
    public void userCanLoginFromMainPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        loginPage.login(email, password);
        assertTrue("Ошибка: Ожидался переход после логина", loginPage.isMainPageSuccessful());
    }

    @Test
    @Description("Проверка входа через Личный кабинет")
    public void userCanLoginFromLK() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginLK();
        loginPage.login(email, password);
        assertTrue("Ошибка: Ожидался переход после логина", loginPage.isMainPageSuccessful());
    }

    @Test
    @Description("Проверка входа через кнопку в форме регистрации")
    public void userCanLoginFromRegistrationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickEnter();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        assertTrue("Ошибка: Ожидался переход после логина", loginPage.isMainPageSuccessful());
    }

    @Test
    @Description("Проверка входа через кнопку в форме Восстановление пароля")
    public void userCanLoginFromResetPasswordForm() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.clickEnterfromResetPage();
        resetPasswordPage.login(email, password);
        assertTrue("Ошибка: Ожидался переход после логина", resetPasswordPage.isMainPageSuccessful());
    }
}
