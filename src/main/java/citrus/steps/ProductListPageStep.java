package citrus.steps;

import citrus.pages.ProductListPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

public class ProductListPageStep {
    ProductListPage productListPage = new ProductListPage();

    @Step("Click on Product")
    public void clickOnProduct(String productName) {
        productListPage.waitForPageToLoad()
                .closePopUp()
                .clickOnProductByName(productName);
    }

    @Step("Get product price by its name")
    public String getProDuctPriceByItsName(String productName) {
        productListPage.waitForPageToLoad()
                .closePopUp();
        return productListPage.getProductPriceByName(productName);
    }

    @Step("Add prod to basket by name")
    public void addProdToBasketByName(String productName) {
        productListPage.addProductToBasket(productName);
    }

    @Step("Get product price by its order")
    public String getProdPriceByOrder(int prodOrder) {
        productListPage.waitForPageToLoad()
                .closePopUp();
        return productListPage.getProductPricesFromProdPage().get(prodOrder).getText();
    }

    @Step("Get product name by its order")
    public String getProdNameByOrder(int prodOrder) {
        return productListPage.getProductNamesFromProdPage().get(prodOrder).getText();
    }

    @Step("Add products to basket")
    public void addProductsToBasket() {
        productListPage.getBasketFragment().addProdToBasketByOrder(0);
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().closeBasket();
        productListPage.getBasketFragment().addProdToBasketByOrder(1);
    }

    @Step("Check products in basket")
    public void checkProductsInBasket(String firstProductName, String secondProductName, String strFirstProductPrice, String strSecondProductPrice) {
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getBasketFragment().getBasketPrice().get(1).shouldHave(Condition.text(strFirstProductPrice));
        productListPage.getBasketFragment().getBasketPrice().get(2).shouldHave(Condition.text(strSecondProductPrice));
        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productListPage
                .getBasketFragment()
                .getTotalFromPricesInBasket(strFirstProductPrice, strSecondProductPrice)));
    }

    @Step("Add products to compare and go to compare page")
    public void addProdsToCompare() {
        productListPage.addProdToCompareByOrder(0);
        productListPage.addProdToCompareByOrder(1);
        productListPage.clickCompareButton();
    }

    @Step("Search by price checkboxes")
    public void searchByPriceCheckboxes(String minPriceFilter, String maxPriceFilter) {
        productListPage.getFilterFragment().fillInPriceFilters(0, minPriceFilter);
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment().fillInPriceFilters(1, maxPriceFilter);
        productListPage.getFilterFragment().clickAsideFilter();
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment().getPriceTo().shouldBe(Condition.visible);
    }

    @Step("Get product list size")
    public int getProdListSize() {
        return productListPage.waitForPageToLoad()
                .getProdNamesFromFiltersPage().size();
    }

    @Step("Check for brand in names")
    public void checkForBrandInNames(int listSize, String sampleText) {
        ElementsCollection getList = productListPage.getProdNamesFromFiltersPage();
        for (int counter = 0; counter < listSize; counter++) {
            getList.get(counter).shouldHave(Condition.text(sampleText));
            System.out.println(getList.get(counter));
        }
    }

    @Step("Check for prices in right range on page")
    public void checkForPricesInRange(int prodListSize, String minPriceFilter, String maxPriceFilter) throws Exception {
        for (int priceCounter = 0; priceCounter < prodListSize; priceCounter++) {
            String strPrice = productListPage.getProdPricesFilterPage().get(priceCounter).getText().replaceAll("[^\\d.]", "");
            int intPrice = Integer.parseInt(strPrice);
            System.out.println(intPrice);
            if (intPrice < Integer.parseInt(minPriceFilter) || intPrice > Integer.parseInt(maxPriceFilter)) {
                throw new Exception("Wrong price value.");
            }
        }
    }

    @Step("Filter by RAM checkboxes")
    public void filterByramCheckboxes(String firstRAM, String secondRAM) {
        productListPage.waitForPageToLoad()
                .closePopUp()
                .getFilterFragment()
                .clickRamCheckbox(firstRAM);
        productListPage.waitForPageToLoad()
                .closePopUp()
                .getFilterFragment()
                .clickRamCheckbox(secondRAM);
        productListPage.waitForPageToLoad()
                .closePopUp();
    }

    @Step("Wait for all products to appear")
    public void waitForAllProdToAppear(int prodListSize) {
        productListPage.getProdNamesFromFiltersPage().get(prodListSize - 1).shouldBe(Condition.visible);
    }

    @Step("Check for RAM in names")
    public void checkForRamInNames(int listSize, String sampleTextOne, String sampleTextTwo) throws Exception {
        ElementsCollection getList = productListPage.getProdNamesFromFiltersPage();
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    throw new Exception("There are no products with " + sampleTextOne + " or " + sampleTextTwo + " on the page.");
                }
            }
        }
    }

    @Step("Filter by material checkbox")
    public void filterByMaterialCheckbox() {
        productListPage.waitForPageToLoad()
                .closePopUp()
                .getFilterFragment()
                .clickMaterialCheckbox();
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.waitForPopUpAndCloseIt();
    }

    @Step("Check prod material")
    public void checkProdMaterial(int prodListSize, String material) {
        for (int materialCounter = 0; materialCounter < prodListSize; materialCounter++) {
            productListPage.hoverProdView(materialCounter);
            productListPage.getProdMaterials().get(materialCounter).shouldHave(Condition.text(material));
            System.out.println(productListPage.getProdMaterials().get(materialCounter));
        }
    }

    @Step("Get price to compare")
    public String getPriceToCompare(int order) {
        productListPage.waitForPageToLoad()
                .closePopUp();
        return productListPage.getProdPricesFilterPage().get(order).getText();
    }

    @Step("Get first name to compare")
    public String getFirstNameToCompare() {
        return productListPage.getProdNamesFromFiltersPage().get(0).getText().replace(" ...", "");
    }

    @Step("Get second name to compare")
    public String getSecondNameToCompare() {
        return productListPage.getProdNamesFromFiltersPage().get(1).getText();
    }

    @Step("Add products to comparison and go to compare page")
    public void addProdsToComparison() {
        productListPage.addProdToCompareByOrderOnFilter(0);
        productListPage.addProdToCompareByOrderOnFilter(1);
        productListPage.clickCompareButton()
                .waitForPageToLoad();
    }


}


