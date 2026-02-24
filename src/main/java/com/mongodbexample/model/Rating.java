package com.mongodbexample.model;

import lombok.Data;

@Data
public class Rating{
	private Object rate;
	private int count;
}