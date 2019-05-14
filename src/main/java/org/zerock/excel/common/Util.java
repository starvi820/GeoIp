package org.zerock.excel.common;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
	public static <T> ResponseEntity<T> generatePostResult(T entity, URI location, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<T>(entity, headers, status);
	}
}
