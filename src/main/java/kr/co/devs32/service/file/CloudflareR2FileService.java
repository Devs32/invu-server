package kr.co.devs32.service.file;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * Cloudflare R2에 파일을 업로드하고 URL을 반환하는 서비스 구현체입니다.
 */
@Service
@Qualifier("cloudflareR2Service") // 여러 구현체가 있을 경우 구분
public class CloudflareR2FileService implements FileService {

	private final S3Client client;
	private final String bucketName;
	private final String accessUrl;

	/**
	 * Cloudflare R2 파일 서비스의 생성자입니다.
	 *
	 * @param client     Cloudflare R2와 통신할 S3 클라이언트
	 * @param bucketName 업로드할 S3 버킷 이름
	 * @param accessUrl  업로드된 파일의 기본 접근 URL
	 */
	public CloudflareR2FileService(
		@Qualifier("cloudflareR2Client") S3Client client,
		@Value("${cloudflare.r2.bucket-name}") String bucketName,
		@Value("${cloudflare.r2.access-url}") String accessUrl) {
		this.client = client;
		this.bucketName = bucketName;
		this.accessUrl = accessUrl;
	}

	/**
	 * 파일을 Cloudflare R2에 업로드하고 업로드된 파일의 URL을 반환합니다.
	 *
	 * @param key  파일을 식별할 키 (파일 이름)
	 * @param file 업로드할 파일
	 * @return 업로드된 파일의 URL
	 * @throws RuntimeException 파일 업로드 실패 시 예외 발생
	 */
	@Override
	public String uploadFile(String key, MultipartFile file) {
		try {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.contentType(file.getContentType())
				.build();

			client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
			return getFileUrl(key);
		} catch (IOException e) {
			// TDDO: need define class for reponse
			throw new RuntimeException("파일 업로드 실패", e);
		}
	}

	/**
	 * 업로드된 파일의 URL을 반환합니다.
	 *
	 * @param key 업로드된 파일의 키
	 * @return 파일의 접근 가능한 URL
	 */
	@Override
	public String getFileUrl(String key) {
		return String.format("%s/%s", accessUrl, key);
	}
}
