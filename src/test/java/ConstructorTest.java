import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pageObject.ConstructorForm;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.driver.WebDriverCreator.createWebDriver;

public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Переход на раздел конструктора: соусы")
    public void testConstructorTabSouse() {
        ConstructorForm constructor = new ConstructorForm(driver);
        constructor.clickSauces();
        assertTrue("Ошибка: не выполнен переход на вкладку Соусы", constructor.wait("Соусы"));
    }
    @Test
    @Description("Переход на раздел конструктора: булки")
    public void testConstructorTabBuns() {
        ConstructorForm constructor = new ConstructorForm(driver);
        constructor.clickSauces();
        constructor.wait("Соусы");
        constructor.clickBuns();
        assertTrue("Ошибка: не выполнен переход на вкладку Булки", constructor.wait("Булки"));
    }
    @Test
    @Description("Переход на раздел конструктора: начинки")
    public void testConstructorTabsFillings() {
        ConstructorForm constructor = new ConstructorForm(driver);
        constructor.clickFillings();
        assertTrue("Ошибка: не выполнен переход на вкладку Начинки", constructor.wait("Начинки"));
    }

}
