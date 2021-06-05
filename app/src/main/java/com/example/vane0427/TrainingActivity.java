package com.example.vane0427;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import veg.mediacapture.sdk.MediaCapture;
import veg.mediacapture.sdk.MediaCaptureCallback;
import veg.mediacapture.sdk.MediaCaptureConfig;
import veg.mediacapture.sdk.MediaCaptureConfig.CaptureModes;
import veg.mediacapture.sdk.MediaCaptureConfig.CaptureVideoResolution;

public class TrainingActivity extends AppCompatActivity implements MediaCaptureCallback
{
	private TextView textView;
	private Button mButtonStart;
	private final static String TAG = "TrainingActivity";
	private MediaCapture capturer;
	private MediaCaptureConfig mConfig;
	private static TrainingActivity sTrainingActivity;
	private int mOldMsg = 0;
	private final Toast toastShot = null;
	private boolean misSurfaceCreated = false;
	private MulticastLock multicastLock = null;
	private static boolean isPlaying = false;
	public int response;
	Socket socket = null;
	public PrintWriter out;
	public BufferedReader in;
	private static final int TCP_SERVER_PORT = 9999;
	private static final String TCP_SERVER_IP = "192.168.137.1"; // server ip is here
	boolean isTime = false;
	int totalCount = 0;
	int time = 0;
	int num = 0;
	int order = 0;
	int type = 0;
	private static final int[] times = {1, 2, 3, 4, 5, 6, 8, 10, 20, 30, 40, 50, 60, 90, 120, 180, 300};
	private static final int[] nums = {3, 4, 6, 8, 10, 12, 15, 16, 18, 20, 24, 30, 32, 36, 40, 50};


	@Override
	public int OnCaptureStatus(int arg)
	{
		return 0;
	}

	@Override
	public int OnCaptureReceiveData(ByteBuffer buffer, int type, int size, long pts) { return 0; }

