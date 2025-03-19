package kr.co.devs32.web.service.file;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@ExtendWith(MockitoExtension.class)
class CloudflareR2ClientTest {

	@Mock
	private S3Client s3Client;

	@Mock
	private MultipartFile file;

	private CloudflareR2Client cloudflareR2Client;

	private final String bucketName = "test-bucket";
	private final String accessUrl = "http://example.com";

	@BeforeEach
	void setUp() {
		cloudflareR2Client = spy(new CloudflareR2Client(
			"http://localhost:9000",
			"test-access-key",
			"test-secret-key",
			bucketName,
			accessUrl
		));
		ReflectionTestUtils.setField(cloudflareR2Client, "client", s3Client);
	}

	@Test
	void testUploadFile_Success() throws Exception {
		String key = "test-file.txt";
		byte[] fileBytes = "test content".getBytes();

		when(file.getContentType()).thenReturn("text/plain");
		when(file.getBytes()).thenReturn(fileBytes);
		when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(null);

		boolean result = cloudflareR2Client.uploadFile(key, file);
		assertTrue(result);
	}

	@Test
	void testUploadFile_Failure() throws Exception {
		String key = "test-file.txt";
		when(file.getBytes()).thenThrow(new RuntimeException("File error"));

		RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
			cloudflareR2Client.uploadFile(key, file);
		});
		assertEquals("파일 업로드 실패", thrown.getMessage());
	}

	@Test
	void testGetFileUrl() {
		String key = "test-file.txt";
		String expectedUrl = accessUrl + "/" + key;
		assertEquals(expectedUrl, cloudflareR2Client.getFileUrl(key));
	}
}
