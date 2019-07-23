package ro.msg.learning.shop;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MostAbundantLocationStrategy {
    @Autowired
    MostAbundantStrategy mostAbundantStrategy;
    @MockBean
    StockRepository stockRepository;
    @MockBean
    OrderDetailRepository orderDetailRepository;
    @MockBean
    LocationRepository locationRepository;
    @MockBean
    ProductRepository productRepository;
    @Before
    public void setUp() {
        Location location1=new Location("Cluj1","Romania","Cluj-Napoca","Cluj","Str teo nr.1");
        location1.setId(1);
        Location location2=new Location("Oradea1","Romania","Oradea","Bihor","Str nuf nr.93");
        location2.setId(2);
        Location location3=new Location("Cluj2","Romania","Cluj-Napoca","Cluj","Str brs nr.9");
        location3.setId(3);

        Mockito.when(locationRepository.findById(1)).thenReturn(java.util.Optional.of(location1));
        Mockito.when(locationRepository.findById(2)).thenReturn(java.util.Optional.of(location2));
        Mockito.when(locationRepository.findById(3)).thenReturn(java.util.Optional.of(location3));
        Mockito.when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2, location3));
        //todo delete unnecessary objects


        StockID stockID1=new StockID(1,1);
        StockID stockID2=new StockID(2,1);
        StockID stockID3=new StockID(1,2);
        Stock stock1=new Stock(stockID1,50);
        Stock stock2=new Stock(stockID2,50);
        Stock stock3=new Stock(stockID3,50);

        Mockito.when(stockRepository.findByLocation(1)).thenReturn(Arrays.asList(stock1,stock2));
        Mockito.when(stockRepository.findByLocation(2)).thenReturn(Collections.singletonList(stock3));

    }
    @Test
    public void contextLoads() {
        ProductQuantityDTO detail1=new ProductQuantityDTO(1,5);
        ProductQuantityDTO detail2=new ProductQuantityDTO(2,5);
        OrderAndDetailsDTO order1=new OrderAndDetailsDTO(Date.valueOf("2018-09-09"),"Romania","Oradea,","Bihor","Str nuf nr 13", Collections.singletonList(detail1),1);
        OrderAndDetailsDTO order2=new OrderAndDetailsDTO(Date.valueOf("2018-09-09"),"Romania","Oradea,","Bihor","Str nuf nr 13",Arrays.asList(detail1,detail2),1);
        Optional<Location> location=locationRepository.findById(1);
        if(location.isPresent()) {
            assertThat(mostAbundantStrategy.getLocationsForOrder(order1)).isEqualTo(Collections.singletonList(location.get()));//todo change values
            assertThat(mostAbundantStrategy.getLocationsForOrder(order2)).isEqualTo(Arrays.asList(location.get(),location.get()));
        }
    }
}
