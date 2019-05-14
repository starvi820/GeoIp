package org.zerock.excel.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.repository.geoip.ExcelRepository;
import org.zerock.service.excel.ExcelService;

public class ExcelWrite {
	
	private static String downloadPath ="D:\\";
	
	private static Sheet sheet;
	private static int rowIndex;
	

	
	
	public static File excelWrite(ExcelWriteOption writeOption) {
		
			
			
			Workbook wb = ExcelFileType.getWorkbook(writeOption.getFileName());
	        sheet = wb.createSheet(writeOption.getSheetName());
	        
	        setTitle(writeOption.getTitles());
	        setContents(writeOption.getContents());
	        FileOutputStream fos = null;
	        
	        try {
	            fos = new FileOutputStream(downloadPath + writeOption.getFileName());
	            wb.write(fos);
	            
	        } catch (IOException e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	        finally {
	            if(fos != null) {
	                try {
	                    fos.flush();
	                    fos.close();
	                } catch (IOException e) {}
	            }
	        }
	        
	        return getFile(writeOption.getFileName());
	    }
	    
	    private static void setTitle(List<String> values) {
	        Row row = null;
	        Cell cell = null;
	        int cellIndex = 0;
	        if( values != null && values.size() > 0 ) {
	            row = sheet.createRow(rowIndex++);
	            for(String value : values) {
	                cell = row.createCell(cellIndex++);
	                cell.setCellValue(value);
	            }
	        }
	        
	    }
	    
	    private static void setContents(List<String[]> values) {
	        
	        Row row = null;
	        Cell cell = null;
	        
	        int cellIndex = 0;
	        
	        if( values != null && values.size() > 0 ) {
	            
	            for(String[] arr : values) {
	                row = sheet.createRow(rowIndex++);
	                cellIndex = 0;
	                for(String value : arr) {
	                    cell = row.createCell(cellIndex++);
	                    cell.setCellValue(value);
	                }
	            }
	        }
	        
	    }
	    
	    private static File getFile(String fileName) {
	        return new File(downloadPath + fileName);
	    }
	    
//	    public static void remove(File file) {
//	        FileUtils.deleteFile(file.getAbsolutePath());
//	    }
	    
	    
	    
//	    public static void main(String[] args) {
//	        ExcelWriteOption wo = new ExcelWriteOption();
//	        wo.setSheetName("Test");
//	        wo.setFileName("test.xlsx");
//	        List<String> titles = new ArrayList<String>();
//	        titles.add("Title1");
//	        titles.add("Title2");
//	        titles.add("Title3");
//	        wo.setTitles(titles);
//	        
//	        List<String[]> contents = new ArrayList<String[]>();
//	        contents.add(new String[]{"1", "2", "3"});
//	        contents.add(new String[]{"11", "22", "33"});
//	        contents.add(new String[]{"111", "222", "333"});
//	        wo.setContents(contents);
//	        
//	        ExcelWrite.write(wo);
//}
	        
	    
	  
	    
	    
	}
