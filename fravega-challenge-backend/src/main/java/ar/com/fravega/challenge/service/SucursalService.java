package ar.com.fravega.challenge.service;

import java.util.List;

import ar.com.fravega.challenge.dto.PointDTO;
import ar.com.fravega.challenge.dto.SucursalDTO;

public interface SucursalService extends GenericService<SucursalDTO> {

	public List<SucursalDTO> searchByNearness(PointDTO point);
}
