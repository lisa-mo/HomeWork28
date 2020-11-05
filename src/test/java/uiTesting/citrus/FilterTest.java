package uiTesting.citrus;

import citrus.steps.ComparePageStep;
import citrus.steps.HomePageStep;
import citrus.steps.ProductListPageStep;
import citrus.steps.ProductPageStep;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class FilterTest extends BaseTest {

    HomePageStep homePageStep;
    ProductListPageStep productListPageStep;
    ProductPageStep productPageStep;
    ComparePageStep comparePageStep;

    String menuText = "Смартфоны";
    String linkTextSamsung = "Samsung";
    String linkTextHuawei = "Huawei";
    String linkTextZTE = "ZTE";
    String minPriceFilter = "5000";
    String maxPriceFilter = "9000";
    String firstRAM = "16";
    String secondRAM = "32";
    String material = "Пластик";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
        open("");

        homePageStep = new HomePageStep();
        productListPageStep = new ProductListPageStep();
        productPageStep = new ProductPageStep();
        comparePageStep = new ComparePageStep();
    }

    //        1.Use price filter
    @Test
    public void filterProdByPrice() throws Exception {
        homePageStep.clickOnLinkInMenu(menuText, linkTextSamsung);
        productListPageStep.searchByPriceCheckboxes(minPriceFilter, maxPriceFilter);
        int prodListSize = productListPageStep.getProdListSize();
        productListPageStep.checkForBrandInNames(prodListSize, linkTextSamsung);
        productListPageStep.checkForPricesInRange(prodListSize, minPriceFilter, maxPriceFilter);
    }

    //        2.Use memory size filter
    @Test
    public void filterProdByRAM() throws Exception {
        homePageStep.clickOnLinkInMenu(menuText, linkTextHuawei);
        productListPageStep.filterByramCheckboxes(firstRAM, secondRAM);
        int prodListSize = productListPageStep.getProdListSize();
        productListPageStep.waitForAllProdToAppear(prodListSize);
        productListPageStep.checkForBrandInNames(prodListSize, linkTextHuawei);
        productListPageStep.checkForRamInNames(prodListSize, firstRAM, secondRAM);
    }

    //    //        3.Use body material filter
    @Test
    public void filterProdByMaterial() {
        homePageStep.clickOnLinkInMenu(menuText, linkTextZTE);
        productListPageStep.filterByMaterialCheckbox();
        int prodListSize = productListPageStep.getProdListSize();
        productListPageStep.waitForAllProdToAppear(prodListSize);
        productListPageStep.checkForBrandInNames(prodListSize, linkTextZTE);
        productListPageStep.checkProdMaterial(prodListSize, material);
    }
}
