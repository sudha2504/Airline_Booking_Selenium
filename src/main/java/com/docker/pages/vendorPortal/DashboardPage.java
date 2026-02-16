package com.docker.pages.vendorPortal;

import com.docker.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id="monthly-earning")
    private WebElement monthlyEarning;

    @FindBy(id="annual-earning")
    private WebElement AnnualEarning;

    @FindBy(id="profit-margin")
    private WebElement profitMargin;

    @FindBy(id="available-inventory")
    private WebElement avlbInventory;

    @FindBy(css="#dataTable_wrapper input")
    private WebElement searchInput;

    @FindBy(css="#dataTable_info")
    private WebElement resultsData;

    @FindBy(linkText="Logout")
    private WebElement logout;

    @FindBy(css=".img-profile")
    private WebElement profilePic;

    @FindBy(xpath ="//a[text()='Logout']")
    private WebElement logoutModal;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(searchInput));
        return this.searchInput.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarning.getText();
    }

    public String getAnnualEarning(){
        return this.AnnualEarning.getText();
    }

    public String getProfitMarginEarning(){
        return this.profitMargin.getText();
    }

    public String getAvlbInventory(){
        return this.avlbInventory.getText();
    }

    public void enterSearchString(String searchItem){
        this.searchInput.sendKeys(searchItem);
    }

    public int getSearchResult(){
        String resultOutput = this.resultsData.getText();
        String[] results = resultOutput.split(" ");
        while (results.length < 9){
            resultOutput = this.resultsData.getText();
            log.info(resultOutput);
        }
        int count = Integer.parseInt(results[5]);
        log.info("Results count : {} ", count);
        return count;
    }

    public void logout(){
        this.profilePic.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logout));
        this.logout.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutModal));
        this.logoutModal.click();
    }

}
