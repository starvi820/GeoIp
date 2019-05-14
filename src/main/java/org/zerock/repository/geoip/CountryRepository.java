package org.zerock.repository.geoip;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.entity.geoip.Country;


public interface CountryRepository extends JpaRepository<Country, Integer> {

	
	@Query(value="select * from tbl_country where continent_code=?1" , nativeQuery=true)
	public List<Country> findByCountryCode(String continentCode);
	
}
