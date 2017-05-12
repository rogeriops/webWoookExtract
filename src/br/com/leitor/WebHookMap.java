package br.com.leitor;


import java.util.Map;

public class WebHookMap {
	
	private Map<String, Integer> requestMap; 
	
	private Map<Integer, Integer> responseMap;
	
	
	public WebHookMap(Map<String, Integer> requestMap, Map<Integer, Integer> responseMap) {
		super();
		this.requestMap = requestMap;
		this.responseMap = responseMap;
	}

	public Map<String, Integer> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Integer> requestMap) {
		this.requestMap = requestMap;
	}

	public Map<Integer, Integer> getResponseMap() {
		return responseMap;
	}

	public void setResponseMap(Map<Integer, Integer> responseMap) {
		this.responseMap = responseMap;
	}

	
}
