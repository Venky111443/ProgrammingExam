package com.apptecs.cost.calculater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoucherRes {
	private Float discount;
	private String code;
	private String expiry;
}
