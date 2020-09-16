package ar.com.fravega.challenge.dto;

public class SucursalDTO implements BaseDTO {

	private Long id;
	private String address;
	private PointDTO point;

	/**
	 * @return the point
	 */
	public PointDTO getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(PointDTO point) {
		this.point = point;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param name the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
