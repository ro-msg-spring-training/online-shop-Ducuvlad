package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Stock;

@Data
@Builder
public class StockDTO {

    private Integer productId;
    private Integer locationId;
    private Integer quantity;

    public static StockDTO fromStock(Stock stock) {
        return StockDTO.builder()
                .productId(stock.getStockID().getProductID())
                .locationId(stock.getStockID().getLocationID())
                .quantity(stock.getQuantity())
                .build();
    }
}
