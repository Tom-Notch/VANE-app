package com.example.vane0427;

import java.io.*;
import java.net.*;
import java.nio.Buffer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SocketClient
{
	private static final int TCP_SERVER_PORT = 9999;
	private static final String TCP_SERVER_IP = "192.168.137.1";
	private Socket socket;
	private BufferedWriter out;
	private BufferedReader in;

	public SocketClient()
	{
		try
		{
			this.socket = new Socket(TCP_SERVER_IP, TCP_SERVER_PORT);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void disconnect()
	{
		try
		{
			this.socket.close();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void write(String msg)
	{
		try
		{
			this.out.write(msg);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String read()
	{
		String msg = null;
		try
		{
			msg = this.in.readLine();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return msg;
	}
}
