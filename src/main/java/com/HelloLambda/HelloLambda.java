package com.HelloLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda implements RequestHandler<Integer, String>{

    @Override
    public String handleRequest(Integer input, Context context) {
        System.out.println(input);
        return String.valueOf(input);
    }
}