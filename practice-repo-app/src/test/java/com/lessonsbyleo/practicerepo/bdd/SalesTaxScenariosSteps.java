package com.lessonsbyleo.practicerepo.bdd;

import com.lessonsbyleo.practicerepo.data.Order;
import com.lessonsbyleo.practicerepo.data.ShoppingCartScenarioData;
import com.lessonsbyleo.practicerepo.service.ShoppingCartTestHarness;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.lessonsbyleo.practicerepo.exception.ZipCodeUnavailableException.getZipCodeMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTaxScenariosSteps {

    @Autowired
    private ShoppingCartTestHarness shoppingCartTestHarness;

    @Autowired
    private Map<String, ShoppingCartScenarioData> shoppingCartScenarioDataMap;

    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("the customers sales tax has already been saved internally")
    public void theCustomersSalesTaxHasAlreadyBeenSavedInternally() {
        String zipCode = shoppingCartTestHarness.getInternalSalesTaxZipCode();
        givenCustomerOrderHasThe(zipCode);
    }

    @Given("the customers sales tax has not been saved internally")
    public void theCustomersSalesTaxHasNotBeenSavedInternally() {
        String zipCode = shoppingCartTestHarness.getExternalSalesTaxZipCode();
        givenCustomerOrderHasThe(zipCode);
    }

    @Given("the shopping cart is unable to get the customers sales tax")
    public void theShoppingCartIsUnableToGetTheCustomersSalesTax() {
        String zipCode = shoppingCartTestHarness.getUnavailableSalesTaxZipCode();
        givenCustomerOrderHasThe(zipCode);
    }
    private void givenCustomerOrderHasThe(String zipCode){
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());
        Order customerOrder = shoppingCartScenarioData.getOrder();
        customerOrder.getCustomer().getAddress().setZipCode(zipCode);
        shoppingCartScenarioData.setExpectedShoppingCartExceptionMessage(getZipCodeMessage(zipCode));
    }

    @Then("the shopping cart gets the customers sales tax internally")
    public void theShoppingCartGetsTheCustomersSalesTaxInternally() {
        shoppingCartTestHarness.verifyInternalSalesTaxUsed();
        verifySalesTax(shoppingCartTestHarness.getInternalSalesTaxZipCode());
    }

    @Then("the shopping cart gets the customers sales tax externally")
    public void theShoppingCartGetsTheCustomersSalesTaxExternally() {
        shoppingCartTestHarness.verifyExternalSalesTaxUsed();
        verifySalesTax(shoppingCartTestHarness.getExternalSalesTaxZipCode());
    }

    private void verifySalesTax(String zipcode){
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());
        double expectedSalesTax = shoppingCartTestHarness.getExpectedSalesTax(zipcode);
        assertEquals(expectedSalesTax, shoppingCartScenarioData.getTotalBill().getSalesTax());
    }
}
