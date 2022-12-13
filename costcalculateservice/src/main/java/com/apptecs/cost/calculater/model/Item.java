package com.apptecs.cost.calculater.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item implements Serializable{
	private Float weight;
	private Float height;
	private Float width;
	private Float length;
	private String voucher;
	
	public Float getValume() {
		System.out.println(width*height*length);
		return width*height*length;
	}

}
