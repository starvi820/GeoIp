package org.zerock.controller.excel;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.dto.excel.ExcelDto;
import org.zerock.entity.excel.Excel;
import org.zerock.excel.common.Util;
import org.zerock.repository.geoip.GeoIPRepository;
import org.zerock.service.excel.ExcelService;
import org.zerock.service.geoip.GeoIPService;

@RestController
@SuppressWarnings("resource")
@RequestMapping(value ="/excel")
public class ExcelController {

	@Autowired
	ExcelService excelService;
	@Autowired
	GeoIPRepository geoipRepository;
	@Autowired
	GeoIPService geoipService;

	@RequestMapping(value = "/excelUpload", method = RequestMethod.POST)
	public ModelAndView excelUpload(MultipartHttpServletRequest request) throws Exception {

		MultipartFile excelFile = request.getFile("excel");

		if (excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("파일을 선택해 주세요.");
		
		}

		File destFile = new File("D://" + excelFile.getOriginalFilename());
		try {
			excelFile.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		excelService.excelUpload(destFile);

//		 FileUtils.deleteDirectory(destFile.getAbsolutePath());

		ModelAndView view = new ModelAndView();
		view.setViewName("/map");
		
		return view;

	}

	@RequestMapping(value = "/excelDown", method = RequestMethod.GET)
	public void ExcelDownload(HttpServletResponse response, Model model) throws Exception {
		
		HSSFWorkbook objWorkBook = new HSSFWorkbook();
		HSSFSheet objSheet = null;
		HSSFRow objRow = null;
		HSSFCell objCell = null;

		// HSSFFont font = objWorkBook.createFont();
		// font.setFontHeightInPoints((short) 10);
		// font.setBoldweight((short) font.BOLDWEIGHT_BOLD);
		// font.setFontName("맑은고딕");
		HSSFCellStyle styleHd = objWorkBook.createCellStyle(); // 제목 스타일

		// styleHd.setFont(font);

		styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleHd.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		objSheet = objWorkBook.createSheet("Sheet1"); // 워크시트 생성

		List<Excel> rowList = excelService.excelList();

		objRow = objSheet.createRow(0);
		objRow.setHeight((short) 0x150);

		objCell = objRow.createCell(0);
		objCell.setCellValue("startIp");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell(1);
		objCell.setCellValue("endIp");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell(2);
		objCell.setCellValue("country_name");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell(3);
		objCell.setCellValue("city_name");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(4);
		objCell.setCellValue("city_location_name");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell(5);
		objCell.setCellValue("public_ip");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell(6);
		objCell.setCellValue("private_ip");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(7);
		objCell.setCellValue("company_name");
		objCell.setCellStyle(styleHd);
		
		
		int index = 1;
		for (Excel row : rowList) {
			objRow = objSheet.createRow(index);
			objRow.setHeight((short) 0x150);

			objCell = objRow.createCell(0);
			objCell.setCellValue((String) row.getStartIp());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(1);
			objCell.setCellValue((String) row.getEndIp());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(2);
			objCell.setCellValue((String) row.getCountryName());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(3);
			objCell.setCellValue((String) row.getCityName());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(4);
			objCell.setCellValue((String) row.getCityLocationName());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(5);
			objCell.setCellValue(row.getPublicIp());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(6);
			objCell.setCellValue(row.getIsPrivateIp());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(7);
			objCell.setCellValue(row.getCompanyName());
			objCell.setCellStyle(styleHd);

			index++;
		}

		for (int i = 0; i < rowList.size(); i++) {
			objSheet.autoSizeColumn(i);
		}

		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition",
				"ATTachment; Filename=" + URLEncoder.encode("geoIpList", "UTF-8") + ".xls");

		OutputStream fileOut = response.getOutputStream();
		objWorkBook.write(fileOut);
		fileOut.close();

		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	@RequestMapping(value = "/excelInsert", method = RequestMethod.POST)
	public ResponseEntity<Excel> postExcel(@RequestBody ExcelDto dto, BindingResult result,
			UriComponentsBuilder uriBuilder) {

		if (result.hasErrors()) {

			URI location = uriBuilder.path("/excel/excelInsert").build().toUri();
			return Util.generatePostResult(null, location, HttpStatus.BAD_REQUEST);
		}

		Excel excelIn = new Excel();

		BeanUtils.copyProperties(dto, excelIn);

		String startIp = excelIn.getStartIp();
		String endIp = excelIn.getEndIp();

		String[] startIpAddressInArray = startIp.split("\\.");
		String[] endIpAddressInArray = endIp.split("\\.");

		long resultStartIp = 0;
		long resultEndIp = 0;

		for (int i = 0; i < startIpAddressInArray.length; i++) {
			int power = 3 - i;
			int sIp = Integer.parseInt(startIpAddressInArray[i]);

			resultStartIp += sIp * Math.pow(256, power);

		}

		excelIn.setIpStartNumber(resultStartIp);

		for (int i = 0; i < endIpAddressInArray.length; i++) {
			int power = 3 - i;
			int eIp = Integer.parseInt(endIpAddressInArray[i]);
			resultEndIp += eIp * Math.pow(256, power);

		}
		excelIn.setIpEndNumber(resultEndIp);

		Excel excelCreated = excelService.excelCreate(excelIn);

		URI location = uriBuilder.path("/excel/excelInsert/{id}").buildAndExpand(excelCreated.getIpIndex()).toUri();

		return Util.generatePostResult(excelCreated, location, HttpStatus.CREATED);

	}
	
	@RequestMapping(value="/delete/{ipIndex}" ,method=RequestMethod.DELETE)
	public void deleteExcel(@PathVariable("ipIndex") Integer ipIndex , HttpServletRequest req) {
					
		excelService.deleteExcel(ipIndex);
	}


	@RequestMapping(value = "/excelList", method = {RequestMethod.POST,RequestMethod.GET})
	public List<Excel> listExcel() {

		return excelService.excelList();
	}
		
	@RequestMapping(value="/excelOne/{ipIndex}",method=RequestMethod.GET)
	public Excel getExcel(@PathVariable Integer ipIndex) {
		
		
		return excelService.excelFindOne(ipIndex);
	}

	@RequestMapping(value = "/excelUpdate/{ipIndex}", method = RequestMethod.PUT)
	public Excel updateExcel(@PathVariable Integer ipIndex, @RequestBody ExcelDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new Excel();
		}

		Excel excel = excelService.excelFindOne(ipIndex);
		
		BeanUtils.copyProperties(dto, excel);
		
		
		String startIp = excel.getStartIp();
		String endIp = excel.getEndIp();
		
		
		String [] startIpAddressInArray = startIp.split("\\.");
		String [] endIpAddressInArray = endIp.split("\\.");
		
		long resultStartIp=0;
		long resultEndIp = 0;
		
		
		for(int i =0; i<startIpAddressInArray.length; i++) {
			int power = 3-i;
			int sIp = Integer.parseInt(startIpAddressInArray[i]);
			
			resultStartIp += sIp * Math.pow(256, power);
		}
		
		excel.setIpStartNumber(resultStartIp);
		
		
		for(int i=0; i<endIpAddressInArray.length; i++) {
			int power =3-i;
			int eIp = Integer.parseInt(endIpAddressInArray[i]);
			
			resultEndIp += eIp * Math.pow(256,power);
		}
		
		excel.setIpEndNumber(resultEndIp);
		excel.setIpIndex(ipIndex);

		return excelService.excelUpdate(excel);

	}

