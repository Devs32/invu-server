package kr.co.devs32.web.service.file;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	IFileClient client;

	/**
	 * 주어진 파일 맵을 업로드하고 업로드된 파일의 키(Set)를 반환합니다.
	 *
	 * @param fileMap 업로드할 파일의 맵 (키: 파일 이름, 값: MultipartFile 객체)
	 * @return 업로드된 파일의 URL(Set)
	 */
	public Set<String> uploadFileMap(Map<String, MultipartFile> fileMap) {
		return fileMap.entrySet().stream()
			.map(e -> {
				boolean result = client.uploadFile(e.getKey(), e.getValue());
				if (result) {
					return client.getFileUrl(e.getKey());
				} else  {
					return null;
				}
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());
	}

	/**
	 * 여러 개의 파일을 업로드하고 업로드된 파일의 키(List)를 반환합니다.
	 *
	 * @param files 업로드할 파일 리스트
	 * @return 업로드된 파일의 URL(List)
	 */
	public List<String> uploadFiles(List<MultipartFile> files) {
		if (CollectionUtils.isEmpty(files)) {
			return Collections.emptyList();
		}
		Map<String, MultipartFile> fileMap = files.stream()
			.collect(Collectors.toMap(MultipartFile::getOriginalFilename, Function.identity(), (f1, f2) -> f1));
		return new ArrayList<>(uploadFileMap(fileMap));
	}

	public String getFileUrl(String key) {
		return client.getFileUrl(key);
	}
}
