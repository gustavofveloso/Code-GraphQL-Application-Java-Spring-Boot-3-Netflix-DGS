package com.course.graphql.component.fake;

import java.util.List;

import com.course.graphql.datasource.fake.FakeHelloDataSource;
import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.Hello;
import com.course.graphql.generated.types.HelloInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class FakeHelloMutation {

    // @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.AddHello)
    @DgsMutation
    public int addHello(@InputArgument(name = "helloInput") HelloInput helloInput) {
        var hello = Hello.newBuilder().text(helloInput.getText())
                .randomNumber(helloInput.getNumber()).build();

        FakeHelloDataSource.HELLO_LIST.add(hello);

        return FakeHelloDataSource.HELLO_LIST.size();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ReplaceHelloText)
    public List<Hello> replaceHelloText(@InputArgument(name = "helloInput") HelloInput helloInput) {
        FakeHelloDataSource.HELLO_LIST.stream().filter(
            h -> h.getRandomNumber() == helloInput.getNumber()
        ).forEach( h -> h.setText(helloInput.getText()));

        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.DeleteHello)
    public int deleteHello(int number) {
        FakeHelloDataSource.HELLO_LIST.removeIf(h -> h.getRandomNumber() == number);

        return FakeHelloDataSource.HELLO_LIST.size();
    }
}
