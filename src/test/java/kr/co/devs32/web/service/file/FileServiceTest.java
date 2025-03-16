package kr.co.devs32.web.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

	@Mock
	private IFileClient client;

	@InjectMocks
	private FileService fileService;

	private MultipartFile file1;
	private MultipartFile file2;

	@BeforeEach
	void setUp() {
		file1 = mock(MultipartFile.class);
		file2 = mock(MultipartFile.class);
	}

	@Test
	void testUploadFileMap() {
		Map<String, MultipartFile> fileMap = new HashMap<>();
		fileMap.put("file1.txt", file1);
		fileMap.put("file2.txt", file2);

		when(client.uploadFile(eq("file1.txt"), any(MultipartFile.class))).thenReturn(true);
		when(client.uploadFile(eq("file2.txt"), any(MultipartFile.class))).thenReturn(false);
		when(client.getFileUrl("file1.txt")).thenReturn("http://example.com/file1.txt");

		Set<String> result = fileService.uploadFileMap(fileMap);

		assertEquals(1, result.size());
		assertTrue(result.contains("http://example.com/file1.txt"));
	}

	@Test
	void testUploadFiles() {
		List<MultipartFile> files = Arrays.asList(file1, file2);
		when(file1.getOriginalFilename()).thenReturn("file1.txt");
		when(file2.getOriginalFilename()).thenReturn("file2.txt");
		when(client.uploadFile(eq("file1.txt"), any(MultipartFile.class))).thenReturn(true);
		when(client.uploadFile(eq("file2.txt"), any(MultipartFile.class))).thenReturn(false);
		when(client.getFileUrl("file1.txt")).thenReturn("http://example.com/file1.txt");

		List<String> result = fileService.uploadFiles(files);

		assertEquals(1, result.size());
		assertEquals("http://example.com/file1.txt", result.get(0));
	}

	@Test
	void testGetFileUrl() {
		when(client.getFileUrl("file1.txt")).thenReturn("http://example.com/file1.txt");
		String url = fileService.getFileUrl("file1.txt");
		assertEquals("http://example.com/file1.txt", url);
	}
}
