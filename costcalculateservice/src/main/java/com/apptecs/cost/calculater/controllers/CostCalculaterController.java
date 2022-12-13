package com.apptecs.cost.calculater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptecs.cost.calculater.model.Item;
import com.apptecs.cost.calculater.model.OrderStatus;
import com.apptecs.cost.calculater.service.ItemService;

@RestController
@RequestMapping("api/price")
public class CostCalculaterController {

	@Autowired
	ItemService itemService;

	@PostMapping
	private OrderStatus saveBook(@RequestBody Item item) {
		return itemService.calculate(item);
	}

}
