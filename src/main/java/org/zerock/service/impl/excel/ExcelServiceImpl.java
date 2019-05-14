package org.zerock.service.impl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.entity.excel.Excel;
import org.zerock.excel.common.ExcelRead;
import org.zerock.excel.common.ExcelReadOption;
import org.zerock.repository.geoip.ExcelRepository;
import org.zerock.service.excel.ExcelService;


@Service(value="ExcelService")
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	private ExcelRepository excelRepository;

	@Override
	@Transactional
	public void excelUpload(File destFile) {
		
		ExcelReadOption readOption = new ExcelReadOption();
		  readOption.setFilePath(destFile.getAbsolutePath());
		  readOption.setOutputColumns("A","B","C","D","E","F","G");
		  readOption.setStartRow(0);
		  
		  List<Map<String, String>> excelContent = ExcelRead.read(readOption);
		  Excel excel = null;
		  
		  for(Map<String, String> article : excelContent){
			  
			  excel = new Excel();
			  
			//  excel.setCol1(article.get("ip_index"));
			  excel.setStartIp(article.get("A"));
			  excel.setEndIp(article.get("B"));
			  excel.setCountryName(article.get("C"));
			  excel.setCityName(article.get("D"));
			  excel.setCityLocationName(article.get("E"));
			  excel.setPublicIp(article.get("F"));
			  excel.setCompanyName(article.get("G"));
//			  excel.setIsPrivateIp(article.get("F"));
			  
			  excelRepository.save(excel);
			  
		  }
		  
		 
		
	}

	@Override
	public List<Excel> excelDownLoad(Excel excel) {
		return excelRepository.findAll();
	}

	@Override
	public List<Excel> excelList() {
		return excelRepository.findAllByOrderBy();
	}

	@Override
	public Excel excelCreate(Excel excel) {
		return excelRepository.save(excel);
	}

	@Override
	public List<Excel> excelList(Excel excel) {
		return excelRepository.findAll();
	}

	@Override
	public Excel excelUpdate(Excel excel) {
		return excelRepository.save(excel);
	}

	@Override
	public List<Excel> findByPublicIp(String publicIp) {
		return excelRepository.findByPublicIp(publicIp);
	}

	@Override
	public List<Excel> findByCountryName(String countryName) {
		return excelRepository.findByCountryName(countryName);
	}



	

	@Override
	public void deleteExcel(Integer id) {
		excelRepository.deleteById(id);
	}

	@Override
	public List<Excel> findByIpStartNumberAndIpEndNumberAndCityName(Long ipStartNumber, Long ipEndNumber,String cityName) {
		return excelRepository.findByIpStartNumberAndIpEndNumberAndCityName(ipStartNumber, ipEndNumber, cityName);
	}

	@Override
	public Excel excelFindOne(Integer ipIndex) {
		// TODO Auto-generated method stub
		return excelRepository.findByipIndex(ipIndex);
	}

	@Override
	public Excel updateExcel(Excel excel) {
		return excelRepository.save(excel);
	}
	

	


}
