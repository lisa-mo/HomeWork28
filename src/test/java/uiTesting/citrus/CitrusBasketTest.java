package uiTesting.citrus;

import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import citrus.steps.ComparePageStep;
import citrus.steps.HomePageStep;
import citrus.steps.ProductListPageStep;
import citrus.steps.ProductPageStep;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketTest {
        ProductPage productPage;
    ProductListPage productListPage;

    HomePageStep homePageStep;
    ProductListPageStep productListPageStep;
    ProductPageStep productPageStep;
    ComparePageStep comparePageStep;

    String productName = "Apple iPhone 11 128Gb Black";
    String menuText = "Смартфоны";
    String linkText = "Apple";
    String brandForSearch = "Apple iPhone";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
        open("");

        homePageStep = new HomePageStep();
        productListPageStep = new ProductListPageStep();
        productPageStep = new ProductPageStep();
        comparePageStep = new ComparePageStep();
        productPage = new ProductPage();
        productListPage = new ProductListPage();
    }


    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }


    @Test
    public void addProdViaMenuToBasket() {
        homePageStep.clickOnLinkInMenu(menuText, linkText);
        productListPageStep.clickOnProduct(productName);
        String productPrice = productPageStep.addProductToBasket();
        productPageStep.verifyBasketContent(productName, productPrice);
       productPage.getBasketFragment().deleteProdFromBasket()
               .closeBasket();
    }

    @Test
    public void addProdViaSearchToBasket() {
            refresh();
        homePageStep.searchForProductByName(productName);
        String productPrice = productListPageStep.getProDuctPriceByItsName(productName);
        productListPageStep.addProdToBasketByName(productName);
        productPageStep.verifyBasketContent(productName, productPrice);
       productPage.getBasketFragment().deleteProdFromBasket()
               .closeBasket();
    }

    @Test
    public void addTwoProdToBasketViaSearch() {
            refresh();
        homePageStep.searchProductByBrand(brandForSearch);
        String strFirstProductPrice = productListPageStep.getProdPriceByOrder(0);
        String strSecondProductPrice = productListPageStep.getProdPriceByOrder(1);
        String firstProductName = productListPageStep.getProdNameByOrder(0);
        String secondProductName = productListPageStep.getProdNameByOrder(1);
        productListPageStep.addProductsToBasket();
        productListPageStep.checkProductsInBasket(firstProductName, secondProductName, strFirstProductPrice, strSecondProductPrice);
       productListPage.getBasketFragment().deleteProdFromBasket()
               .closeBasket();
    }

    @Test
    public void addTwoProdToBasketViaCompare() {
            refresh();
        homePageStep.searchProductByBrand(brandForSearch);
        String strFirstProductPrice = productListPageStep.getProdPriceByOrder(0);
        String strSecondProductPrice = productListPageStep.getProdPriceByOrder(1);
        String firstProductName = productListPageStep.getProdNameByOrder(0);
        String secondProductName = productListPageStep.getProdNameByOrder(1);
        productListPageStep.addProdsToCompare();
        comparePageStep.addProdsToBasketFromCompare();
        productListPageStep.checkProductsInBasket(firstProductName, secondProductName, strFirstProductPrice, strSecondProductPrice);
       productListPage.getBasketFragment().deleteProdFromBasket()
               .closeBasket();
    }
}
