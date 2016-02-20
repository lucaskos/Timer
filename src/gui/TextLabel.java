package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TextLabel extends JLabel implements ActionListener {
	private boolean isRunning;
	private long startTime;
	private long pauseTime;
	private Timer timer = new javax.swing.Timer(1000, this);
	private long initTime = System.currentTimeMillis();
	private int time;

	public TextLabel() {
		this.setText(getCurrentTime(System.currentTimeMillis() - initTime));
	}

	public void start() {
		if (isRunning == false) {
			startTime = System.currentTimeMillis();
		} else {
			startTime = System.currentTimeMillis() - (pauseTime - startTime);
		}

		isRunning = true;
		timer.start();
	}

	public void reset() {
		startTime = 0;
		this.pause();
		isRunning = false;
		this.setText(getCurrentTime(System.currentTimeMillis() - System.currentTimeMillis()));
	}

	public void pause() {
		pauseTime = System.currentTimeMillis();
		time = (int) ((pauseTime - startTime) / 1000);
		timer.stop();
	}

	public void actionPerformed(ActionEvent e) {
		setText(getCurrentTime(System.currentTimeMillis() - startTime));
	}

	private String getCurrentTime(long time) {
		return myFormat(time);
	}

	public String getTimeOfActionEnd() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(pauseTime);
	}

	public String getTimeOfActionStart() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(startTime);

	}

	public int getSeconds() {
		return time;
	}

	private String myFormat(final long time) {
		final long hr = TimeUnit.MILLISECONDS.toHours(time);
		final long min = TimeUnit.MILLISECONDS.toMinutes(time - TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.MILLISECONDS
				.toSeconds(time - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
		final long ms = TimeUnit.MILLISECONDS.toMillis(
				time - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
		return String.format("%02d:%02d:%02d", hr, min, sec);
	}
}