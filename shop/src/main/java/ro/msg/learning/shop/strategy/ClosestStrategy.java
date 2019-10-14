package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exception.NoLocationException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.util.LocationRestTemplate;
import ro.msg.learning.shop.util.Tuple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClosestStrategy implements IStrategy {
    private LocationRestTemplate locationRestTemplate = new LocationRestTemplate();
    private StockRepository stockRepository;
    private LocationRepository locationRepository;

    public ClosestStrategy(StockRepository stockRepository, LocationRepository locationRepository) {
        this.stockRepository = stockRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getLocationsForOrder(OrderAndDetailsDTO order) {
        List<ProductQuantityDTO> products = order.getProducts();
        //order.getCity()
        List<Location> locations = locationRepository.findAll();
        List<String> locationNames = locations
                .stream()
                .map(l -> l.getStreetAddress() + ", " + l.getCity() + ", " + l.getCounty())
                .collect(Collectors.toList());
        locationNames.add(0, order.getStreetAddress() + ", " + order.getCity() + ", " + order.getCounty());
        String distances = locationRestTemplate.getDistanceData(locationNames);
        List<String> listDistances = new LinkedList<String>(Arrays.asList(distances.split(",")));
        listDistances.remove(0);
        List<Location> sortedLocations = IntStream.range(0, listDistances.size())
                .mapToObj(i -> new Tuple<>(listDistances.get(i), locations.get(i)))
                .sorted(Comparator.comparing(Tuple::getX))
                .map(Tuple::getY)
                .collect(Collectors.toList());
        return products.stream()
                .map(product -> sortedLocations.stream()
                        .filter(location ->
                                stockRepository.findStockByStockID_LocationIDAndStockID_ProductIDAndQuantityGreaterThanEqual
                                        (product.getProductID(), location.getId(), product.getQuantity()).isPresent())
                        .findFirst()
                        .orElseThrow(() -> new NoLocationException("No location has the needed stock for product" + product.getProductID())))
                .collect(Collectors.toList());
    }
}
