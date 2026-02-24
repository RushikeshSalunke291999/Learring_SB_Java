package com.mongodbexample.model;

import lombok.Data;

@Data
public class Product{
	private String image;
	private Object price;
	private Rating rating;
	private String description;
	private int id;
	private String title;
	private String category;
}