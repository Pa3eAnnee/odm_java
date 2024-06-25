package com.example.odm_java;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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


	public static List<Entry<String>> readEntriesFromFile(String filePath) {
		List<Entry<String>> entries = new ArrayList<>();

		try (FileReader reader = new FileReader(filePath)) {
			JSONTokener tokener = new JSONTokener(reader);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String title = jsonObject.getString("title");
				LocalDate startDate = LocalDate.parse(jsonObject.getString("startDate"));
				LocalTime startTime = LocalTime.parse(jsonObject.getString("startTime"));
				LocalDate endDate = LocalDate.parse(jsonObject.getString("endDate"));
				LocalTime endTime = LocalTime.parse(jsonObject.getString("endTime"));
				ZoneId zoneId = ZoneId.of(jsonObject.getString("zoneId"));
				String recurrenceRule = jsonObject.optString("recurrenceRule", null);

				Interval interval = new Interval(startDate, startTime, endDate, endTime, zoneId);
				Entry<String> entry = new Entry<>(title);
				entry.setInterval(interval);

				if (recurrenceRule != null && !recurrenceRule.isEmpty()) {
					entry.setRecurrenceRule(recurrenceRule);
				}

				entries.add(entry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entries;
	}
}
