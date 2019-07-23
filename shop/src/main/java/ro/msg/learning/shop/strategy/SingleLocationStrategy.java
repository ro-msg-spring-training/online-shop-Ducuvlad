package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exception.NoLocationException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SingleLocationStrategy implements IStrategy {

    private StockRepository stockRepository;
    private LocationRepository locationRepository;
    @Override
    public List<Location>  getLocationsForOrder(OrderAndDetailsDTO order) {
        List<Location> locations = locationRepository.findAll()
                .stream()
                .filter(location -> checkIfLocationHasAll(order.getProducts(),location))
                .collect(Collectors.toList());
        Optional<Location> singleLocation=locations.stream().min(Comparator.comparingInt(BaseEntity::getId));
        if(locations.size()==0)
            throw new NoLocationException("No location that has all products has been found");
        List<Location> singleLocations=new ArrayList<>();
        for(int i=0;i<order.getProducts().size();i++)
        {
            singleLocations.add(singleLocation.get());
        }
        return singleLocations;
    }


    private boolean checkIfLocationHasAll(List<ProductQuantityDTO> productsQuantities,Location location )   {

        List<Stock> stocks = stockRepository.findByLocation(location.getId());
        List<Integer> stockProductsIds = stocks.stream()
                .map(stock -> stock.getStockID().getProductID())
                .collect(Collectors.toList());

        List<Integer> productsIds = productsQuantities.stream()
                .map(ProductQuantityDTO::getProductID)
                .collect(Collectors.toList());

        if (!stockProductsIds.containsAll(productsIds)) return false;

        return productsQuantities.stream()
                .allMatch(product -> (product.getQuantity() <=
                        stocks.stream().filter(s->s.getStockID().getProductID()==product.getProductID()).findFirst().get().getQuantity()));



    }
}
