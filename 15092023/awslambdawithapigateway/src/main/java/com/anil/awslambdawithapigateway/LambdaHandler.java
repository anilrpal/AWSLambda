package com.anil.awslambdawithapigateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.anil.awslambdawithapigateway.model.APIResponse;
import com.anil.awslambdawithapigateway.model.Employee;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIResponse> {

	@Override
	public APIResponse handleRequest(APIGatewayProxyRequestEvent input, Context context) {
		return buildAPIResponse(context);
	}

	
	private APIResponse buildAPIResponse(Context context) {
		Map<String,String> headers= new HashMap();
		headers.put("X-amazon-author", "Anil Pal");
		headers.put("X-amazon-apiVersion", "vq");
		
		String body= employeeList().toString();
		
		APIResponse apiResponse=new APIResponse(200, body, headers);
		
		context.getLogger().log("Response : "+apiResponse);
		
		return apiResponse;
	}
	
	private List<Employee> employeeList(){
		return Stream.of(new Employee(100,"Anil Pal","anil.pal@cloverinfotech.com"),
				new Employee(101,"Divesh Kukreja","divesh.kukreja@cloverinfotech.com"),
				new Employee(102,"Pavan Mundada","pavan.mundada@cloverinfotech.com"),
				new Employee(103,"Balasaheb More","balasaheb.more@cloverinfotech.com")).collect(Collectors.toList());
	}
}
