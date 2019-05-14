package org.zerock.dto.excel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExcelDto {
	 
	@NotNull
	@Size(min=1,max=100)
	private String startIp;
	
	@NotNull
	@Size(min=1,max=100)
	private String endIp;
	
	@NotNull
	private Long ipStartNumber;
	private Long ipEndNumber;
	
	
	@Size(min=1,max=100)
	private String countryName;
	
	@Size(min=1,max=100)
	private String cityName;
	
	
	
	@NotNull
	@Size(min=1,max=100)
	private String publicIp;
	
	private Boolean isPrivateIp;
	

	@NotNull
	@Size(min=1,max=100)
	private String cityLocationName;
	@NotNull
	@Size(min=1,max=100)
	private String companyName;
	
	

}