	@RequestMapping(value = "/duplication", method = RequestMethod.POST)
	public int getpublicIp(String publicIp) {


		List<Excel> list = excelService.findByPublicIp(publicIp);

		if (list.size() == 0) {
			return -1;

		} else {
			return 1;
		}
	}

	// ip range 체크

	@RequestMapping(value = "/ipRangeChk", method = RequestMethod.POST)
	public int ipRangeCheck( Long ipStartNumber, Long ipEndNumber, String cityName,HttpServletRequest req) {
		
		
		String startIp = req.getParameter("startIp");
		String endIp = req.getParameter("endIp");
		String cityNamed = req.getParameter("cityName");
		
		
		String[] startIpInArray = startIp.split("\\.");
		String[] endIpInArray = endIp.split("\\.");
		
		long ipSnumber =0;
		long ipEnumber =0;
				
		for (int i=0; i<startIpInArray.length;i++) {
			int power =3-i;
			int sIp = Integer.parseInt(startIpInArray[i]);
			
			ipSnumber += sIp * Math.pow(256, power);
			
			
		}
			
		for (int i=0; i<endIpInArray.length;i++) {
			int power = 3-i;
			int eIp = Integer.parseInt(endIpInArray[i]);
			
			ipEnumber += eIp * Math.pow(256, power);
			
			
		}
		
		
		List<Excel> startEndRange = excelService.findByIpStartNumberAndIpEndNumberAndCityName(ipSnumber,ipEnumber,cityNamed);
				
		
		if(startEndRange.size()==0) {
			return 1;
		}else{
			return -1;
		}
		
		
		
	}

