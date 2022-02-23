package com.lessonsbyleo.practicerepo.bdd;

import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.Order;
import com.lessonsbyleo.practicerepo.data.ShoppingCartScenarioData;
import com.lessonsbyleo.practicerepo.exception.InvalidOrderException;
import com.lessonsbyleo.practicerepo.service.ShoppingCartTestHarness;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class InventoryScenarioSteps {
    @Autowired
    private ShoppingCartTestHarness shoppingCartTestHarness;
    @Autowired
    private Map<String, ShoppingCartScenarioData> shoppingCartScenarioDataMap;

    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("one of the orders items is not part of the inventory")
    public void oneOfTheOrdersItemsIsNotPartOfTheInventory() {
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());
        Order order = shoppingCartScenarioData.getOrder();
        Item item = new Item();
        item.setSku("sku_unknown");
        order.getItems().add(item);

        shoppingCartScenarioData.setExpectedShoppingCartExceptionMessage(InvalidOrderException.INVALID_ORDER);
    }

    @Given("one of the orders items has more quantity than inventory can provide")
    public void oneOfTheOrdersItemsHasMoreQuantityThanInventoryCanProvide() {
        ShoppingCartScenarioData shoppingCartScenarioData = shoppingCartScenarioDataMap.get(scenario.getId());
        Order order = shoppingCartScenarioData.getOrder();
        order.getItems().get(0).setQuantity(Integer.MAX_VALUE);

        shoppingCartScenarioData.setExpectedShoppingCartExceptionMessage(InvalidOrderException.INVALID_ORDER);
    }

    @Then("the shopping cart gets the orders inventory details")
    public void getsTheOrdersInventoryDetails() {
        shoppingCartTestHarness.verifyInventoryWebclientCalled();
    }

}
