package practice.graphql.java.basic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GraphQLModel {
	//Fields
	@Id
	private Integer id;
	private String name;
	private Integer cost;
	private Boolean flag;
	
	//Parameterized Constructor
	public GraphQLModel(Integer id, String name, Integer cost, Boolean flag) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.flag = flag;
	}
	
	//Getters & Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	

}
