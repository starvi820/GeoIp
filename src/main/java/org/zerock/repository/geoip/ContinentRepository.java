package org.zerock.repository.geoip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.entity.geoip.Continent;


public interface ContinentRepository extends JpaRepository<Continent, Integer> {
	
	
	

}
