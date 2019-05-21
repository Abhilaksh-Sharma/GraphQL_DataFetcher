package practice.graphql.java.basic.datafetcher;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import practice.graphql.java.basic.model.GraphQLModel;
import practice.graphql.java.basic.repository.GraphQLRepository;

/* 
public interface DataFetcher<T> {
    T get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception;
}
 * DataFetcher is a functional interface 
 * with only one method "get"to override 
 * with one argument of data-type "DataFetchingEnvironment"
 * DataFetchingEnvironment --> gives us the Source/Parent instance
 * DataFetchingEnvironment --> gives us the parameters passed to a query from client side
 * 
 * T --> represents what type of data they are going to fetch from query like List<Model> , Model , String etc.
 * */



@Component
public class GraphQLDataFetchers {
	
	@Autowired
	GraphQLRepository repository;
	
	//QUERY
	public DataFetcher<List<GraphQLModel>> getModels() {
		return dataFetchingEnvironment ->{
			return repository.findAll();
		};
	}
	
	public DataFetcher<Optional<GraphQLModel>> getModelById() {
		return dataFetchingEnvironment ->{
			return repository.findById(dataFetchingEnvironment.getArgument("id"));
		};
	}
	
	public DataFetcher<List<GraphQLModel>> getModelByName() {
		return dataFetchingEnvironment -> {
			return repository.findByName(dataFetchingEnvironment.getArgument("name"));
		};
	}
	
	public DataFetcher<List<GraphQLModel>> getModelByCost() {
		return dataFetchingEnvironment -> {
			return repository.findByCost(dataFetchingEnvironment.getArgument("cost"));
		};
	}
	
	public DataFetcher<List<GraphQLModel>> getModelByFlag() {
		return dataFetchingEnvironment -> {
			return repository.findByFlag(dataFetchingEnvironment.getArgument("flag"));
		};
	}

	
	//MUTATION
	public DataFetcher<GraphQLModel> saveModel() {
		return dataFetchingEnvironment -> {
			return repository.save(new GraphQLModel(dataFetchingEnvironment.getArgument("id"),dataFetchingEnvironment.getArgument("name"),dataFetchingEnvironment.getArgument("cost"),dataFetchingEnvironment.getArgument("flag")));
		};
	}
	
	public DataFetcher<GraphQLModel> updateModel() {
		return dataFetchingEnvironment -> {
			return repository.save(new GraphQLModel(dataFetchingEnvironment.getArgument("id"),dataFetchingEnvironment.getArgument("name"),dataFetchingEnvironment.getArgument("cost"),dataFetchingEnvironment.getArgument("flag")));
		};
	}
	
	public DataFetcher<Optional<GraphQLModel>> deleteModel() {
		return dataFetchingEnvironment -> {
			Integer id = dataFetchingEnvironment.getArgument("id");
			Optional<GraphQLModel> model = repository.findById(id);
			repository.deleteById(id);
			return model;
		};
	}

}
