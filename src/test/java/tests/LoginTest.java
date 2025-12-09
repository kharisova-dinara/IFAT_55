package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withLockedUserPermission;

public class LoginTest extends BaseTest {

    @Test()
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login(withLockedUserPermission());
        assertTrue(loginPage.isErrorMsgAppear(), "Error message does not appear");
        assertEquals(loginPage.errorMessageText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(priority = 2, enabled = true, invocationCount = 1, alwaysRun = true)
    public void checkCorrectLogin() {
        System.out.println("LoginTest correct is running in thread:" + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isPageLoaded("Products"), "Register btn is not visible");
    }
}
