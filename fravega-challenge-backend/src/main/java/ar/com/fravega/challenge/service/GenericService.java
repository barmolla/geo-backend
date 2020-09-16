package ar.com.fravega.challenge.service;

import java.util.List;

import ar.com.fravega.challenge.dto.BaseDTO;
import ar.com.fravega.challenge.exception.ResourceNotFoundException;

public interface GenericService<DTO extends BaseDTO> {

	List<DTO> getAll();

	DTO update(Long id, DTO dto);

	DTO create(DTO dto);

	DTO getById(Long id) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException;
}
