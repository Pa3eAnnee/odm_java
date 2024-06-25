package com.example.odm_java;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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

		List<Entry<String>> entries = Utils.readEntriesFromFile("entries.json");
		for (Entry<String> entry : entries) {
			events.addEntry(entry);
		}

		CalendarSource myCalendarSource = new CalendarSource("My Calendars");
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
}