	@RequestMapping(value = "/publicIpChk", method = RequestMethod.POST)
	public int publicIpCheck(String publicIp, HttpServletRequest request) {

		String ipRegex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";
		String publicIpCheck = request.getParameter("publicIp");
		
		if (publicIpCheck.matches(ipRegex) == false) {

			return -1;
			
		}else{
			
			return 1;
		}
	}

	@RequestMapping(value = "/startIpChk", method = RequestMethod.POST)
	public int startIpCheck(String startIp, HttpServletRequest request) {
		String ipRegex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		String startIpCheck = request.getParameter("startIp");

		if (startIpCheck.matches(ipRegex) == false) {
			return -1;
		} else {
			return 1;
		}

	}

	@RequestMapping(value = "/endIpChk", method = RequestMethod.POST)
	public int endIpCheck(String endIp, HttpServletRequest request) {

		String ipRegex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		String endIpCheck = request.getParameter("endIp");
		if (endIpCheck.matches(ipRegex) == false) {
			return -1;
		} else {
			return 1;
		}

	}

	@RequestMapping(value = "/cidrChk", method = RequestMethod.POST)
	public Map<String, Object> cidrAddressChk(HttpServletRequest request) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		String cidrInput = request.getParameter("cidrInput");

		SubnetUtils subnetUtils = new SubnetUtils(cidrInput);

		subnetUtils.setInclusiveHostCount(true);

		String resultNetMask = subnetUtils.getInfo().getNetmask();
		String resultNetworkAddress = subnetUtils.getInfo().getNetworkAddress();
		String resultBroadcastAddress = subnetUtils.getInfo().getBroadcastAddress();
		String resultStartIp = subnetUtils.getInfo().getLowAddress();
		String resultEndIp = subnetUtils.getInfo().getHighAddress();
		String[] allAddresses = subnetUtils.getInfo().getAllAddresses();

		result.put("resultNetMask", subnetUtils.getInfo().getNetmask());
		result.put("resultNetworkAddress", subnetUtils.getInfo().getNetworkAddress());
		result.put("resultBroadcastAddress", subnetUtils.getInfo().getBroadcastAddress());
		result.put("resultStartIp", subnetUtils.getInfo().getLowAddress());
		result.put("resultEndIp", subnetUtils.getInfo().getHighAddress());
		result.put("allAddresses", subnetUtils.getInfo().getAllAddresses());
		
		
		System.out.println(allAddresses[0]);

		// System.out.println(cidrInput + "해당 대역의 포함여부: " +
		// subnetUtils.getInfo().isInRange("120.131.5.130"));
		//
		// System.out.println("120.131.5.234 해당 대역의 포함여부: " +
		// subnetUtils.getInfo().isInRange("120.131.5.234"));
		// for (String allAddress : allAddresses) {
		// System.out.println(allAddress);
		// }
		return result;

	}

}
