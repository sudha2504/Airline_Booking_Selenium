package com.docker.tests.vendorPortal.models;

public record passengerData(String firstName,
                            String lastName,
                            String email,
                            String password,
                            String street,
                            String city,
                            String zip,
                            String passengerCount,
                            String expectedFare) {
}
