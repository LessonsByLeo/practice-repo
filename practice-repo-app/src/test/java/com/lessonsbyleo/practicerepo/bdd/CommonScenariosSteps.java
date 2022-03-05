package com.lessonsbyleo.practicerepo.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.Order;
import com.lessonsbyleo.practicerepo.data.ShoppingCartScenarioData;
import com.lessonsbyleo.practicerepo.data.TotalBill;
import com.lessonsbyleo.practicerepo.exception.InvalidOrderException;
import com.lessonsbyleo.practicerepo.exception.ShoppingCartException;
import com.lessonsbyleo.practicerepo.exception.ZipCodeUnavailableException;
import com.lessonsbyleo.practicerepo.service.ShoppingCartService;
import com.lessonsbyleo.practicerepo.service.ShoppingCartTestHarness;
import com.lessonsbyleo.practicerepo.util.OrderCreator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static com.lessonsbyleo.practicerepo.util.ShoppingCartConstants.REAL_IMPL;
import static org.junit.jupiter.api.Assertions.*;

@CucumberContextConfiguration
@ContextConfiguration(
        classes = { PracticeRepoBDDConfig.class },
        initializers = { ConfigDataApplicationContextInitializer.class })
@ActiveProfiles({"stubbed"})

//@DataJpaTest
public class CommonScenariosSteps {

    @Autowired
    private OrderCreator orderCreator;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartTestHarness shoppingCartTestHarness;

    @Autowired
    private ObjectWriter objectWriter;

    @Autowired
    private Map<String, ShoppingCartScenarioData> shoppingCartScenarioDataMap;

    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void done(){
        shoppingCartTestHarness.resetMocks();
    }

    @Given("the customer has an order in their shopping cart")
    public void theCustomerHasOrdersInTheirShoppingCart() throws JsonProcessingException {
        ShoppingCartScenarioData shoppingCartScenarioData = new ShoppingCartScenarioData();

        Order order = orderCreator.createTestOrder(1, "11111-111");
        shoppingCartScenarioData.setOrder(order);
        this.scenario.log("Test Order: \n" + objectWriter.writeValueAsString(order));
        shoppingCartScenarioDataMap.put(scenario.getId(), shoppingCartScenarioData);
    }

    @When("the customer requests the total bill of the shopping cart order")
    public void theCustomerRequestsTheTotalBillOfTheShoppingCartOrder(){
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());

        Order order = shoppingCartScenarioData.getOrder();
        try {
            TotalBill totalBill = shoppingCartService.calculateTotalBill(order, REAL_IMPL);
            shoppingCartScenarioData.setTotalBill(totalBill);
        } catch (ShoppingCartException e) {
            shoppingCartScenarioData.setShoppingCartException(e);
        }
    }

    @Then("the shopping cart calculates customers the total bill")
    public void theShoppingCartCalculatesCustomersTheTotalBill() throws JsonProcessingException {
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());
        TotalBill totalBill = shoppingCartScenarioData.getTotalBill();
        Order order = shoppingCartScenarioData.getOrder();

        scenario.log("customerOrder: \n" + objectWriter.writeValueAsString(order));
        scenario.log("TotalBill: \n" + objectWriter.writeValueAsString(totalBill));

        double expectedTotal = shoppingCartTestHarness.getExpectedTotal(order);
        assertNotNull(totalBill);
        assertEquals(expectedTotal, totalBill.getTotal());
    }

    @Then("the shopping cart cannot calculate the customers total bill")
    public void theShoppingCartCannotCalculateCustomersTheTotalBill() {
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());
        TotalBill totalBill = shoppingCartScenarioData.getTotalBill();
        ShoppingCartException shoppingCartException = shoppingCartScenarioData.getShoppingCartException();
        String expectedShoppingCartExceptionMessage = shoppingCartScenarioData.getExpectedShoppingCartExceptionMessage();

        assertNull(totalBill);
        assertNotNull(shoppingCartException);
        assertEquals(expectedShoppingCartExceptionMessage, shoppingCartException.getMessage());
    }
}
