package kr.co.devs32.web.service.file;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 및 URL 조회 기능을 제공하는 서비스 클라이언트 인터페이스입니다.
 */
public interface IFileClient {

	/**
	 * 주어진 키와 파일을 업로드하고 해당 파일의 키를 반환합니다.
	 *
	 * @param key  파일을 식별할 키 (파일 이름)
	 * @param file 업로드할 파일
	 * @return 업로드된 파일의 URL
	 */
	boolean uploadFile(String key, MultipartFile file);

	/**
	 * 주어진 키에 해당하는 파일의 URL을 반환합니다.
	 *
	 * @param key 업로드된 파일의 키
	 * @return 파일의 URL
	 */
	String getFileUrl(String key);
}
