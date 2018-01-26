package com.emirates.hackathon.botevent;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sendgrid.Client;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;

public class BotEventHandler implements RequestHandler<Object, String> {

	@Override
	public String handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		Client client = new Client();
	
		Request request = new Request();
		request.setBaseUri("emirates-ta.herokuapp.com");
		request.setMethod(Method.GET);
		request.setEndpoint("/sendMenu");

		try {
			Response response = client.api(request);
			context.getLogger().log("response code: " + response.getStatusCode());
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
			return String.valueOf(response.getStatusCode());
		} catch (IOException ex) {
			context.getLogger().log("error: " + ex);
		}
		return "Error";
	}

}
