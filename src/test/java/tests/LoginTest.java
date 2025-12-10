package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class LoginTest extends BaseTest {

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                /*      {PropertyReader.getProperty("sausedemo.locked_user"),
                        PropertyReader.getProperty("sausedemo.password"),
                        "Epic sadface: Sorry, this user has been locked out."}, */

                {UserFactory.withLockedUserPermission(),
                        "Epic sadface: Sorry, this user has been locked out."},

                {UserFactory.withUsernameOnly(""),
                        "Epic sadface: Username is required"},

                {UserFactory.withPasswordOnly(""),
                        "Epic sadface: Password is required"},

                {new User("Locked_out_user", "secret_sauce"),
                        "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Epic("Авторизация")
    @Feature("Логин")
    @Story("Некорректный логин")
    @TmsLink("IFAT_55")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Dinara Kharisova nurutdinova.dinara@mail.ru")
    @Issue("IFAT_5")
    @Flaky
    @Test(description = "Проверка некорректного логина", priority = 1, dataProvider = "loginData")
    public void checkIncorrectLogin(User user, String errorMsg) {
        loginPage.open()
                .login(user);
        //AllureUtils.takeScreenshot(driver);
        assertTrue(loginPage.isErrorMsgAppear(), "Error message does not appear");
        assertEquals(loginPage.errorMessageText(), errorMsg);
    }

    @Epic("Авторизация")
    @Feature("Логин")
    @Story("Корректный логин")
    @TmsLink("IFAT_55")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Dinara Kharisova nurutdinova.dinara@mail.ru")
    @Issue("IFAT_5")
    @Flaky
    @Test(priority = 2, enabled = true, invocationCount = 1, alwaysRun = true)
    public void checkCorrectLogin() {
        System.out.println("LoginTest correct is running in thread:" + Thread.currentThread().getId());

        loginPage.open()
                .login(withAdminPermission());

        assertTrue(productsPage.isPageLoaded(PRODUCTS.getDisplayName()), "Register btn is not visible");
    }
}
