package com.course.graphql.datasource.fake;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.course.graphql.generated.types.Stock;

import net.datafaker.Faker;

@Configuration
public class FakeStockDataSource {
    
    @Autowired
    private Faker faker;

    public Stock randomStock() {
        return Stock.newBuilder().lastTradeDateTime(OffsetDateTime.now())
                .price(faker.random().nextInt(100, 1000))
                .symbol(faker.stock().nyseSymbol())
                .build();
    }
}
