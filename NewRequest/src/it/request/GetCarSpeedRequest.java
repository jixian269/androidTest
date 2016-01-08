package it.request;

import org.json.JSONObject;

import it.action.MyAction;
import it.base.BaseRequest;

public class GetCarSpeedRequest extends BaseRequest {

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return "http://192.168.228.18:8080/transportservice/type/jason/action/"+MyAction.GetCarSpeed;
	}

	@Override
	public String getRequestJson() {
		// TODO Auto-generated method stub
		String result = "";
		try {
			result  = new JSONObject().put("CarId", 1).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Object handleResponseJson(String result) {
		// TODO Auto-generated method stub
		String res = "";
		try {
			res = new JSONObject(result).optString("serverinfo");
			res = new JSONObject(res).optInt("CarSpeed", -1)+"";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}


}
