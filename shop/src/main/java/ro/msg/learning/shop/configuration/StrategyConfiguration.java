package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.IStrategy;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;


@Configuration
public class StrategyConfiguration {
    @Value("${strategy}")
    String strategyDescription;
    @Bean
    public IStrategy getStrategy() {

        switch(strategyDescription){
            case "single location":
                return new SingleLocationStrategy();
            case "most abundant":
                return new MostAbundantStrategy();
            default:
            throw new Error("NO VALID CONFIGURATION FOR STRATEGY");//todo make a better error class

        }
    }
}
