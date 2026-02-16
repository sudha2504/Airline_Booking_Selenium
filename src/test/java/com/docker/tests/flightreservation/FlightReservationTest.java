package com.docker.tests.flightreservation;

import com.docker.pages.flightReservation.*;
import com.docker.tests.BaseTest;
import com.docker.tests.vendorPortal.models.passengerData;
import com.docker.util.Config;
import com.docker.util.Constants;
import com.docker.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlightReservationTest extends BaseTest {

    private String noOfPassengers;
    private String FarePrice;
    private passengerData passenger;

    @BeforeTest
    @Parameters("reservationDataPath")
    public void setParameters(String path) throws IOException {
        this.passenger = JsonUtil.getTestData(path, passengerData.class);
        this.noOfPassengers = passenger.passengerCount();
        this.FarePrice = passenger.expectedFare();
    }

    @Test
    public void userRegistrationTest(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(passenger.firstName(), passenger.lastName());
        registrationPage.enterUserCredentials(passenger.email(),passenger.password());
        registrationPage.enterAddress(passenger.street(),passenger.city(),passenger.zip());
        registrationPage.register();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationPage(){
        RegistrationConfirmationPage registrationconfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationconfirmationPage.isAt());
        registrationconfirmationPage.setGotoFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightSearchTest(){
        FlightSearchPage flightsearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightsearchPage.isAt());
        flightsearchPage.selectPassengers(noOfPassengers);
        flightsearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){

        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.chooseAirlineFlight();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void FlightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        String Passengers = flightConfirmationPage.getConfirmationNumber();
        String price = flightConfirmationPage.getPrice();
//        Assert.assertEquals(Passengers, noOfPassengers);
        Assert.assertEquals(price, FarePrice);
    }

 }



