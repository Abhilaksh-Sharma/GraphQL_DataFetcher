package practice.graphql.java.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import practice.graphql.java.basic.service.ServiceLayer;

@RestController
public class GraphQLController {

	@Autowired
	ServiceLayer service;

	@RequestMapping("/graphql")
	public ResponseEntity<Object> resolveQuery(@RequestBody String query) {
		System.out.println(service.getGraphQL());
		System.out.println(query);
		ExecutionResult result = service.getGraphQL().execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

}
