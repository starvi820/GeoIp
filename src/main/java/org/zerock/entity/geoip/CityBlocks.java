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
@Table(name="tbl_city_blocks")
@Getter
@Setter
@NoArgsConstructor
public class CityBlocks {
	@Id
	@SequenceGenerator(name="tbl_city_blocks_seq",sequenceName="tbl_city_blocks_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_city_blocks_seq")
	@Column(name="city_id",unique=true,nullable=false)
	private Integer cityId;
	@Column(name="ip_start_number",unique=true,nullable=false)
	private Integer ipStartNumber;
	@Column(name="ip_end_number",unique=true,nullable=false)
	private Integer ipEndNumber;

}