	private void PlayAudio(int index, long delay)
	{
		isPlaying = true;
		MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), index);
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(delay);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				mediaPlayer.start();
				//Log.i(TAG, mediaPlayer.isPlaying()+" ");

				while (mediaPlayer.isPlaying()) ;
				isPlaying = false;
				//Log.i(TAG, mediaPlayer.isPlaying()+" ");
			}
		}).start();
		//return true;
	}

	@SuppressLint("SourceLockedOrientationActivity")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training);
		sTrainingActivity = this;
		Log.v(TAG, "=>onCreate MediaCapture::getLibVersion()=" + MediaCapture.getLibVersion());
		getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		capturer = (MediaCapture) findViewById(R.id.captureView);

		configureRTSP();

		MediaCapture.RequestPermission(this, mConfig.getCaptureSource());

		capturer.Open(null, this);
		Bundle bundle = getIntent().getExtras();
		time = bundle.getInt("Time");
		num = bundle.getInt("Number");
		order = bundle.getInt("Order");
		type = bundle.getInt("Type");
		isTime = (time < 0 || type == 2) ? false : true;
		Log.e(TAG, "onCreate: timeindex = " + time);
		Log.e(TAG, "onCreate: numberIndex = " + num);
		Log.e(TAG, "onCreate: orderIndex = " + order);
		Log.e(TAG, "onCreate: typeIndex = " + type);
		Log.e(TAG, "onCreate: isTime = " + isTime);
		mButtonStart = findViewById(R.id.btn_start);
		mButtonStart.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				capturer.Start();
				PlayAudio(R.raw.turn_left, 0);
				Socket socket = null;
				Thread c = new Client();
				c.start();
			}
		});
	}

	// configuration of RTSP parameters
	private void configureRTSP()
	{
		if (capturer == null)
			return;

		mConfig = capturer.getConfig();

		int ncm = mConfig.getCaptureMode();
		ncm &= ~(CaptureModes.PP_MODE_AUDIO.val());

		mConfig.setStreaming(true);
		mConfig.setCaptureMode(ncm);
		mConfig.setStreamType(MediaCaptureConfig.StreamerTypes.STREAM_TYPE_RTSP_SERVER.val());

		mConfig.setAudioFormat(MediaCaptureConfig.TYPE_AUDIO_AAC);
		mConfig.setAudioBitrate(64);
		mConfig.setAudioSamplingRate(44100); //hardcoded
		mConfig.setAudioChannels(2);

		mConfig.setUrl(MediaCaptureConfig.StreamerTypes.STREAM_TYPE_RTSP_SERVER.val(), "rtsp://@:5554");
		mConfig.setUseSec(false);

		mConfig.setVideoOrientation(90); //portrait
		mConfig.setCaptureSource(MediaCaptureConfig.CaptureSources.PP_MODE_CAMERA.val()); // front camera
		mConfig.setVideoFramerate(30); // 10 fps for better performance
		mConfig.setVideoBitrate(5000);
		mConfig.setVideoKeyFrameInterval(1);
		mConfig.setVideoBitrateMode(MediaCaptureConfig.BITRATE_MODE_ADAPTIVE);
		mConfig.setCameraFacing(MediaCaptureConfig.CAMERA_FACING_FRONT);
		mConfig.setVideoResolution(CaptureVideoResolution.VR_1920x1080);

		mConfig.setVideoMaxLatency(100);

		mConfig.setRecording(false);

		mConfig.setTranscoding(true);
		mConfig.setTransWidth(320);
		mConfig.setTransHeight(240);
		mConfig.setTransFps(1);
		mConfig.setTransFormat(MediaCaptureConfig.TYPE_VIDEO_RAW);
		mConfig.setTransFormat(String.valueOf(MediaCaptureConfig.StreamerTypes.STREAM_TYPE_RTSP_SERVER));
	}

	protected void onPause()
	{
		Log.e(TAG, "onPause()");
		super.onPause();
		if (capturer != null)
			capturer.onPause();
	}

	@Override
	protected void onResume()
	{
		Log.e(TAG, "onResume()");
		super.onResume();
		if (capturer != null)
			capturer.onResume();
	}

	@Override
	protected void onStart()
	{
		Log.e(TAG, "onStart()");
		super.onStart();
		sTrainingActivity = this;
		// Lock screen mWakeLock.acquire();
		if (capturer != null)
			capturer.onStart();
	}

	@Override
	protected void onStop()
	{
		if (socket != null) out.println("stop");
		Log.e(TAG, "onStop() send stop");
		super.onStop();
		if (capturer != null)
			capturer.onStop();
		// A WakeLock should only be released when isHeld() is true ! if (mWakeLock.isHeld()) mWakeLock.release();
		if (toastShot != null)
			toastShot.cancel();
		if (misSurfaceCreated)
		{
			finish();
		}
	}

	@Override
	public void onBackPressed()
	{
		if (toastShot != null)
			toastShot.cancel();
		if (capturer != null)
			capturer.Close();
		super.onBackPressed();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		Log.e(TAG, "onWindowFocusChanged(): " + hasFocus);
		super.onWindowFocusChanged(hasFocus);
		if (capturer != null)
			capturer.onWindowFocusChanged(hasFocus);
	}

	@Override
	public void onLowMemory()
	{
		Log.e(TAG, "onLowMemory()");
		super.onLowMemory();
		//		if (capturer != null)
		//			capturer.onLowMemory();
	}

	@Override
	protected void onDestroy()
	{
		if (socket != null) out.println("stop");
		Log.e(TAG, "onDestroy: send stop");

		Log.e(TAG, "onDestroy()");
		if (toastShot != null)
			toastShot.cancel();
		if (capturer != null)
			capturer.onDestroy();
		System.gc();
		if (multicastLock != null)
		{
			multicastLock.release();
			multicastLock = null;
		}
		capturer.Stop();
		super.onDestroy();
	}

	// for socket connection decode
	public String intToStringUTF8(int data)
	{
		byte[] bytes = new byte[1];
		bytes[0] = (byte) data;
		return new String(bytes, Charset.forName("UTF-8"));
	}

	class Client extends Thread
	{
		public void run()
		{
			int totalCount = (order == 0) ? 0 : nums[num];
			try
			{
				socket = new Socket(TCP_SERVER_IP, TCP_SERVER_PORT);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String startMsg = null;
				switch (type)
				{
					default:
					case 0:
						startMsg = "s";
						break;

					case 1:
						startMsg = "p";
						break;

					case 2:
						startMsg = "h";
						break;
				}
				Log.e(TAG, "run: startMsg = " + startMsg);
				out.println(startMsg);
				long timeStamp = System.currentTimeMillis();
				while (true)
				{
					if (in.ready())
					{
						response = in.read();
						String msg = intToStringUTF8(response);
						Log.i(TAG, "run: reponse = " + intToStringUTF8(response));

						if (msg.contains("+"))
						{
							if (order == 0) ++totalCount;
							else --totalCount;
							Log.i(TAG, "run: total Count = " + totalCount);
						}
						else
						{
							switch (msg)
							{
								case "a":
									if (type == 0) PlayAudio(R.raw.s_e_a, 0);
									else if (type == 1) PlayAudio(R.raw.p_e_a, 0);
									else if (type == 2) PlayAudio(R.raw.h_e_a, 0);
									break;

								case "b":
									if (type == 0) PlayAudio(R.raw.s_e_b, 0);
									else if (type == 1) PlayAudio(R.raw.p_e_b, 0);
									else if (type == 2) PlayAudio(R.raw.h_e_b, 0);
									break;

								case "c":
									PlayAudio(R.raw.s_e_c, 0);
									break;

								default:
									break;
							}
						}

						if (totalCount == 5)
							PlayAudio(R.raw.num_5, 0);
						else if (totalCount == 10)
							PlayAudio(R.raw.num_10, 0);
						else if (totalCount == 15)
							PlayAudio(R.raw.num_15, 0);
						else if (totalCount == 20)
							PlayAudio(R.raw.num_20, 0);
						else if (totalCount == 25)
							PlayAudio(R.raw.num_25, 0);
						else if (totalCount == 30)
							PlayAudio(R.raw.num_30, 0);

						if (isTime && System.currentTimeMillis() - timeStamp > times[time] * 1000)
						{
							PlayAudio(R.raw.time_is_up, 0);
							break;
						}
						else if (!isTime && totalCount - nums[num] == 1)
							PlayAudio(R.raw.one_left, 0);
						else if (!isTime && totalCount >= nums[num])
						{
							PlayAudio(R.raw.cong, 0);
							break;
						}

					}
					Intent intent = new Intent(TrainingActivity.this, Home2Activity.class);
					startActivity(intent);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}