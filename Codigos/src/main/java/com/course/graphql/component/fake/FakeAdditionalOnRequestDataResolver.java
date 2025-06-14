package com.course.graphql.component.fake;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.graphql.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;

@DgsComponent
public class FakeAdditionalOnRequestDataResolver {
    
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AdditionalOnRequest)
    public String additionalOnRequest(
        @RequestHeader(name = "optionalHeader", required = false) String optionalHeader,
        @RequestHeader(name="mandatoryHeader", required = false) String mandatoryHeader,
        @RequestParam(name="optionalParam", required = false) String optionalParam,
        @RequestParam(name="mandatoryParam", required = false) String mandatoryParam
    ) {
        var sb = new StringBuilder();
        sb.append("Optional header: " + optionalHeader);
        sb.append(", ");
        sb.append("Mandatory header: " + mandatoryHeader);
        sb.append(", ");
        sb.append("Optional param: " + optionalParam);
        sb.append(", ");
        sb.append("Mandatory param: " + mandatoryParam);

        return sb.toString();
    }
}
