package com.mongodbexample.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
@Data
public class Rating{
	private Object rate;
	private int count;
}