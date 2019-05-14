package org.zerock.entity.geoip;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_geoip")
@Getter
@Setter
@NoArgsConstructor
public class GeoIp {
	
	@Id
	@SequenceGenerator(name="tbl_geoip_seq",sequenceName="tbl_geoip_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_geoip_seq")
	@Column(name="ip_index",unique=true,nullable=false)
	private Integer ipIndex;
	@Column(name="ip_start",nullable=false)
	private String ipStart;
	@Column(name="ip_end",nullable=false)
	private String ipEnd;
	@Column(name="ip_start_number",nullable=false)
	private Long ipStartNumber;
	@Column(name="ip_end_number",nullable=false)
	private Long ipEndNumber;
	@Column(name="country_code")
	private String countryCode;
	@Column(name="country_name")
	private String countryName;
	@Column(name="mac_address")
	private String macAddress;
	@Column(name="is_private_ip",nullable=true)
	private boolean isPrivateIp;
	@Column(name="public_ip")
	private String publicIp;
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="company_id")
	private Integer companyId;
	
	@Column(name="city_location_name")
	private String cityLocationName;
	
	@Column(name="company_name")
	private String companyName;
	
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="city_location_name",insertable=false,updatable=false)
//	private CityLocation cityLocation;

}
