package com.anil.awslambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;

@SpringBootTest
class AwsLambdaWithSpringBootApplicationTests {

	@Test
	void contextLoads() {
		Context lambdaContext = null;
		LambdaHandler lambdaHandler = new LambdaHandler();
	    AwsProxyRequest req = new AwsProxyRequestBuilder("/api/v1/users", "GET").build();
	    AwsProxyResponse resp = lambdaHandler.handleRequest(req, lambdaContext);
	    Assertions.assertNotNull(resp.getBody());
	    Assertions.assertEquals(200, resp.getStatusCode());
	}

}
