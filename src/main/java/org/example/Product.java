package org.example;

import java.math.BigDecimal;

public record Product(
        Integer prodID,
        String produktName,
        BigDecimal preis
) {
}
