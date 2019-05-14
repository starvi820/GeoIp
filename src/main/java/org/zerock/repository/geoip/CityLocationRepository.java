package org.zerock.repository.geoip;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.entity.geoip.CityLocation;

public interface CityLocationRepository extends JpaRepository<CityLocation, Integer>{
	
	
	@Query(value="select * from tbl_city_location where city_name=?1",nativeQuery=true)
	List<CityLocation> findCityLocationNameBycityName(String cityName);
	
	
	@Query(value="select * from tbl_city_location where city_location_code=?1",nativeQuery=true)
	List<CityLocation> findByCityLocationCode(Integer cityLocationCode);

}
