package com.gmf.ws.utils;

import com.google.gson.Gson;

public class GMFUtility {

	public String convertToJSON(Object data) {
		Gson g = new Gson();
		StringBuffer s = new StringBuffer("");
		s.append(g.toJson(data));
		return s.toString();
	}
	
}
