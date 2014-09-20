package com.alansparrow.android.godstime;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class GodActivity extends ActionBarActivity {
	
	private BootstrapButton mSetButton;
	private TextView mClockTextView;
	private BootstrapEditText mPeriodEditText;
	private int mMinuteToReset;
	//private Timer mTimer = new Timer();
	//private CountMinute mCounter = new CountMinute(this); 
	private MediaPlayer mp;
	private CountDownTimer mCdt;
	private PowerManager mPowerManager;
	private WakeLock mWakeLock;
	
	private void resetTime(int minutes) {
		// TODO Auto-generated method stub
		/*
		mTimer = new Timer();
		mCounter = new CountMinute(this);
		mTimer.schedule(mCounter, m*60*1000, m*60*1000);
		*/
		countTime(minutes);
	}
	
	private void countTime(int minutes) {
		final int m = minutes;
		
		if (mCdt != null) {
			mCdt.cancel();
		}
		
		mCdt = new CountDownTimer(m*60*1000, 1000) {

		     public void onTick(long millisUntilFinished) {
		         mClockTextView.setText(String.format("%d:%d", millisUntilFinished / (1000*60),
		        		 (millisUntilFinished % (1000*60)) / 1000));
		     }

		     public void onFinish() {
		    	 mp.start();
		         countTime(m);
		     }
		  };
		  
		  mCdt.start();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_god);
		
		
		mp = MediaPlayer.create(this, R.raw.s1);
		
		mClockTextView = (TextView) findViewById(R.id.clockTextView);
		mPeriodEditText = (BootstrapEditText) findViewById(R.id.txtPeriod);
        
        mSetButton = (BootstrapButton) findViewById(R.id.setButton);
        mSetButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mMinuteToReset = Integer.parseInt(
							mPeriodEditText.getText().toString());
				} catch (Exception e) {
					mMinuteToReset = 5;
				}
				resetTime(mMinuteToReset);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.god, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public void onDestroy() {
    	super.onDestroy();
    	mWakeLock.release();
    }
	
	@Override
    public void onStart() {
    	super.onStart();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	mPowerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
    	mWakeLock.acquire();
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    }
	
	
    
}
