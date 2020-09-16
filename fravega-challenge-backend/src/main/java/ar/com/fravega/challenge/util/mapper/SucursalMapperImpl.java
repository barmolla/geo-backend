package ar.com.fravega.challenge.util.mapper;

import org.springframework.stereotype.Component;

import ar.com.fravega.challenge.dto.PointDTO;
import ar.com.fravega.challenge.dto.SucursalDTO;
import ar.com.fravega.challenge.entity.Sucursal;

@Component
public class SucursalMapperImpl implements SucursalMapper {

	private static final long serialVersionUID = 8061697332851789486L;

	@Override
	public Sucursal createFrom(SucursalDTO dto) {
		Sucursal sucursalToReturn = new Sucursal();
		PointDTO point = dto.getPoint();

		sucursalToReturn.setAddress(dto.getAddress());
		sucursalToReturn.setLatitude(point.getLatitude());
		sucursalToReturn.setLongitude(point.getLongitude());

		return sucursalToReturn;
	}

	@Override
	public SucursalDTO createFrom(Sucursal entity) {
		SucursalDTO sucursalDTOToReturn = new SucursalDTO();
		PointDTO pointDTO = new PointDTO();

		pointDTO.setLatitude(entity.getLatitude());
		pointDTO.setLongitude(entity.getLongitude());
		sucursalDTOToReturn.setPoint(pointDTO);
		sucursalDTOToReturn.setAddress(entity.getAddress());
		sucursalDTOToReturn.setId(entity.getId());

		return sucursalDTOToReturn;
	}

	@Override
	public Sucursal updateEntity(Sucursal entity, SucursalDTO dto) {
		if (!dto.getAddress().isEmpty()) entity.setAddress(dto.getAddress());

		PointDTO pointDTO = dto.getPoint();

		if (pointDTO != null) {
			if (pointDTO.getLatitude() != null) entity.setLatitude(pointDTO.getLatitude());
			if (pointDTO.getLongitude() != null) entity.setLongitude(pointDTO.getLongitude());
		}

		return entity;
	}

}
