package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.IStrategy;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;


@Configuration
public class StrategyConfiguration {
    @Value("${strategy}")
    private String strategyDescription;

    @Bean
    public IStrategy getStrategy(StockRepository stockRepository, LocationRepository locationRepository) throws StrategyException {

        switch (strategyDescription) {
            case "single location":
                return new SingleLocationStrategy(stockRepository, locationRepository);
            case "most abundant":
                return new MostAbundantStrategy(stockRepository, locationRepository);
            default:
                throw new StrategyException("NO VALID CONFIGURATION FOR STRATEGY");

        }
    }
}
