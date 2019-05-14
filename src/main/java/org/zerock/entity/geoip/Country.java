package org.zerock.entity.geoip;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_country")
@Getter
@Setter
@NoArgsConstructor
public class Country {
	
	@Id
	@SequenceGenerator(name="tbl_country_seq",sequenceName="tbl_country_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_country_seq")
	@Column(name="country_id",unique=true,nullable=false)
	private Integer countryId;
	@Column(name="lang")
	private String lang;
	@Column(name="country_code",nullable=false)
	private String countryCode;
	@Column(name="country_name",nullable=false)
	private String countryName;
	@Column(name="longitude",nullable=false)
	private float longitude;
	@Column(name="latitude",nullable=false)
	private float latitude;
	@Column(name="description")
	private String description;
	@Column(name="reg_date")
	private Date regDate;
	@Column(name="modify_date")
	private Date modifyDate;
	@Column(name="continent_name",nullable=false)
	private String continentName;
	@Column(name="continent_code",nullable=false)
	private String continentCode;

}
