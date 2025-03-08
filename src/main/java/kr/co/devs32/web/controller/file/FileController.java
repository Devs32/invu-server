package kr.co.devs32.web.controller.file;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import kr.co.devs32.service.file.FileService;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/files")
@RequiredArgsConstructor
@RestController
public class FileController {

	private final FileService fileService;

	@GetMapping("/{key}")
	public ResponseEntity<String> getByKey(@PathVariable String key) {
		return ResponseEntity.ok(fileService.getFileUrl(key));
	}

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
		return ResponseEntity.ok(fileService.uploadFiles(files));
	}
}
