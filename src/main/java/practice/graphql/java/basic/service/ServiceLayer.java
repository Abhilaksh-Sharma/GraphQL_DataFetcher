package practice.graphql.java.basic.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import practice.graphql.java.basic.datafetcher.GraphQLDataFetchers;

@Service
public class ServiceLayer {
	
	@Value("classpath:mySchema.graphql")
	private Resource resource;

	private GraphQL graphQL;
	
	@Autowired
	private GraphQLDataFetchers graphQLDataFetchers;
	
	public GraphQL getGraphQL() {
		return graphQL;
	}

	public void setGraphQL(GraphQL graphQL) {
		this.graphQL = graphQL;
	}
	
	
	@PostConstruct
	public void loadSchema() throws IOException {
		//Fetch File from Resource
		File schemaFile = resource.getFile();
		
		//Parse Schema File using Schema Parser 
		//Create a TypeDefinitionRegistry Object
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		
		//Associate Query with DataFetcher --> fetches data with the help of repository
		RuntimeWiring wiring = buildRuntimeWiring();
		
		
		//Create a graphQLSchema Object using SchemaGenerator
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		
		//Initialize the GraphQL graphQL Object
		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
		
	}
	
	public RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring
				.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("models", graphQLDataFetchers.getModels())
						.dataFetcher("modelByID", graphQLDataFetchers.getModelById())
						.dataFetcher("modelByName", graphQLDataFetchers.getModelByName())
						.dataFetcher("modelByCost", graphQLDataFetchers.getModelByCost())
						.dataFetcher("modelByFlag", graphQLDataFetchers.getModelByFlag()))
				.type("Mutation",typeWiring -> typeWiring
						.dataFetcher("createModel", graphQLDataFetchers.saveModel())
						.dataFetcher("updateModel", graphQLDataFetchers.updateModel())
						.dataFetcher("deleteModel", graphQLDataFetchers.deleteModel()))
				.build();
	}

}
