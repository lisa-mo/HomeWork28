package uiTesting.citrus;

import citrus.steps.ComparePageStep;
import citrus.steps.HomePageStep;
import citrus.steps.ProductListPageStep;
import citrus.steps.ProductPageStep;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

//Comparison
//        1.Compare 2+1 products
//        1) Click Notebooks/Acer in menu
//        2) Add first and second laptop to comparison (no navigation to product page). Remember names, prices
//        3) Click on comparison icon in header
//        4) Verify
//        - only 2 products in comparison
//        - prices and names of products are correct
//        5) Click 'add new product to comparison'
//        6) Choose first (remember name, price)
//        7) Verify
//        - only 3 products in comparison
//        - prices and names of products are correct

public class CompareTest extends uiTesting.citrus.BaseTest {

    HomePageStep homePageStep;
    ProductListPageStep productListPageStep;
    ProductPageStep productPageStep;
    ComparePageStep comparePageStep;

    String menuText = "Ноутбуки, планшеты, МФУ";
    String linkTextAcer = "Acer";

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
    }

    @Test
    public void compareProducts() throws Exception {
        homePageStep.clickOnLinkInMenu(menuText, linkTextAcer);
        String strFirstProductPrice = productListPageStep.getPriceToCompare(0);
        String strSecondProductPrice = productListPageStep.getPriceToCompare(1);
        String firstProductName = productListPageStep.getFirstNameToCompare();
        String secondProductName = productListPageStep.getSecondNameToCompare();
        productListPageStep.addProdsToComparison();
        comparePageStep.checkAmountOfProdToCompare(2);
        int prodListSizeOne = comparePageStep.getProdNameSize();
        comparePageStep.checkTwoProdNames(prodListSizeOne, firstProductName, secondProductName);
        comparePageStep.checkTwoProdPrices(prodListSizeOne, strFirstProductPrice, strSecondProductPrice);
        comparePageStep.addOneMoreProdToCompare();
        String newNameToCompare = comparePageStep.getNameOfOtherProdToCompare();
        String newPriceToCompare = comparePageStep.getPriceOfOtherProdToCompare();
        comparePageStep.addThirdProdToCompare();
        comparePageStep.checkAmountOfProdToCompare(3);
        int prodListSizeTwo = comparePageStep.getProdNameSize();
        comparePageStep.checkThreeProdNames(prodListSizeTwo, firstProductName, secondProductName, newNameToCompare);
        comparePageStep.checkThreeProdPrices(prodListSizeTwo, strFirstProductPrice, strSecondProductPrice, newPriceToCompare);
    }
}
