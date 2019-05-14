package org.zerock.service.impl.geoip;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.entity.geoip.City;
import org.zerock.entity.geoip.CityLocation;
import org.zerock.entity.geoip.Continent;
import org.zerock.entity.geoip.Country;
import org.zerock.entity.geoip.GeoIp;
import org.zerock.repository.geoip.CityLocationRepository;
import org.zerock.repository.geoip.CityRepository;
import org.zerock.repository.geoip.ContinentRepository;
import org.zerock.repository.geoip.CountryRepository;
import org.zerock.repository.geoip.GeoIPRepository;
import org.zerock.service.geoip.GeoIPService;


@Service
@Transactional
public class GeoIPServiceImpl implements GeoIPService {
	
	
	@Autowired
	private ContinentRepository continentRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private GeoIPRepository geoipRepository;
	@Autowired
	private CityLocationRepository cityLocationRepository;

//city
	@Override
	public List<City> cityFindAll() {
		return cityRepository.findAll();
	}

	@Override
	public Page<City> cityFindAll(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}

	@Override
	public City cityFindOne(Integer id) {
		return cityRepository.getOne(id);
	}

	@Override
	public City cityCreate(City city) {
		return cityRepository.save(city);
	}

	@Override
	public City cityUpdate(City city) {
		return cityRepository.save(city);
	}

	@Override
	public void cityDelete(Integer id) {
		
		cityRepository.deleteById(id);
	}

	
	//country
	@Override
	public List<Country> countryFindAll() {
		return countryRepository.findAll();
	}

	@Override
	public Page<Country> countryFindeAll(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}

	@Override
	public Country countryFindOne(Integer id) {
		return countryRepository.getOne(id);
	}

	@Override
	public Country countryCreate(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public Country countryUpdate(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public void countryDelete(Integer id) {
		countryRepository.deleteById(id);
	}

	@Override
	public List<Continent> continentFindAll() {
		return continentRepository.findAll();
	}

	@Override
	public Page<Continent> continentFindAll(Pageable pageable) {
		return continentRepository.findAll(pageable);
	}

	@Override
	public Continent continentFindOne(Integer id) {
		return continentRepository.getOne(id);
	}

	@Override
	public Continent continentCreate(Continent continent) {
		return continentRepository.save(continent);
	}

	@Override
	public Continent continentUpdate(Continent continent) {
		return continentRepository.save(continent);
	}

	@Override
	public void continentDelete(Integer id) {
		
		continentRepository.deleteById(id);
		
	}

	@Override
	public List<GeoIp> geoipFindAll() {
		return geoipRepository.geoipFindAllByOrderBy();
	}

	@Override
	public Page<GeoIp> geoipFindAll(Pageable pageable) {
		return geoipRepository.findAll(pageable);
	}

	@Override
	public GeoIp geoipFindOne(Integer ipIndex) {
		return geoipRepository.getOne(ipIndex);
	}

	@Override
	public GeoIp geoipCreate(GeoIp geoip) {
		return geoipRepository.save(geoip);
	}

	@Override
	public GeoIp geoipUpdate(GeoIp geoIp) {
		return geoipRepository.save(geoIp);
	}

	@Override
	public void geoipDelete(Integer id) {
		
		geoipRepository.deleteById(id);
		
	}

	@Override
	public List<Country> countryFindByContinentCode(String continentCode) {
		return countryRepository.findByCountryCode(continentCode);
	}

	@Override
	public List<City> cityFindAllBycountryCode(String countryCode) {
		return cityRepository.cityFindByCountryCode(countryCode);
	}

	@Override
	public List<GeoIp> geoipFindAllByCityName(String cityName) {
		return geoipRepository.geoipFindAllByCityName(cityName);
	}

	@Override
	public GeoIp geoipFindByIpIndex(Integer ipIndex) {
		return geoipRepository.geoipFindByIpIndex(ipIndex);
	}

	@Override
	public List<CityLocation> cityLocationFindCityLocationNameByCityName(String cityName) {
		// TODO Auto-generated method stub
		return cityLocationRepository.findCityLocationNameBycityName(cityName);
	}

	@Override
	public List<CityLocation> cityLocationFindByCityLocationCode(Integer cityLocationCode) {
		// TODO Auto-generated method stub
		return cityLocationRepository.findByCityLocationCode(cityLocationCode);
	}

	@Override
	public List<Object[]> geoipFindBycityLocation() {
		// TODO Auto-generated method stub
		return geoipRepository.geoipFindByCityLocationName();
	}


	

}
