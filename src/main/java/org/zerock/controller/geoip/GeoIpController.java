package org.zerock.controller.geoip;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.dto.geoip.CityDto;
import org.zerock.dto.geoip.ContinentDto;
import org.zerock.dto.geoip.CountryDto;
import org.zerock.dto.geoip.GeoIpDto;
import org.zerock.entity.geoip.City;
import org.zerock.entity.geoip.CityLocation;
import org.zerock.entity.geoip.Continent;
import org.zerock.entity.geoip.Country;
import org.zerock.entity.geoip.GeoIp;
import org.zerock.excel.common.PageMaker;
import org.zerock.excel.common.PageVo;
import org.zerock.excel.common.Util;
import org.zerock.repository.geoip.GeoIPRepository;
import org.zerock.service.geoip.GeoIPService;

@RestController
@RequestMapping("/geoip")
public class GeoIpController {
	
	@Autowired
	private GeoIPService geoipService;
	
	
	//city
	@RequestMapping(value="/city",method=RequestMethod.GET)
	public List<City> listCity(){
		return geoipService.cityFindAll();
	}
	
	/*@RequestMapping(value="/city",method=RequestMethod.GET)
	public Page<City> listCity(@PageableDefault Pageable pageable){
		return geoipService.cityFindAll(pageable);
	}*/
	
	@RequestMapping(value="/city/{id}",method=RequestMethod.GET)
	public City getCity(@PathVariable Integer id) {
		return geoipService.cityFindOne(id);
	}
	
	@RequestMapping(value="/city" , method=RequestMethod.POST)
	public ResponseEntity<City> postCity(@RequestBody @Validated CityDto dto , BindingResult result,UriComponentsBuilder uriBuilder ){
		if(result.hasErrors()) {
			URI location = uriBuilder.path("/map/city").build().toUri();
			return Util.generatePostResult(null, location, HttpStatus.BAD_REQUEST);
		}
		
		City cityIn = new City();
		
		BeanUtils.copyProperties(dto, cityIn);
		
		City cityCreated = geoipService.cityCreate(cityIn);
		URI location = uriBuilder.path("/map/city/{id}").buildAndExpand(cityCreated.getCityId()).toUri();
		
		return Util.generatePostResult(cityCreated, location, HttpStatus.CREATED);
			
	}
	
	@RequestMapping(value="/city/{id}",method=RequestMethod.PUT)
	public City putCity(@PathVariable Integer id,@RequestBody @Validated CityDto dto,BindingResult result) {
		if(result.hasErrors()) {
			return new City();
		}
		
		City city = new City();
		BeanUtils.copyProperties(dto, city);
		
		city.setCityId(id);
		return geoipService.cityUpdate(city);
	}
	
	@RequestMapping(value="/city/{id}",method=RequestMethod.DELETE)
	public void deleteCity(@PathVariable Integer id) {
		geoipService.cityDelete(id);
	}
	


	@RequestMapping(value="/cityFind/{countryCode}",method=RequestMethod.GET)
	public List<City> listCityByName(@PathVariable String countryCode,HttpServletRequest req){
		return geoipService.cityFindAllBycountryCode(countryCode);
	}
	
	
//	country
	@RequestMapping(value="/country",method=RequestMethod.GET)
	public List<Country> listCountry(){
		return geoipService.countryFindAll();
	}
	
	
	
	@RequestMapping(value="/country/{continentCode}",method=RequestMethod.GET)
	public List<Country> listCountryByName(@PathVariable String continentCode,HttpServletRequest req){
		return geoipService.countryFindByContinentCode(continentCode);
	}
	
