package com.lessonsbyleo.practicerepo.service;

import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.Order;
import com.lessonsbyleo.practicerepo.data.TotalBill;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;
import com.lessonsbyleo.practicerepo.util.ShoppingCartCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Service
public class ShoppingCartTestHarness {
    private final SalesTaxWebClientTestDouble internalWebclientTestDouble;
    private final SalesTaxWebClientTestDouble externalWebclientTestDouble;
    private final InventoryWebclientTestDouble inventoryWebclientTestDouble;

    public ShoppingCartTestHarness(SalesTaxWebclient internalWebclient, SalesTaxWebclient externalWebclient, InventoryWebclient inventoryWebclient) {
        this.internalWebclientTestDouble = (SalesTaxWebClientTestDouble) internalWebclient;
        this.externalWebclientTestDouble = (SalesTaxWebClientTestDouble) externalWebclient;
        this.inventoryWebclientTestDouble = (InventoryWebclientTestDouble) inventoryWebclient;
    }

    public void verifyInternalSalesTaxUsed() {
        verify(internalWebclientTestDouble.getSalesTaxWebclientMock(), times(1)).getSalesTax(anyString());
        verify(externalWebclientTestDouble.getSalesTaxWebclientMock(), times(0)).getSalesTax(anyString());
    }

    public void verifyExternalSalesTaxUsed() {
        verify(internalWebclientTestDouble.getSalesTaxWebclientMock(), times(1)).getSalesTax(anyString());
        verify(externalWebclientTestDouble.getSalesTaxWebclientMock(), times(1)).getSalesTax(anyString());
    }

    public void verifyInventoryWebclientCalled() {
        verify(inventoryWebclientTestDouble.getInventoryWebclientMock(), times(1)).getInventory(any());
    }

    public String getInternalSalesTaxZipCode() {
        return "11111-111";
    }

    public String getExternalSalesTaxZipCode() {
        return "10005-111";
    }

    public String getUnavailableSalesTaxZipCode() {
        return "99999-999";
    }

    public double getExpectedSalesTax(String zipcode) {
        Double expectedSalesTax = internalWebclientTestDouble.getSalesTaxWithoutMock(zipcode);
        if(Objects.isNull(expectedSalesTax)){
            expectedSalesTax = externalWebclientTestDouble.getSalesTaxWithoutMock(zipcode);
        }
        return expectedSalesTax;
    }

    public double getExpectedTotal(Order order) {
        TotalBill totalBill = new TotalBill();
        List<Item> items = order.getItems();
        List<Item> expectedItems = new ArrayList<>();
        List<String> skus = new ArrayList<>();

        for(Item item: items) {
            skus.add(item.getSku());
        }
        Map<String, Item> inventoryMap = inventoryWebclientTestDouble.getInventoryWithoutMock(skus);

        for(Item item: items){
            Item expectedItem = new Item(item);
            expectedItem.setCost(inventoryMap.get(item.getSku()).getCost());
            expectedItems.add(expectedItem);
        }
        String zipcode = order.getCustomer().getAddress().getZipCode();
        totalBill.setSalesTax(getExpectedSalesTax(zipcode));
        Order expectedOrder = new Order();
        expectedOrder.setItems(expectedItems);
        totalBill.setOrder(expectedOrder);
        return ShoppingCartCalculator.calculate(totalBill).getTotal();
    }

    public void resetMocks() {
        reset(this.internalWebclientTestDouble.getSalesTaxWebclientMock());
        reset(this.externalWebclientTestDouble.getSalesTaxWebclientMock());
        reset(this.inventoryWebclientTestDouble.getInventoryWebclientMock());
    }

}
