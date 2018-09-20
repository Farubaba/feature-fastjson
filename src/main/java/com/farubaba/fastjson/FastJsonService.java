package com.farubaba.fastjson;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farubaba.json.BaseJsonService;
import com.farubaba.json.JsonType;

public class FastJsonService extends BaseJsonService {
	private static FastJsonService instance = new FastJsonService();
	
	private FastJsonService() {
		
	}
	
	public static FastJsonService getInstance() {
		return instance;
	}
	
	@Override
	public <T> T fromJson(String jsonString, Class<T> classOfT) {
		Object jsonObject = JSON.parse(jsonString);
		if(jsonObject instanceof JSONObject) {
			return JSONObject.parseObject(jsonString, classOfT);
		}
		return null;
	}

	@Override
	public <A> List<A> fromJsonToList(String jsonString, Class<A> classOfA) {
		if(jsonString != null) {
			Object jsonObject = JSON.parse(jsonString);
			if(jsonObject instanceof JSONArray) {
				List<A> list = JSONObject.parseArray(jsonString, classOfA);
				return list;
			}		
		}
		return null;
	}

	
	@Override
	public <E> String toJsonString(E e) {
		return JSON.toJSONString(e);
	}
	
	@Override
	public JsonType getJsonType(String jsonString) {
		JsonType jsonType = new JsonType();
		Object obj = JSON.parse(jsonString);
		if(obj == null) {
			jsonType.setValid(false);
		}else if(obj instanceof JSONArray) {
			jsonType.setArray(true);
		}else if(obj instanceof JSONObject) {
			jsonType.setObject(true);
		}
		//FIXME 还有primitive 和 jsonNull类型
		return jsonType;
	}
	
}
