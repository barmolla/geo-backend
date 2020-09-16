package ar.com.fravega.challenge.entity;

import javax.persistence.Column;

public class Point extends BaseEntity {

	private static final long serialVersionUID = -7296302348486823458L;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;
}
