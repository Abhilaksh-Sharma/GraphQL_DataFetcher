package practice.graphql.java.basic.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import practice.graphql.java.basic.model.GraphQLModel;

//By-default all methods in interface are public & abstract.
@Repository
public interface GraphQLRepository extends MongoRepository<GraphQLModel, Integer>{

	List<GraphQLModel> findByName(String name);
	List<GraphQLModel> findByCost(Integer cost);
	List<GraphQLModel> findByFlag(Boolean flag);

}
