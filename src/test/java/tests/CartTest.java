package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {

    @Epic("Корзина")
    @Feature("Добавление товаров")
    @Story("Проверка товаров в корзине")
    @TmsLink("IFAT_55")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Dinara Kharisova nurutdinova.dinara@mail.ru")
    @Issue("IFAT_5")
    @Flaky
    @Test
    public void checkGoodsInCart() {
        System.out.println("CartTest inc is running in thread:" + Thread.currentThread().getId());

        loginPage.open()
                .login(withAdminPermission());
        productsPage.isPageLoaded("Products");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)")
                .addToCart("Sauce Labs Bolt T-Shirt")
                //loginPage.open("cart.html");
                .switchToCart();
        cartPage.isPageLoaded("Your Cart");
        //System.out.println(cartPage.getProductsNames() + "!");
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));
    }
}
