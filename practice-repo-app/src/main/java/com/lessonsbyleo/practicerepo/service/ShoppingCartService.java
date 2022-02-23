package com.lessonsbyleo.practicerepo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.practicerepo.data.Order;
import com.lessonsbyleo.practicerepo.data.TotalBill;
import com.lessonsbyleo.practicerepo.exception.InvalidOrderException;
import com.lessonsbyleo.practicerepo.exception.ShoppingCartException;
import com.lessonsbyleo.practicerepo.exception.ZipCodeUnavailableException;
import com.lessonsbyleo.practicerepo.util.OrderUtil;
import com.lessonsbyleo.practicerepo.util.ShoppingCartCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);

    @Autowired
    private SalesTaxService salesTaxService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ObjectWriter objectWriter;

    public TotalBill calculateTotalBill(Order order, String webclientType) throws ShoppingCartException {
        TotalBill totalBill = setSalesTaxAndInventoryCosts(order, webclientType);
        return ShoppingCartCalculator.calculate(totalBill);
    }

    private TotalBill setSalesTaxAndInventoryCosts(Order order,String webclientType) throws ShoppingCartException {
        TotalBill totalBill = new TotalBill();

        try {
            double salesTax = getSalesTax(order, webclientType);
            Order updatedOrder = inventoryService.validateAndUpdateOrder(order,webclientType);

            totalBill.setOrder(updatedOrder);
            totalBill.setSalesTax(salesTax);
            return totalBill;
        } catch (InvalidOrderException e) {
            logInvalidOrderException(e,objectWriter);
            throw new ShoppingCartException(e);
        } catch (ZipCodeUnavailableException e) {
            throw new ShoppingCartException(e);
        }
    }

    private void logInvalidOrderException(InvalidOrderException e, ObjectWriter objectWriter){
        try {
            logger.error("InvalidOrderException InvalidItems:\n{}",objectWriter.writeValueAsString(e.getInvalidItems()));
        } catch (JsonProcessingException ex) {
            logger.error("Unable to log invalidItems");
        }
    }

    private double getSalesTax(Order order, String webclientType) throws ZipCodeUnavailableException {
        String zipCode = OrderUtil.getZipcode(order);
        return salesTaxService.getSalesTax(zipCode, webclientType);
    }
}
