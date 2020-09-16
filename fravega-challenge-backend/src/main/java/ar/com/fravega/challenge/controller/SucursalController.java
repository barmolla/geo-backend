package ar.com.fravega.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.fravega.challenge.dto.PointDTO;
import ar.com.fravega.challenge.dto.SucursalDTO;
import ar.com.fravega.challenge.service.SucursalService;

@RestController
@RequestMapping("/api/v1")
public class SucursalController {

	@Autowired
	private SucursalService sucursalService;

	@PostMapping(
			value 	 = "/sucursal",
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SucursalDTO> create(@Valid @RequestBody SucursalDTO sucursal) {
		return ResponseEntity.ok(this.sucursalService.create(sucursal));
	}

	@DeleteMapping("/sucursal/{id}")
	public void delete(@PathVariable(value = "id") Long sucursalId) {
		this.sucursalService.deleteById(sucursalId);
	}

	@PatchMapping(			
			value 	 = "/sucursal/{id}",
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SucursalDTO> update(@PathVariable(value = "id") Long sucursalId, @RequestBody SucursalDTO patch) {
		return ResponseEntity.ok(this.sucursalService.update(sucursalId, patch));
	}

	@GetMapping(
			value = "/sucursal/{id}",
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SucursalDTO> getById(@PathVariable(value = "id") Long sucursalId) {
		return ResponseEntity.ok(this.sucursalService.getById(sucursalId));
	}

	@GetMapping(
			value = "/sucursal",
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SucursalDTO>> getAll() {
		return ResponseEntity.ok(this.sucursalService.getAll());
	}

	@GetMapping(
			value = "/sucursal/search",
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SucursalDTO>> searchByNearness(PointDTO point) {
		return ResponseEntity.ok(this.sucursalService.searchByNearness(point));
	}
}
