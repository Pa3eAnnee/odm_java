package com.example.odm_java;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		CalendarView calendarView = new CalendarView(); // (1)

		Calendar events = new Calendar("Évènement"); // (2)
		Calendar meeting = new Calendar("Réunion");


		events.setStyle(Style.STYLE1); // (3)
		meeting.setStyle(Style.STYLE2);

		LocalDate startDate = LocalDate.of(2024, 6, 25);
		LocalTime startTime = LocalTime.of(10, 45);
		LocalDate endDate = LocalDate.of(2024, 6, 25);
		LocalTime endTime = LocalTime.of(11, 45);

		// Step 2: Create a ZoneId instance
		ZoneId zoneId = ZoneId.systemDefault(); // You can specify a different time zone if needed

		// Step 3: Create the Interval instance
		Interval interval = new Interval(startDate, startTime, endDate, endTime, zoneId);

		// Step 4: Create an Entry and set the Interval
		Entry<String> entry = new Entry<>("Dentist Appointment");
		entry.setInterval(interval);
		entry.setRecurrenceRule("RRULE:FREQ=DAILY;INTERVAL=2;"); // Optional: Set a recurrence rule

		String filePath = new File("src/main/resources/com/example/odm_java/mock_data/entries.json").getAbsolutePath();
		List<Entry<String>> entries = Utils.readEntriesFromFile(filePath);
		for (Entry<String> e : entries) {
			events.addEntry(e);
		}
//		events.addEntry(entry);

		CalendarSource myCalendarSource = new CalendarSource("My Calendars"); // (4)
		myCalendarSource.getCalendars().addAll(events, meeting);

		calendarView.getCalendarSources().addAll(myCalendarSource); // (5)

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
						// update every 10 seconds
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

		Scene scene = new Scene(calendarView);
		primaryStage.setTitle("Calendar");
		primaryStage.setScene(scene);
		primaryStage.setWidth(1300);
		primaryStage.setHeight(1000);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
