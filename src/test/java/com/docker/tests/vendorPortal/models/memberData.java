package com.docker.tests.vendorPortal.models;

public record memberData(String username,
                         String password,
                         String monthlyEarning,
                         String annualEarning,
                         String avlbInventory,
                         String searchKeyword,
                         int searchResultCount) {
}
