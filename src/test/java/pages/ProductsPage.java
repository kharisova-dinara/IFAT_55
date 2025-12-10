package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Данная страница описывает функционал витрины страницы
 */

public class ProductsPage extends BasePage {
    By pageTitle = By.xpath("//*[@data-test='title']");

    private static final String ADD_TO_CARD_BUTTON_PATTERN = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private static final String DATA_TEST_PATTERN = "[data-test='%s']";

    By cartBadge = By.xpath("//*[@data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавляем товар в корзину по названию")
    public ProductsPage addToCart(final String goodsName) {
        //By addGoodsToCart = By.xpath(String.format(ADD_TO_CARD_BUTTON_PATTERN, "Sauce Labs Bike Light"));
        By addGoodsToCart = By.xpath(ADD_TO_CARD_BUTTON_PATTERN.formatted(goodsName));
        driver.findElement(addGoodsToCart).click();
        return this;
    }

    @Step("Добавляем товар в корзину")
    public ProductsPage addToCart(final int goodsOrder) {
        driver.findElements(By.xpath(TEXT_LOCATOR_PATTERN.formatted("Add to cart"))).get(goodsOrder).click();
        return this;
    }

    @Step("Проверяем количество товаров в корзине")
    public String checkGoodsQuantity() {
        return driver.findElement(cartBadge).getText();
    }

    @Step("Переходим в корзину")
    public ProductsPage switchToCart() {
        driver.findElement(cartBadge).click();
        return this;
    }
}

//public String checkGoodsQuantittttty() {return driver.findElement(cartBadge).getDomAttribute("data-test");}
