package com.example.store.repository;

import com.example.store.entity.Stock;
import org.springframework.data.jpa.domain.Specification;

public class StockSpecifications {

    public static Specification<Stock> filterByCellNumber(Integer cellNumber) {
        return (root, query, builder) -> {
            if (cellNumber != null) {
                return builder.equal(root.get("cellNumber"), cellNumber);
            }
            return null;
        };
    }

    public static Specification<Stock> filterByProductId(Long productId) {
        return (root, query, builder) -> {
            if (productId != null) {
                return builder.equal(root.get("product").get("id"), productId);
            }
            return null;
        };
    }

    public static Specification<Stock> filterByQuantity(Integer quantity) {
        return (root, query, builder) -> {
            if (quantity != null) {
                return builder.equal(root.get("quantity"), quantity);
            }
            return null;
        };
    }
}





