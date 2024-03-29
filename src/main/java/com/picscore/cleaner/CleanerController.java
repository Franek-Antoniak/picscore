package com.picscore.cleaner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CleanerController {
	private final CleanerService cleanerService;

	@DeleteMapping("/admin/delete/data")
	public ResponseEntity<String> deleteAllData() {
		cleanerService.clearDataBase();
		return ResponseEntity.status(HttpStatus.OK)
		                     .build();
	}
}
