package org.zerock.entity.geoip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_city_location")
@Getter
@Setter
@NoArgsConstructor
public class CityLocation {
	
	@Id
	@SequenceGenerator(name="tbl_city_location_seq",sequenceName="tbl_city_location_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_city_location_seq")
	@Column(name="index",unique=true,nullable=false)
	private Integer index;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="city_name")
	private String cityName;
	@Column(name="city_location_name")
	private String cityLocationName;
	@Column(name="city_location_code")
	private Integer cityLocationCode;
	
	@Column(name="longitude" , nullable=true)
	private Float longitude;
	@Column(name="latitude" , nullable=true)
	private Float latitude;

}
