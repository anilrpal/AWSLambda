package com.anil;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.anil.service.EmployeeService;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
		EmployeeService employeeService=new EmployeeService();
		switch(input.getHttpMethod()) {
		case "POST":
			//Save employee
			return employeeService.saveEmployee(input, context);
		case "GET":
			if(input.getPathParameters()!=null) {
				// fetch employee by employee id
				return employeeService.getEmployeeById(input, context);
			}else {
				//fetch all employee
				return employeeService.getEmployees(input, context);
			}
		case "DELETE":
			if(input.getPathParameters()!=null) {
				//delete employee by employee id
				return  employeeService.deleteEmployeeById(input, context);
			}
		default :
			// throw some error
			throw new Error("Unsupported method "+input.getHttpMethod());
		}
	}

}
