package com.course.graphql.component.fake;

import java.time.Duration;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;

import com.course.graphql.datasource.fake.FakeStockDataSource;
import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.Stock;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsSubscription;

import reactor.core.publisher.Flux;

@DgsComponent
public class FakeStockDataResolver {
    
    @Autowired
    private FakeStockDataSource dataSource;

    // @DgsSubscription
    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.RandomStock)
    public Publisher<Stock> randomStock() {
        return Flux.interval(Duration.ofSeconds(3)).map(t -> dataSource.randomStock());
    }
}