	/*@RequestMapping(value="/city",method=RequestMethod.GET)
	public Page<City> listCity(@PageableDefault Pageable pageable){
		return geoipService.cityFindAll(pageable);
	}*/
	
//	@RequestMapping(value="/country/{id}",method=RequestMethod.GET)
//	public Country getCountry(@PathVariable Integer id) {
//		return geoipService.countryFindOne(id);
//	}
//	
//	@RequestMapping(value="/country",method=RequestMethod.POST)
//	public ResponseEntity<Country> postCountry (@RequestBody @Validated CountryDto dto,BindingResult result,UriComponentsBuilder uriBuilder ){
//		
//		if(result.hasErrors()) {
//			URI location = uriBuilder.path("/map/country").build().toUri();
//			return Util.generatePostResult(null, location, HttpStatus.BAD_REQUEST);
//		}
//		
//		Country countryIn = new Country();
//		
//		BeanUtils.copyProperties(dto, countryIn);
//		countryIn.setRegDate(new Date());
//		
//		Country countryCreated = geoipService.countryCreate(countryIn);
//		URI location = uriBuilder.path("/map/country/{id}").buildAndExpand(countryCreated.getCountryId()).toUri();
//		
//		return Util.generatePostResult(countryCreated, location, HttpStatus.CREATED);
//				
//	}
//	
//	
//	@RequestMapping(value="/country/{id}",method=RequestMethod.PUT)
//	public Country putCountry (@PathVariable Integer id , @RequestBody @Validated CountryDto dto,BindingResult result) {
//		if(result.hasErrors()) {
//			return new Country();
//		}
//		
//		Country country = new Country();
//		BeanUtils.copyProperties(dto, country);
//		
//		country.setCountryId(id);
//		country.setModifyDate(new Date());
//		return geoipService.countryUpdate(country);
//				
//	}
//	
//	@RequestMapping(value="/country/{id}",method=RequestMethod.DELETE)
//	public void deleteCountry(@PathVariable Integer id) {
//		geoipService.countryDelete(id);
//	}
	
	//continent
	@RequestMapping(value="/continent",method=RequestMethod.GET)
	public List<Continent> listContinent(){
		return geoipService.continentFindAll();
	}
	
