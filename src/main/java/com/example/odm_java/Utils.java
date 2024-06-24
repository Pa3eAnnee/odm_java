package com.example.odm_java;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Utils {
	public static List<Member> readMembersFromFile(String filePath) {
		List<Member> members = new ArrayList<>();

		try (FileReader reader = new FileReader(filePath)) {
			JSONTokener tokener = new JSONTokener(reader);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String ID = jsonObject.getString("ID");
				String ROLE = jsonObject.getString("ROLE");
				String EMAIL = jsonObject.getString("EMAIL");
				String FIRST_NAME = jsonObject.getString("FIRST_NAME");
				String LAST_NAME = jsonObject.getString("LAST_NAME");

				Member member = new Member(ID, ROLE, EMAIL, FIRST_NAME, LAST_NAME);
				members.add(member);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Member member : members) {
			System.out.println(member);
		}
		return members;
	}
}
