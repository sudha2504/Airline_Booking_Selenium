package com.docker.tests.vendorPortal;

import com.docker.pages.vendorPortal.DashboardPage;
import com.docker.pages.vendorPortal.LoginPage;
import com.docker.tests.BaseTest;
import com.docker.tests.vendorPortal.models.memberData;
import com.docker.util.Config;
import com.docker.util.Constants;
import com.docker.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class VendorPortalTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboard;
    private memberData userData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String path) throws IOException {
//        Driver Setup
        this.loginPage = new LoginPage(driver);
        this.dashboard = new DashboardPage(driver);
        this.userData= JsonUtil.getTestData(path, memberData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.loginToVendorPortal(userData.username(), userData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardVerify(){
        Assert.assertTrue(dashboard.isAt());
        Assert.assertEquals(dashboard.getMonthlyEarning(), userData.monthlyEarning());
        Assert.assertEquals(dashboard.getAnnualEarning(), userData.annualEarning());
        Assert.assertEquals(dashboard.getAvlbInventory(), userData.avlbInventory());

        dashboard.enterSearchString(userData.searchKeyword());
        System.out.println(userData.searchKeyword());
        System.out.println(dashboard.getSearchResult());
        Assert.assertEquals(dashboard.getSearchResult(), userData.searchResultCount());

    }

    @Test(dependsOnMethods = "dashboardVerify")
    public void logout(){
        dashboard.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
