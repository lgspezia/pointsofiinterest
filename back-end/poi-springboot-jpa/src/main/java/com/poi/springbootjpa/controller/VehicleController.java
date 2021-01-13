package com.poi.springbootjpa.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poi.springbootjpa.exception.ResourceNotFoundException;
import com.poi.springbootjpa.model.Vehicle;
import com.poi.springbootjpa.repository.VehicleRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class VehicleController {
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long vehicleId)
			throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		return ResponseEntity.ok().body(vehicle);
	}

	@PostMapping("/vehicles")
	public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@PutMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value = "id") Long vehicleId,
			@Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		vehicle.setData(vehicleDetails.getData());
		vehicle.setPlaca(vehicleDetails.getPlaca());
		vehicle.setLatitude(vehicleDetails.getLatitude());
		vehicle.setLongitude(vehicleDetails.getLongitude());
		vehicle.setVelocidade(vehicleDetails.getVelocidade());
		
		final Vehicle updatedVehicle = vehicleRepository.save(vehicle);
		return ResponseEntity.ok(updatedVehicle);
	}

	@DeleteMapping("/vehicles/{id}")
	public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long vehicleId)
			throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));

		vehicleRepository.delete(vehicle);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
