package com.poi.springbootjpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.poi.springbootjpa.Application;
import com.poi.springbootjpa.model.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllVehicles() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/vehicles",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetVehicleById() {
		Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/1", Vehicle.class);
		System.out.println(vehicle.getPlaca());
		assertNotNull(vehicle);
	}

	@Test
	public void testCreateVehicle() {
		Vehicle vehicle = new Vehicle();
		
		vehicle.setPlaca("AVO-0350");
		vehicle.setData("");
		vehicle.setLongitude(30l);
		vehicle.setLatitude(50l);

		ResponseEntity<Vehicle> postResponse = restTemplate.postForEntity(getRootUrl() + "/vehicles", vehicle, Vehicle.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateVehicle() {
		int id = 1;
		Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		vehicle.setPlaca("AVO-0350");
		vehicle.setData("");
		vehicle.setLongitude(30l);
		vehicle.setLatitude(50l);

		restTemplate.put(getRootUrl() + "/vehicles/" + id, vehicle);

		Vehicle updatedVehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		assertNotNull(updatedVehicle);
	}

	@Test
	public void testDeleteVehicle() {
		int id = 2;
		Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		assertNotNull(vehicle);

		restTemplate.delete(getRootUrl() + "/vehicles/" + id);

		try {
			vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
