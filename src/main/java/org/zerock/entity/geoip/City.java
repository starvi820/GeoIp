package org.zerock.entity.geoip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_city")
@Getter
@Setter
@NoArgsConstructor
public class City {
	
	@Id
	@SequenceGenerator(name="tbl_city_seq",sequenceName="tbl_city_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_city_seq")
	@Column(name="city_id",unique=true,nullable=false)
	private Integer cityId;
	@Column(name="country_code",nullable=false)
	private String countryCode;
	@Column(name="city_name",nullable=false)
	private String cityName;
	@Column(name="longitude",nullable=false)
	private float longitude;
	@Column(name="latitude",nullable=false)
	private float latitude;
	
	
	
	

}
