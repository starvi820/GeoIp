package org.zerock.entity.geoip;

import java.util.Date;

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
@Table(name="tbl_continent")
@Getter
@Setter
@NoArgsConstructor
public class Continent {
	
	@Id
	@SequenceGenerator(name="tbl_continent_seq",sequenceName="tbl_continent_seq,",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_continent_seq")
	@Column(name="continent_id",unique=true,nullable=false)
	private Integer continentId;
	@Column(name="lang")
	private String lang;
	@Column(name="continent_code")
	private String continentCode;
	@Column(name="continent_name")
	private String continentName;
	@Column(name="longitude")
	private float longitude;
	@Column(name="latitude")
	private float latitude;
	@Column(name="description")
	private String description;
	@Column(name="reg_date")
	private Date regDate;
	@Column(name="modify_date")
	private Date modifyDate;

}
