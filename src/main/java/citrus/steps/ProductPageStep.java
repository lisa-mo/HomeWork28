package citrus.steps;

import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class ProductPageStep {

    ProductPage productPage = new ProductPage();

    @Step("Add product to basket")
    public String addProductToBasket() {
        productPage.clickBuyButton();
        return productPage.getProductPrice();
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName, String productPrice) {
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketPrice().get(1).shouldHave(Condition.text(productPrice));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

}
