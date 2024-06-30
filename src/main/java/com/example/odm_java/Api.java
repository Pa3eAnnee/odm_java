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
import java.util.concurrent.ExecutionException;


public class Api {
	private static String url = "http://localhost:3003";

	public static void getMembers() {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url + "/users"))
				.build();

		try {
			CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

			HttpResponse<String> response = responseFuture.get();

			if (response.statusCode() != 200) {
				System.out.println("Couldn't contact services... Switching to offline mode");
				return;
			}

			String responseBody = response.body();

			Path filePath = Path.of("src", "main", "resources", "data", "members.json");
			Files.createDirectories(filePath.getParent());
			Files.writeString(filePath, responseBody, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

		} catch (InterruptedException | ExecutionException | IOException e) {
			System.out.println("Couldn't contact services... Switching to offline mode");
		}
	}
}
