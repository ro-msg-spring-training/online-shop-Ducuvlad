package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exception.NoLocationException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MostAbundantStrategy implements IStrategy {
    private StockRepository stockRepository;
    private LocationRepository locationRepository;
    @Override
    public List<Location>  getLocationsForOrder(OrderAndDetailsDTO order) {
        //List<Location> locations = locationRepository.findAll();

        List<ProductQuantityDTO> products = order.getProducts();


        return products.stream()
                .map(product ->
                {
                    Optional<Integer> location= stockRepository.findLargestLocationForProduct(product.getProductID(),product.getQuantity());
                    if(location.isPresent()) {
                        Optional<Location>newLocation=locationRepository.findById(location.get());
                        if(newLocation.isPresent())
                            return newLocation.get();
                        else throw new  NoLocationException("No location with the necessary stock found for a product ERROR:2");
                    }
                    else
                        throw new  NoLocationException("No location with the necessary stock found for product "+product.getProductID()+" ERROR:1");
                })
                .collect(Collectors.toList());
    }
}
