package com.ikth.apps.msreserve.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.ikth.apps.msreserve.reservation.configuration.DataAccessObjectConfiguration;
import com.ikth.apps.msreserve.reservation.configuration.SwaggerConfiguration;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.ikth.apps.msreserve"})
@Import({
	SwaggerConfiguration.class
	, DataAccessObjectConfiguration.class})
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}
}
