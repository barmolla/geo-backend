package ar.com.fravega.challenge.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.fravega.challenge.dto.PointDTO;
import ar.com.fravega.challenge.dto.SucursalDTO;
import ar.com.fravega.challenge.exception.ResourceNotFoundException;
import ar.com.fravega.challenge.service.SucursalService;

public class SucursalServiceIntegrationTest extends IntegrationTestComponent {

	@Autowired
	private SucursalService sucursalService;

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	void givenAValidIDWhenGetByIDThenReturnsSucursalDTO(int id) {
		SucursalDTO sucursalDTO = this.sucursalService.getAll().get(id);
		SucursalDTO sucursalDTO2 = this.sucursalService.getById(sucursalDTO.getId());

		assertAll("Should return a correct DTO",
				() -> assertNotNull(sucursalDTO2),
				() -> assertEquals(sucursalDTO.getId(), sucursalDTO2.getId()),
				() -> assertEquals(sucursalDTO.getAddress(), sucursalDTO2.getAddress()),
				() -> assertEquals(sucursalDTO.getPoint().getLatitude(), sucursalDTO2.getPoint().getLatitude()),
				() -> assertEquals(sucursalDTO.getPoint().getLongitude(), sucursalDTO2.getPoint().getLongitude()));
	}

	@Test
	void givenSpecificPointWhenSearchByNearnessThenReturnsNearestSucursal() {
		PointDTO peronPotosiPoint = new PointDTO(-34.606171, -58.427457);
		List<SucursalDTO> sucursalDTOList = this.sucursalService.searchByNearness(peronPotosiPoint);
		int size = sucursalDTOList.size();

		assertNotNull(sucursalDTOList);
		assertEquals(4, size);

		SucursalDTO nearestSucursalDTO = sucursalDTOList.get(0);
		SucursalDTO farthestSucursalDTO = sucursalDTOList.get(size - 1);
		assertEquals(nearestSucursalDTO.getAddress(), "Angel Gallardo");
		assertEquals(farthestSucursalDTO.getAddress(), "Malabia");
	}

	@Test
	void givenAnInvalidIDWhenSearchByIDThenThrowsException() {
		assertThrows(ResourceNotFoundException.class, () -> {
			this.sucursalService.getById(Long.valueOf(1000));	
		});
	}

	@Test
	void givenPointWhenSaveThatPointAndSearchByNearnessThenReturnsSamePoint() {
		SucursalDTO sucursalDTO = new SucursalDTO();
		PointDTO point = new PointDTO(-34.606171, -58.427457);

		sucursalDTO.setAddress("Same point");
		sucursalDTO.setPoint(point);

		this.sucursalService.create(sucursalDTO);
		List<SucursalDTO> sucursales = this.sucursalService.searchByNearness(point);

		SucursalDTO actual = sucursales.get(0);

		assertAll("Should return same point in the first position",
				() -> assertNotNull(actual),
				() -> assertEquals(sucursalDTO.getPoint().getLatitude(), actual.getPoint().getLatitude()),
				() -> assertEquals(sucursalDTO.getPoint().getLongitude(), actual.getPoint().getLongitude()));
	}
}
