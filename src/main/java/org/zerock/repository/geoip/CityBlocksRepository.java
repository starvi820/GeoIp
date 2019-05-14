package org.zerock.repository.geoip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.entity.geoip.CityBlocks;

public interface CityBlocksRepository extends JpaRepository<CityBlocks, Integer> {

	
}
