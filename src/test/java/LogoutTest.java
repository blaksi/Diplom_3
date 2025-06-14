import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pageObject.LoginPage;
import ru.yandex.practicum.pageObject.ProfilePage;
import ru.yandex.practicum.utils.UserApi;

import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.driver.WebDriverCreator.createWebDriver;

public class LogoutTest {
    private final String email = "new" + System.currentTimeMillis() + "@sdfgh.ru";
    private final String password = "test123";
    private final String name = "Test";
    private WebDriver driver;
    private String accessToken;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/login");

        UserApi user = new UserApi(name, password, email);
        accessToken = user.registerNewUser();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        loginPage.isMainPageSuccessful();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @Description("Выход из Личного кабинета")
    public void checkLogout() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLoginLK();
        profilePage.isPageLKSuccessful();
        profilePage.clickLogout();
        assertTrue("Ошибка: выход из аккаунта не осуществлен", profilePage.isPageLoginSuccessful());
    }


}
