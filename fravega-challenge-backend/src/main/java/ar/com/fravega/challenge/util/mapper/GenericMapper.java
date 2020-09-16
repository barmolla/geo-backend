package ar.com.fravega.challenge.util.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.fravega.challenge.dto.BaseDTO;
import ar.com.fravega.challenge.entity.BaseEntity;

public interface GenericMapper<ENTITY extends BaseEntity, DTO extends BaseDTO> extends Serializable {

	ENTITY createFrom(final DTO dto);

	DTO createFrom(final ENTITY entity);
 
	ENTITY updateEntity(final ENTITY entity, final DTO dto);

	default List<DTO> createFromEntities(final List<ENTITY> entities) {
		return entities.stream()
				.map(this::createFrom)
				.collect(Collectors.toList());
	}
 
	default List<ENTITY> createFromDtos(final List<DTO> dtos) {
		return dtos.stream()
				.map(this::createFrom)
				.collect(Collectors.toList());
	}

}
