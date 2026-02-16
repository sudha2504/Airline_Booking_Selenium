package com.docker.pages.flightReservation;

import com.docker.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css ="#flights-confirmation-section .card-body div:nth-child(3) > div:nth-child(2) p")
    private WebElement totalPrice;

    @FindBy(css ="#flights-confirmation-section .card-body div:nth-child(1) > div:nth-child(2) p")
    private WebElement confirmationNumber;

    public FlightConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.totalPrice));
        return this.totalPrice.isDisplayed();
    }

    public String getPrice(){
        log.info("Flight Total Price: {} ", totalPrice.getText());
        return totalPrice.getText();

    }

    public String getConfirmationNumber(){
        log.info("ConfirmationNumber: {} ", confirmationNumber.getText());
        return confirmationNumber.getText();
    }
}
