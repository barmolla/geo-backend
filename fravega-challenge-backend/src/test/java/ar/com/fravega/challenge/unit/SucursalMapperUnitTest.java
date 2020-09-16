package ar.com.fravega.challenge.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.com.fravega.challenge.dto.PointDTO;
import ar.com.fravega.challenge.dto.SucursalDTO;
import ar.com.fravega.challenge.entity.Sucursal;
import ar.com.fravega.challenge.util.mapper.SucursalMapperImpl;

public class SucursalMapperUnitTest {

	private final SucursalMapperImpl sucursalMapper = new SucursalMapperImpl();

	@Test
	void givenAnEntityWhenCreateFromThenReturnDTO() {
		int id = 1;
		double latitude = 10.2;
		double longitude = 11.2;
		String address = "suc1";
		Sucursal entity = new Sucursal();

		entity.setId(id);
		entity.setAddress(address);
		entity.setLatitude(latitude);
		entity.setLongitude(longitude);

		SucursalDTO dto = this.sucursalMapper.createFrom(entity);

		assertAll("Should return a DTO",
				() -> assertNotNull(dto),
				() -> assertEquals(Long.valueOf(id), dto.getId()),
				() -> assertEquals(address, dto.getAddress()),
				() -> assertEquals(Double.valueOf(latitude), dto.getPoint().getLatitude()),
				() -> assertEquals(Double.valueOf(longitude), dto.getPoint().getLongitude()));
	}

	@Test
	void givenADTOWhenCreateFromThenReturnEntity() {
		double latitude = 11.3;
		double longitude = 13.4;
		String address = "suc1";
		SucursalDTO dto = new SucursalDTO();
		PointDTO point = new PointDTO(latitude, longitude);

		dto.setAddress(address);
		dto.setPoint(point);

		Sucursal entity = this.sucursalMapper.createFrom(dto);

		assertAll("Should return an Entity",
				() -> assertNotNull(entity),
				() -> assertEquals(address, dto.getAddress()),
				() -> assertEquals(Double.valueOf(latitude), dto.getPoint().getLatitude()),
				() -> assertEquals(Double.valueOf(longitude), dto.getPoint().getLongitude()));
	}

	@Test
	void givenDTOArrayWhenCreateFromDTOsThenReturnEntitiesArray() {
		List<Sucursal> sucursales = this.sucursalMapper.createFromDtos(getSucursalDTOArray());

		assertNotNull(sucursales);
		assertEquals(3, sucursales.size());
		assertEquals("suc1", sucursales.get(0).getAddress());
		assertEquals("suc3", sucursales.get(2).getAddress());
		assertEquals(Double.valueOf(11.6), sucursales.get(1).getLatitude());

	}

	@Test
	void givenEntityArrayWhenCreateFromEntitiessThenReturnDTOArray() {
		List<SucursalDTO> sucursales = this.sucursalMapper.createFromEntities(getSucursalArray());

		assertNotNull(sucursales);
		assertEquals(3, sucursales.size());
		assertEquals("suc1", sucursales.get(0).getAddress());
		assertEquals("suc3", sucursales.get(2).getAddress());
		assertEquals(Long.valueOf(3), sucursales.get(0).getId());
		assertEquals(Long.valueOf(7), sucursales.get(2).getId());
		assertEquals(Double.valueOf(-12.5), sucursales.get(1).getPoint().getLatitude());

	}

	@Test
	void givenDTOWithNewValuesWhenUpdateEntityThenReturnUpdatedEntity() {
		Sucursal entity = new Sucursal();

		entity.setId(1);
		entity.setAddress("old address");
		entity.setLatitude(10.2);
		entity.setLongitude(11.2);

		SucursalDTO dto = new SucursalDTO();

		dto.setAddress("new address");
		dto.setPoint(new PointDTO(5.1, 6.1));

		entity = this.sucursalMapper.updateEntity(entity, dto);

		assertEquals("new address", entity.getAddress());
		assertEquals(1, entity.getId());
		assertEquals(Double.valueOf(5.1), entity.getLatitude());
		assertEquals(Double.valueOf(6.1), entity.getLongitude());
	}

	private List<Sucursal> getSucursalArray() {
		Sucursal suc1 = new Sucursal();
		Sucursal suc2 = new Sucursal();
		Sucursal suc3 = new Sucursal();

		suc1.setId(3);
		suc2.setId(5);
		suc3.setId(7);
		suc1.setAddress("suc1");
		suc2.setAddress("suc2");
		suc3.setAddress("suc3");
		suc1.setLatitude(12.5);
		suc2.setLatitude(-12.5);
		suc3.setLatitude(24.5);
		suc1.setLongitude(6.2);
		suc1.setLongitude(-6.2);
		suc1.setLongitude(12.4);

		return Arrays.asList(suc1, suc2, suc3);
	}

	private List<SucursalDTO> getSucursalDTOArray() {
		SucursalDTO dto1 = new SucursalDTO();
		SucursalDTO dto2 = new SucursalDTO();
		SucursalDTO dto3 = new SucursalDTO();
		String address1 = "suc1";
		String address2 = "suc3";
		String address3 = "suc3";
		double latitude1 = 9.3;
		double latitude2 = 11.6;
		double latitude3 = -98.2;
		double longitude1 = 11.4;
		double longitude2 = 13.1;
		double longitude3 = -3.7;
		PointDTO point1 = new PointDTO(latitude1, longitude1);
		PointDTO point2 = new PointDTO(latitude2, longitude2);
		PointDTO point3 = new PointDTO(latitude3, longitude3);

		dto1.setAddress(address1);
		dto1.setPoint(point1);
		dto2.setAddress(address2);
		dto2.setPoint(point2);
		dto3.setAddress(address3);
		dto3.setPoint(point3);

		return Arrays.asList(dto1, dto2, dto3);
	}
}
