package org.zerock.entity.excel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_geoip")
@Getter
@Setter
@NoArgsConstructor
public class Excel {
	
	@Id
	@SequenceGenerator(name="tbl_geoip_seq",sequenceName="tbl_geoip_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_geoip_seq")
	@Column(name="ip_index",unique=true,nullable=false)
	private Integer ipIndex;
	@Column(name="ip_start")
	private String startIp;
	@Column(name="ip_end")
	private String endIp;
	@Column(name="ip_start_number")
	private Long ipStartNumber;
	@Column(name="ip_end_number")
	private Long ipEndNumber;
	@Column(name="country_code")
	private String countryCode;
	@Column(name="country_name")
	private String countryName;
	@Column(name="city_name")
	private String cityName;
	@Column(name="mac_address")
	private String macAddress;
	@Column(name="is_private_ip")
	private Boolean isPrivateIp;
	@Column(name="public_ip")
	private String publicIp;
	@Column(name="company_id")
	private Integer companyId;
	@Column(name="city_location_name")
	private String cityLocationName;
	@Column(name="company_name")
	private String companyName;
	
	
	
}
