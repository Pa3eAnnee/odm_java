package com.example.odm_java;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class Api {
	private static String url = "http://localhost:3003";

	public static void getMembers() throws IOException, IOException {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url + "/users"))
			.build();

		CompletableFuture<String> responseBodyFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body);

		String responseBody = responseBodyFuture.join();

		Path filePath = Path.of("src", "main", "resources", "data", "members.json");
		Files.createDirectories(filePath.getParent());
		Files.writeString(filePath, responseBody, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
