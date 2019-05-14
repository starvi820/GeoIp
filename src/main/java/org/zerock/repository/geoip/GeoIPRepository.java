package org.zerock.repository.geoip;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.entity.geoip.GeoIp;

public interface GeoIPRepository extends JpaRepository<GeoIp, Integer> {

	
	@Query(value="select * from tbl_geoip order by ip_index",nativeQuery=true)
	public List<GeoIp> geoipFindAllByOrderBy();
	
	
	@Query(value="select * from tbl_geoip where city_name=?1",nativeQuery=true)
	public List<GeoIp> geoipFindAllByCityName(String cityName);
	
	
	@Query(value = "select * from tbl_geoip where ip_index=?1",nativeQuery=true)
	public GeoIp geoipFindByIpIndex(Integer ipIndex);
	
		
	@Query(value="select longitude,latitude,company_name from tbl_geoip g inner join tbl_city_location c on c.city_location_name=g.city_location_name",nativeQuery=true)
	public List<Object[]> geoipFindByCityLocationName();
	
	
}
