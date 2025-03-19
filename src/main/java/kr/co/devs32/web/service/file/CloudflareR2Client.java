package kr.co.devs32.web.service.file;

import java.net.URI;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.checksums.RequestChecksumCalculation;
import software.amazon.awssdk.core.checksums.ResponseChecksumValidation;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * Cloudflare R2에 파일을 업로드하고 URL을 반환하는 서비스 구현체입니다.
 */
@Component
@Qualifier("cloudflareR2Client")
public class CloudflareR2Client implements IFileClient {

	private final S3Client client;
	private final String bucketName;
	private final String accessUrl;

	public CloudflareR2Client(
		@Value("${cloudflare.r2.endpoint}") String endpoint,
		@Value("${cloudflare.r2.access-key}") String accessKey,
		@Value("${cloudflare.r2.secret-key}") String secretKey,
		@Value("${cloudflare.r2.bucket-name}") String bucketName,
		@Value("${cloudflare.r2.access-url}") String accessUrl) {
		URI endpointUri = URI.create(endpoint);
		AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
		S3Configuration serviceConfiguration = S3Configuration.builder()
			.pathStyleAccessEnabled(true)
			.build();
		this.client = S3Client.builder()
			.credentialsProvider(StaticCredentialsProvider.create(credentials))
			.region(Region.of("auto"))
			.endpointOverride(endpointUri)
			.requestChecksumCalculation(RequestChecksumCalculation.WHEN_REQUIRED)
			.responseChecksumValidation(ResponseChecksumValidation.WHEN_REQUIRED)
			.serviceConfiguration(serviceConfiguration)
			.build();
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
	public boolean uploadFile(String key, MultipartFile file) {
		try {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.contentType(file.getContentType())
				.build();

			client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
			return true;
		} catch (Exception e) {
			// TODO: need define class for reponse
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
