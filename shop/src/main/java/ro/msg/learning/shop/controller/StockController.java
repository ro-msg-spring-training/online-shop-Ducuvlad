package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @GetMapping(value = "/{locationId}/stocks")
    public List<StockDTO> getStocksFromLocation(@PathVariable("locationId") Integer locationId) {
        System.out.println(requestMappingHandlerAdapter.getMessageConverters().toString());
        return stockService.getStocksFromLocationId(locationId);
    }
}
