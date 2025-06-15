import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pageObject.RegisterPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.driver.WebDriverCreator.createWebDriver;

public class RegistrationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Проверка регистрации")
    public void checkRegistrationUser() {
        RegisterPage registerPage = new RegisterPage(driver);
        String email = System.currentTimeMillis() + "@test.ru";
        registerPage.regiserUser("dfg", email, "123dfghjfg");
        registerPage.waiting();
        assertTrue("Ошибка: Переход на страницу логина не произошёл", driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @Description("Проверка ошибки при пароле менее 6 символов")
    public void checkErrorPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        String email = System.currentTimeMillis() + "@test.ru";
        String actual = registerPage.getErrorPassword("dfg", email, "12345");
        assertEquals("Некорректный пароль", actual);
    }

}
