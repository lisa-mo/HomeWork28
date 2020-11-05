package citrus.steps;

import citrus.pages.ComparePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

public class ComparePageStep {

    ComparePage comparePage = new ComparePage();

    @Step("Add products to basket from compare")
    public void addProdsToBasketFromCompare() {
        comparePage.getBasketFragment().addProdToBasketByOrder(0);
        comparePage.getBasketFragment().closeBasket();
        comparePage.getBasketFragment().addProdToBasketByOrder(2);
    }

    @Step("Check amount of prod to compare")
    public void checkAmountOfProdToCompare(int size) {
        comparePage.waitForPopUpAndCloseIt();
        comparePage.checkProdAmountOnComparePage().shouldHaveSize(size);
    }

@Step("Get products name size")
    public int getProdNameSize() {
        return comparePage.waitForPageToLoad()
                .getProdNamesFromComparePage().size();
}

    @Step("Check two products names")
    public void checkTwoProdNames(int listSize, String sampleTextOne, String sampleTextTwo) throws Exception {
        ElementsCollection getList = comparePage.getProdNamesFromComparePage();
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    throw new Exception("There are no products with " + sampleTextOne + " or " + sampleTextTwo + " on the page.");
                }
            }
        }
    }

    @Step("Check two products prices")
    public void checkTwoProdPrices(int listSize, String sampleTextOne, String sampleTextTwo) throws Exception {
        ElementsCollection getList = comparePage.getProdNamesFromComparePage();
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    throw new Exception("There are no products with " + sampleTextOne + " or " + sampleTextTwo + " on the page.");
                }
            }
        }
    }

    @Step("Add one more product to compare")
    public void addOneMoreProdToCompare() {
        comparePage.ckickAddMoreToCompare();
        comparePage.getMoreProd().shouldBe(Condition.visible);
        comparePage.clickToAddAnotherProdToCompare(0);
    }

    @Step("Get name of another prod to compare")
    public String getNameOfOtherProdToCompare() {
        return comparePage.getNamesOfAnotherProdToCompare().get(0).getText();
    }

    @Step("Get price of another prod to compare")
    public String getPriceOfOtherProdToCompare() {
        return comparePage.getAnotherProdPricesToCompare().get(0).getText();
    }

    @Step("Add third prod to compare")
    public void addThirdProdToCompare() {
        comparePage.clickAddButton()
                .waitForPageToLoad()
                .closePopUp();
    }

    @Step("Check three products names")
    public void checkThreeProdNames(int listSize, String sampleTextOne, String sampleTextTwo, String sampleTextTree) throws Exception {
        ElementsCollection getList = comparePage.getProdNamesFromComparePage();
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    if (!getList.get(counter).getText().contains(sampleTextTree)) {
                        throw new Exception("There are no products with " + sampleTextOne + ", " + sampleTextTwo + " or " + sampleTextTree + " on the page.");
                    }
                }
            }
        }
    }

    @Step("Check three products prices")
    public void checkThreeProdPrices(int listSize, String sampleTextOne, String sampleTextTwo, String sampleTextTree) throws Exception {
        ElementsCollection getList = comparePage.getProdPricesFromComparePage();
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    if (!getList.get(counter).getText().contains(sampleTextTree)) {
                        throw new Exception("There are no products with " + sampleTextOne + ", " + sampleTextTwo + " or " + sampleTextTree + " on the page.");
                    }
                }
            }
        }
    }


}
