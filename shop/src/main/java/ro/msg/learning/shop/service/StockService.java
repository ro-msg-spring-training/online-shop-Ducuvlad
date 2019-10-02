package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> getStocksFromLocationId(Integer locationId) {
        return stockRepository.findByLocation(locationId).stream().map(StockDTO::fromStock).collect(Collectors.toList());
    }
}
