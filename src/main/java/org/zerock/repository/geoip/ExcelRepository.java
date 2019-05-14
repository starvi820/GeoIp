package org.zerock.repository.geoip;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.entity.excel.Excel;


public interface ExcelRepository extends JpaRepository<Excel, Integer> {

//	
//	@Query("select count(*) from tbl_geoip where ip_start= :ip_start")
//	public Excel getExcelFindbyStartIp (String StartIp);	
	
//	@Query("select count(*) from tbl_geoip where ip_start =:ip_start")
	List<Excel> findByPublicIp(String publicIp);

	
	List<Excel> findByCountryName(String countryName);
		
	
	List<Excel> findByStartIp(String startIp);
	List<Excel> findByEndIp(String endIp);
	
		
	@Query(value="select * from tbl_geoip where ip_start_number<=?1 AND ip_end_number>=?2 AND city_name=?3",nativeQuery=true)
	List<Excel> findByIpStartNumberAndIpEndNumberAndCityName(Long ipStartNumber, Long ipEndNumber , String cityName);
	
	
	@Query(value="select * from tbl_geoip where ip_index=?1",nativeQuery=true)
	Excel findByipIndex(Integer ipIndex);
	
	@Query(value="select * from tbl_geoip order by ip_index",nativeQuery=true)
	public List<Excel> findAllByOrderBy();

}
