package citrus.steps;

import citrus.pages.HomePage;
import io.qameta.allure.Step;

public class HomePageStep {
    HomePage homePage = new HomePage();

    @Step("Click on link in menu")
    public void clickOnLinkInMenu(String menuText, String linkText) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuText)
                .clickLinkInMenu(linkText);
    }

    @Step("Search for product by name")
    public void searchForProductByName(String productName) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(productName);
    }

    @Step("Search product by brand")
    public void searchProductByBrand(String brandForSearch) {
        homePage.waitForPageToLoad()
                .closePopUp();
        homePage.getSearchFragment()
                .clearSearch()
                .searchProduct(brandForSearch);
    }
}
