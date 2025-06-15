import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pageObject.ConstructorForm;
import ru.yandex.practicum.pageObject.LoginPage;
import ru.yandex.practicum.pageObject.ProfilePage;
import ru.yandex.practicum.utils.UserApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.driver.WebDriverCreator.createWebDriver;

public class NavigationTest {
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
        assertTrue("Главная страница не отобразилась", loginPage.isMainPageSuccessful());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @Description("Переход в Личный кабинет")
    public void goToLKFromMainPage() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLoginLK();
        assertTrue("Ошибка: переход в Личный кабинет не осуществлен", profilePage.isPageLKSuccessful());
    }

    @Test
    @Description("Переход из Личного кабинета в конструктор")
    public void goToConstructorFromLK() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLoginLK();
        profilePage.clickConstructor();
        ConstructorForm constructorForm = new ConstructorForm(driver);
        assertEquals("Соберите бургер", constructorForm.constructionTitle());
    }

    @Test
    @Description("Переход в «Конструктор» и на логотип Stellar Burgers.")
    public void goToConstructorAndLogo() {
        //тест на переход на конструктор
        driver.get("https://stellarburgers.nomoreparties.site/feed");
        ConstructorForm constructorForm = new ConstructorForm(driver);
        constructorForm.clickConstructor();
        assertEquals("Соберите бургер", constructorForm.constructionTitle());
        //тест на переход логотипа
        driver.get("https://stellarburgers.nomoreparties.site/feed");
        constructorForm.clickLogo();
        assertTrue("Ошибка: Переход на логотип не осуществлен", constructorForm.isMainPageSuccessful());
    }
}
