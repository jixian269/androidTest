package it.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import android.os.Handler;


public class NetUtil{
	private Handler handler;
	public interface ThreadListener{
		public void seccess(String result);
		public void error(String info);
	}
	
	public NetUtil() {
		// TODO Auto-generated constructor stub
		handler = new Handler();
	}
	
	public void asynRequest(final String path,final String params,final ThreadListener threadListener){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String result = "";
				try {
					result = startRequest(path, params);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					threadListener.error(e+"");
					e.printStackTrace();
				}
				threadListener.seccess(result);
				
			}
		}).start();
	}
	
	public String startRequest(String path,String params) throws IOException{
		String result = "";
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			setRequestParams(conn);
			setRequestData(conn,params);
			result = getResponseData(conn);
			System.out.println(url+":::"+params);
			System.out.println(result);
			return result;
	}

	private String getResponseData(HttpURLConnection conn) throws IOException {
		String result = "";
		// TODO Auto-generated method stub
		if(conn.getResponseCode()==200){
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] bys  = new byte[1024];
			int len = 0;
			while((len=is.read(bys))!=-1){
				bos.write(bys, 0, len);
			}
			result = new String(bos.toByteArray());
		}
		return result;
	}

	private void setRequestData(HttpURLConnection conn, String params) throws IOException {
		// TODO Auto-generated method stub
		OutputStream os = conn.getOutputStream();
		os.write(params.getBytes());
		os.flush();
	}

	private void setRequestParams(HttpURLConnection conn) throws ProtocolException {
		// TODO Auto-generated method stub
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
		conn.setRequestMethod("POST");
		conn.setReadTimeout(5000);
		conn.setConnectTimeout(3000);
		conn.setDoInput(true);
		conn.setDoOutput(true);
	}

	public void asynsRequest(String url, String requestJson, final ThreadListener threadListener) {
		// TODO Auto-generated method stub
		asynRequest(url, requestJson, new ThreadListener() {
			
			@Override
			public void seccess(final String result) {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						threadListener.seccess(result);
					}
				});
			}
			
			@Override
			public void error(final String info) {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						threadListener.error(info);
					}
				});
			}
		});
	}

}
