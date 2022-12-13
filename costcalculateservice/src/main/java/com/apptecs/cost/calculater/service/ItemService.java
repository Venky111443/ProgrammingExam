package com.apptecs.cost.calculater.service;

import java.util.Arrays;

import org.apache.logging.log4j.message.ExitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.apptecs.cost.calculater.model.Item;
import com.apptecs.cost.calculater.model.OrderStatus;
import com.apptecs.cost.calculater.model.VoucherRes;

@Service
@PropertySource("classpath:application.properties")
public class ItemService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${rule.Reject}")
	Float p1;
	@Value("${rule.HeavyParcel}")
	Float p2;
	@Value("${rule.SmallParcel}")
	Float p3;
	@Value("${rule.MediumParcel}")
	Float p4;
	@Value("${rule.LargeParcel}")
	Float p5;
	@Value("${rule.Reject.PHP}")
	Float p1php;
	@Value("${rule.HeavyParcel.PHP}")
	Float p2php;
	@Value("${rule.SmallParcel.PHP}")
	Float p3php;
	@Value("${rule.MediumParcel.PHP}")
	Float p4php;
	@Value("${rule.LargeParcel.PHP}")
	Float p5php;

	public OrderStatus calculate(Item item) {
		System.out.println(p5);
		String status = "Reject";
		OrderStatus orderStatus = new OrderStatus();
		if (item.getWeight() <= 50) {
			float calculatePrice = calculatePrice(item);
			orderStatus.setCost(calculatePrice-getVoucherValue(item.getVoucher()));
			orderStatus.setStatus("success");
			return orderStatus;
		} else {
			orderStatus.setStatus("Rejected");
			return orderStatus;
		}
	}

	private float calculatePrice(Item item) {
		
		
		if(item.getWeight()>p2)
		{
			return p2php*p2;
		}
		else if(item.getValume()<p3)
		{
			return p3php*item.getValume();
		}
		else if(item.getValume()<p4)
		{
			return p4php*item.getValume();
		}
		else {
			return p5php*item.getValume();
		}
	}

	private Float getVoucherValue(String voucher) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		Float dis=0.0F;
		try {
			ResponseEntity<VoucherRes> exchange = restTemplate.exchange("https://mynt-exam.mocklab.io/voucher/"+voucher+"?key=apikey", HttpMethod.GET,
					entity, VoucherRes.class);
			if(exchange.getStatusCode().is2xxSuccessful())
			{
				dis=exchange.getBody().getDiscount();
			}
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		return dis;
	}

}
