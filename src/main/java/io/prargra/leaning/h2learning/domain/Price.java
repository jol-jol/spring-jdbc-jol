package io.prargra.leaning.h2learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Price {
    int priceId;
    int productId;
    float price;
    float discount;
}
