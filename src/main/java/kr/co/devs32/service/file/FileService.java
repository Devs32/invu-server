package kr.co.devs32.service.file;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 및 URL 조회 기능을 제공하는 서비스 인터페이스입니다.
 */
public interface FileService {

	/**
	 * 주어진 파일 맵을 업로드하고 업로드된 파일의 키(Set)를 반환합니다.
	 *
	 * @param fileMap 업로드할 파일의 맵 (키: 파일 이름, 값: MultipartFile 객체)
	 * @return 업로드된 파일의 URL(Set)
	 */
	default Set<String> uploadFileMap(Map<String, MultipartFile> fileMap) {
		return fileMap.entrySet().stream()
			.map(e -> uploadFile(e.getKey(), e.getValue()))
			.collect(Collectors.toSet());
	}

	/**
	 * 여러 개의 파일을 업로드하고 업로드된 파일의 키(List)를 반환합니다.
	 *
	 * @param files 업로드할 파일 리스트
	 * @return 업로드된 파일의 URL(List)
	 */
	default List<String> uploadFiles(List<MultipartFile> files) {
		return files.stream()
			.map(this::uploadFile)
			.toList();
	}

	/**
	 * 단일 파일을 업로드하고 해당 파일의 키를 반환합니다.
	 *
	 * @param file 업로드할 파일
	 * @return 업로드된 파일의 URL
	 */
	default String uploadFile(MultipartFile file) {
		return uploadFile(file.getOriginalFilename(), file);
	}

	/**
	 * 주어진 키와 파일을 업로드하고 해당 파일의 키를 반환합니다.
	 *
	 * @param key  파일을 식별할 키 (파일 이름)
	 * @param file 업로드할 파일
	 * @return 업로드된 파일의 URL
	 */
	String uploadFile(String key, MultipartFile file);

	/**
	 * 주어진 키에 해당하는 파일의 URL을 반환합니다.
	 *
	 * @param key 업로드된 파일의 키
	 * @return 파일의 URL
	 */
	String getFileUrl(String key);
}
