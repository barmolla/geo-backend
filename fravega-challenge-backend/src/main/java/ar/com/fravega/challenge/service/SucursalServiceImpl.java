package ar.com.fravega.challenge.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.fravega.challenge.dto.PointDTO;
import ar.com.fravega.challenge.dto.SucursalDTO;
import ar.com.fravega.challenge.dto.SucursalRelativeDistance;
import ar.com.fravega.challenge.entity.Sucursal;
import ar.com.fravega.challenge.exception.ResourceNotFoundException;
import ar.com.fravega.challenge.repository.SucursalRepository;
import ar.com.fravega.challenge.util.mapper.SucursalMapper;

@Service
public class SucursalServiceImpl implements SucursalService {

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private SucursalMapper sucursalMapper;

	@Autowired
	@Qualifier("LawOfCosines")
	private DistanceCalculatorStrategy distanceCalculator;

	@Override
	public List<SucursalDTO> getAll() {
		List<Sucursal> findAll = this.sucursalRepository.findAll();

		return this.sucursalMapper.createFromEntities(findAll);
	}

	@Override
	public SucursalDTO update(Long id, SucursalDTO dto) {
		Sucursal sucursalToUpdate = this.findById(id);
		Sucursal sucursalUpdated = this.sucursalMapper.updateEntity(sucursalToUpdate, dto);
		Sucursal sucursalUpdatedPersisted = this.sucursalRepository.save(sucursalUpdated);

		return this.sucursalMapper.createFrom(sucursalUpdatedPersisted);
	}

	private Sucursal findById(Long id) {
		return this.sucursalRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sucursal not found on :: " + id));
	}

	@Override
	public SucursalDTO create(SucursalDTO dto) {
		Sucursal createdFromDTO = this.sucursalMapper.createFrom(dto);
		Sucursal savedEntity    = this.sucursalRepository.save(createdFromDTO);

		return this.sucursalMapper.createFrom(savedEntity);
	}

	@Override
	public SucursalDTO getById(Long id) throws ResourceNotFoundException {
		return this.sucursalMapper.createFrom(this.findById(id));
	}

	@Override
	public List<SucursalDTO> searchByNearness(PointDTO point) {
		List<Sucursal> sucursales = this.sucursalRepository.findAll();
		List<SucursalRelativeDistance> sucursalesAux = new ArrayList<>();

		for (Sucursal sucursal : sucursales) {
			PointDTO pointDTO = new PointDTO(sucursal.getLatitude(), sucursal.getLongitude());
			SucursalRelativeDistance sucursalWithDistance = new SucursalRelativeDistance();

			sucursalWithDistance.setSucursal(sucursal);
			sucursalWithDistance.setDistance(this.distanceCalculator.getDistance(point, pointDTO));

			sucursalesAux.add(sucursalWithDistance);
		}

		sucursalesAux.sort(new Comparator<SucursalRelativeDistance>() {
			@Override
			public int compare(SucursalRelativeDistance o1, SucursalRelativeDistance o2) {
				if (o1.getDistance() == o2.getDistance()) {
					return 0;

				} else if (o1.getDistance() > o2.getDistance()) {
					return 1;

				} else {
					return -1;
				}
			}
		});

		return this.sucursalMapper.createFromEntities(sucursalesAux.stream().map(sAux -> sAux.getSucursal()).collect(Collectors.toList()));
	}

	@Override
	public void deleteById(Long id) throws ResourceNotFoundException {
		Sucursal sucursalToDelete = this.findById(id);

		this.sucursalRepository.delete(sucursalToDelete);
	}
}
