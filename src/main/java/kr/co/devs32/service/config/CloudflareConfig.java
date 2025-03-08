package kr.co.devs32.service.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class CloudflareConfig {
	@Bean(name = "cloudflareR2Client")
	public S3Client cloudflareR2Client(
		@Value("${cloudflare.r2.endpoint}") String endpoint,
		@Value("${cloudflare.r2.access-key}") String accessKey,
		@Value("${cloudflare.r2.secret-key}") String secretKey) {

		URI endpointUri = URI.create(endpoint);
		AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

		return S3Client.builder()
			.credentialsProvider(StaticCredentialsProvider.create(credentials))
			.region(Region.of("auto"))
			.endpointOverride(endpointUri)
			.build();
	}

}
