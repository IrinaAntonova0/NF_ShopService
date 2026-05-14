package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record Order(
        String orderID,
        Integer customerID,
        String customerName,
        List<Product> orderedProducts,
        List<Integer> orderedProductAmounts,
        java.math.BigDecimal totalPrice) {

    private static String generateNewOrderID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private static BigDecimal calculateTotal(ArrayList<Product> orderedProductList, List<Integer> orderedProductAmounts ){
        BigDecimal total= new BigDecimal("0.00", MathContext.DECIMAL32);
        total = total.setScale(4, RoundingMode.HALF_UP);
        int i = 0;
        for ( Product p : orderedProductList){
            total = total.add( (p.preis()).multiply(BigDecimal.valueOf(orderedProductAmounts.get(i++)) ) );
        }
        return total;
    }

    public static Order createNewOrder(String customerName, ArrayList<Product> orderedProductList, List<Integer> orderedProductAmounts ) {
        return new Order(generateNewOrderID(),0, customerName, orderedProductList, orderedProductAmounts, calculateTotal(orderedProductList, orderedProductAmounts ));
    }
}
