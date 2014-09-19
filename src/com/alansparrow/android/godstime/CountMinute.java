package com.alansparrow.android.godstime;

import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;

public class CountMinute extends TimerTask {
	private Activity activity;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//mClockTextView = (TextView) findViewById(R.id.clockTextView);
		MediaPlayer mp = MediaPlayer.create(this.activity, R.raw.s1);
		mp.start();
	}
	
	public CountMinute(Activity activity) {
		this.activity = activity;
	}
}
