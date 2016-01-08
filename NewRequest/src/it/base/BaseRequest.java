package it.base;

import it.thread.NetUtil;
import it.thread.NetUtil.ThreadListener;

public abstract class BaseRequest {
	private NetUtil netUtil;
	protected String url;
	public interface RequestListener{
		public void resultReturn(Object result);
	}
	
	public abstract String getUrl();
	public abstract String getRequestJson();
	public abstract Object handleResponseJson(String result);
	
	public void connection(final RequestListener listener){
		netUtil = new NetUtil();
		netUtil.asynRequest(getUrl(), getRequestJson(), new ThreadListener() {
			
			@Override
			public void seccess(String result) {
				// TODO Auto-generated method stub
				listener.resultReturn(handleResponseJson(result));
			}
			
			@Override
			public void error(String info) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