	@RequestMapping(value="/continent/{id}",method=RequestMethod.GET)
	public Continent getContinent(@PathVariable Integer id) {
		return geoipService.continentFindOne(id);
	}
	
	
	@RequestMapping(value="/continent",method=RequestMethod.POST)
	public ResponseEntity<Continent> postContinent(@RequestBody @Validated ContinentDto dto , BindingResult result,UriComponentsBuilder uriBuilder ){
		if(result.hasErrors()) {
			URI location = uriBuilder.path("/map/continent").build().toUri();
			return Util.generatePostResult(null, location, HttpStatus.BAD_REQUEST);
		}
		
		Continent continentIn = new Continent();
		
		BeanUtils.copyProperties(dto, continentIn);
		continentIn.setRegDate(new Date());
		
		Continent continentCreated = geoipService.continentCreate(continentIn);
		URI location = uriBuilder.path("/map/continent/{id}").buildAndExpand(continentCreated.getContinentId()).toUri();
		
		return Util.generatePostResult(continentCreated, location, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/continent/{id}",method=RequestMethod.PUT)
	public Continent putContinent(@PathVariable Integer id , @RequestBody @Validated ContinentDto dto,BindingResult result) {
		if(result.hasErrors()) {
			return new Continent();
		}
		
		Continent continent = new Continent();
		BeanUtils.copyProperties(dto, continent);
		
		continent.setModifyDate(new Date());
		return geoipService.continentUpdate(continent);
	}
	
	@RequestMapping(value="/continent/{id}",method=RequestMethod.DELETE)
	public void deleteContinent (@PathVariable Integer id) {
		geoipService.continentDelete(id);
	}
	
	//geoip
	
//	@RequestMapping(value="/geoipList",method=RequestMethod.GET)
//	public List<GeoIp> listGeoIp(){
//		return geoipService.geoipFindAll();
//	}
	
	@RequestMapping(value="/geoipOne/{ipIndex}", method=RequestMethod.GET)
	public GeoIp getGeoIp(@PathVariable Integer ipIndex) {
				
		return geoipService.geoipFindByIpIndex(ipIndex);
	}
	
	
	@RequestMapping(value="/geoip",method=RequestMethod.POST)
	public ResponseEntity<GeoIp> postGeoIp(@RequestBody @Validated GeoIpDto dto,BindingResult result,UriComponentsBuilder uriBuilder ){
		if(result.hasErrors()) {
			URI location = uriBuilder.path("/map/geoip").build().toUri();
			return Util.generatePostResult(null, location, HttpStatus.BAD_REQUEST);
		}
		
		GeoIp geoIpIn = new GeoIp();
		
		BeanUtils.copyProperties(dto, geoIpIn);
		
		GeoIp geoIpCreated = geoipService.geoipCreate(geoIpIn);
		URI location = uriBuilder.path("/map/geoip/{id}").buildAndExpand(geoIpCreated.getIpIndex()).toUri();
		
		return Util.generatePostResult(geoIpCreated, location, HttpStatus.CREATED);
		
	}
	
	
	@RequestMapping(value="/geoipUpdate/{id}",method=RequestMethod.PUT)
	public GeoIp putGeoIp (@PathVariable Integer id , @RequestBody GeoIpDto dto,BindingResult result) {
		if(result.hasErrors()) {
			return new GeoIp();
		}
		
		GeoIp geoIp = geoipService.geoipFindByIpIndex(id);
				
		BeanUtils.copyProperties(dto, geoIp);
		
		String startIp = geoIp.getIpStart();
		String endIp = geoIp.getIpEnd();
		
		
		String [] startIpAddressInArray = startIp.split("\\.");
		String [] endIpAddressInArray = endIp.split("\\.");
		
		long resultStartIp=0;
		long resultEndIp = 0;
		
		
		for(int i =0; i<startIpAddressInArray.length; i++) {
			int power = 3-i;
			int sIp = Integer.parseInt(startIpAddressInArray[i]);
			
			resultStartIp += sIp * Math.pow(256, power);
		}
		
		geoIp.setIpStartNumber(resultStartIp);
		
		
		for(int i=0; i<endIpAddressInArray.length; i++) {
			int power =3-i;
			int eIp = Integer.parseInt(endIpAddressInArray[i]);
			
			resultEndIp += eIp * Math.pow(256,power);
		}
		
		geoIp.setIpEndNumber(resultEndIp);
		geoIp.setIpIndex(id);
				
		
		return geoipService.geoipUpdate(geoIp);
		
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void deleteGeoIp (@PathVariable Integer id ){
		 geoipService.geoipDelete(id);
		 
	}
	
		
	@RequestMapping(value="/geoipByCityName/{cityName}",method=RequestMethod.GET)
	public List<GeoIp> geoipByCityName(@PathVariable String cityName){
		
		return geoipService.geoipFindAllByCityName(cityName);
	}
	
	@RequestMapping(value="/geoipLst/{cityName}",method=RequestMethod.GET)
	public List<GeoIp> geoipsByCityName(@PathVariable String cityName){
						
		return geoipService.geoipFindAllByCityName(cityName);
	}
	
	
	@RequestMapping(value="/geoipLst",method=RequestMethod.GET)
	public List<GeoIp> geoipsByFindAll(){
		
		return geoipService.geoipFindAll();
	}
	
	
//	@RequestMapping(value="/geoipLst",method=RequestMethod.GET)
//	public String geoipsByFindAllPage(Pageable pageable , Model model){
//		
//		Page<GeoIp> geoipPage = geoipService.geoipFindAll(pageable);
//		model.addAttribute("geoipPage", geoipPage);
//		
//		
//		return "geoip";
//	}
//	
	
	@RequestMapping(value="/cityLocation/{cityName}",method=RequestMethod.GET)
	public List<CityLocation> cityLocationFindBycityName(@PathVariable String cityName){
		
		return geoipService.cityLocationFindCityLocationNameByCityName(cityName);
		
	}
	
	@RequestMapping(value="/cityLocationGeo/{cityLocationCode}",method=RequestMethod.GET)
	public List<CityLocation> cityLocationFindBycityLocationName(@PathVariable Integer cityLocationCode){
		
		return geoipService.cityLocationFindByCityLocationCode(cityLocationCode);
		
	}
	
	@RequestMapping(value="/cityLocationName",method=RequestMethod.GET)
	public List<Object[]> geoipFindBycityLocationName(){
		return geoipService.geoipFindBycityLocation();
	}
	
	


}
