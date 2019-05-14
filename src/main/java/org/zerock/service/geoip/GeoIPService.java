package org.zerock.service.geoip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.entity.geoip.City;
import org.zerock.entity.geoip.CityLocation;
import org.zerock.entity.geoip.Continent;
import org.zerock.entity.geoip.Country;
import org.zerock.entity.geoip.GeoIp;

public interface GeoIPService {
	
	
	//city
	public List<City> cityFindAll();
	public Page<City> cityFindAll(Pageable pageable);
	public City cityFindOne(Integer id);
	public City cityCreate(City city);
	public City cityUpdate(City city);
	public void cityDelete(Integer id);
	
	public List<City> cityFindAllBycountryCode(String countryCode);
	
	public List<GeoIp> geoipFindAllByCityName(String cityName);
	
	//cityBlocks
	
	
	//country
	public List<Country> countryFindAll();
	public Page<Country> countryFindeAll(Pageable pageable);
	public Country countryFindOne(Integer id);
	public Country countryCreate(Country country);
	public Country countryUpdate(Country country);
	public void countryDelete(Integer id);
	
	public List<Country> countryFindByContinentCode(String continentCode);
	
	//continent
	public List<Continent> continentFindAll();
	public Page<Continent> continentFindAll(Pageable pageable);
	public Continent continentFindOne(Integer id);
	public Continent continentCreate(Continent continent);
	public Continent continentUpdate(Continent continent);
	public void continentDelete(Integer id);
	
	//geoip
	public List<GeoIp> geoipFindAll();

	public Page<GeoIp> geoipFindAll(Pageable pageable);
	public GeoIp geoipFindOne(Integer ipIndex);
	public GeoIp geoipFindByIpIndex(Integer ipIndex);
	
	public GeoIp geoipCreate(GeoIp geoip);
	public GeoIp geoipUpdate(GeoIp geoIp);
	public void geoipDelete(Integer id);
	
	//cityLocation
	
	public List<CityLocation> cityLocationFindCityLocationNameByCityName(String cityName);
	
	public List<CityLocation> cityLocationFindByCityLocationCode(Integer cityLocationCode);
	
	public List<Object[]> geoipFindBycityLocation();

}
