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
    @Description("Переходы по разделам конструктора: соусы, булки, начинки")
    public void testConstructorTabs() {
        ConstructorForm constructor = new ConstructorForm(driver);

        constructor.clickSauces();
        assertTrue("Соусы", constructor.wait("Соусы"));

        constructor.clickBuns();
        assertTrue("Булки", constructor.wait("Булки"));

        constructor.clickFillings();
        assertTrue("Начинки", constructor.wait("Начинки"));
    }

}
