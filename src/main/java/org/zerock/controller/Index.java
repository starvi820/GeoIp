package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.entity.geoip.Continent;
import org.zerock.entity.geoip.Country;
import org.zerock.entity.geoip.GeoIp;
import org.zerock.service.geoip.GeoIPService;

@Controller
public class Index {
	
	
	@Autowired
	GeoIPService geoipService;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index1() {
		return "/map";
	}
	
	@RequestMapping(value="/geoip",method=RequestMethod.GET)
	public ModelAndView index2() {
		
		ModelAndView mv = new ModelAndView();
			
		List<Continent> continent = new ArrayList<>();
		
		
		continent = geoipService.continentFindAll();
				
		mv.addObject("continent", continent);
		mv.setViewName("/geoip");
		
		
		return mv;
	}
	
	@RequestMapping(value="/maps",method=RequestMethod.GET)
	public String maps() {
		
		return "/maps";
		
	}
	
	
	
	
}
