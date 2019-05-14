package org.zerock.service.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.zerock.dto.excel.ExcelDto;
import org.zerock.entity.excel.Excel;

public interface ExcelService {

	public void excelUpload(File destFile);

	public List<Excel> excelDownLoad(Excel excel);

	public List<Excel> excelList();

	public Excel excelCreate(Excel excel);

	public List<Excel> excelList(Excel excel);

	public Excel excelUpdate(Excel excel);

	public List<Excel> findByPublicIp(String publicIp);
	
	public Excel excelFindOne(Integer ipIndex);
	
	
	public Excel updateExcel (Excel excel);
	
	//----------------------IP 체크-------------------------------------//
	
	public List<Excel> findByCountryName(String countryName);
	
	public List<Excel> findByIpStartNumberAndIpEndNumberAndCityName(Long ipStartNumber , Long ipEndNumber ,String cityName);
	
	public void deleteExcel (Integer id);
	
	
}
