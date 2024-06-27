package com.example.odm_java;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlanningController extends Controller implements Initializable {

	@FXML
	private CalendarView calendarView;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Calendar events = new Calendar("Évènement");
		Calendar meeting = new Calendar("Réunion");

		events.setStyle(Calendar.Style.STYLE1);
		meeting.setStyle(Calendar.Style.STYLE2);

		List<Entry<String>> entries = readEntriesFromFile("entries.json");
		for (Entry<String> entry : entries) {
			events.addEntry(entry);
		}

		CalendarSource myCalendarSource = new CalendarSource("Calendars");
		myCalendarSource.getCalendars().addAll(events, meeting);

		calendarView.getCalendarSources().addAll(myCalendarSource);

		calendarView.setRequestedTime(LocalTime.now());

		Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
			@Override
			public void run() {
				while (true) {
					Platform.runLater(() -> {
						calendarView.setToday(LocalDate.now());
						calendarView.setTime(LocalTime.now());
					});

					try {
						sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		updateTimeThread.setPriority(Thread.MIN_PRIORITY);
		updateTimeThread.setDaemon(true);
		updateTimeThread.start();
	}

	public static List<Entry<String>> readEntriesFromFile(String filename) {
		String filePath = new File("src/main/resources/com/example/odm_java/mock_data/" + filename).getAbsolutePath();
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
