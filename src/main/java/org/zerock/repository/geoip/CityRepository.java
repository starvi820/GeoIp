package org.zerock.repository.geoip;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.entity.geoip.City;


public interface CityRepository extends JpaRepository<City, Integer> {
	
	
	@Query(value="select * from tbl_city where country_code=?1" , nativeQuery=true)
	public List<City> cityFindByCountryCode(String countryCode);

	
	
	
}
