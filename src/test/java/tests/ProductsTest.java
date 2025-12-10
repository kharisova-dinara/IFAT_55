package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import user.UserFactory;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Epic("Продуктовая страница")
    @Feature("Добавление товаров")
    @Story("Проверка товаров в корзине")
    @TmsLink("IFAT_55")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Dinara Kharisova nurutdinova.dinara@mail.ru")
    @Issue("IFAT_5")
    @Flaky
    @Test
    public void checkGoodsAdded() {
        System.out.println("ProdTest inc is running in thread:" + Thread.currentThread().getId());

        loginPage.open()
                .login(UserFactory.withAdminPermission());
        productsPage.isPageLoaded("Products");
        //productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        //productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addToCart(1);
        assertEquals(productsPage.checkGoodsQuantity(), "1");
    }
}
