package br.com.leitor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Leitor {
	
	private static final Logger LOGGER = Logger.getLogger(Leitor.class.getName());

	private static final Pattern REQUEST = Pattern.compile("request_to=\"([^\"]*)\"");
	private static final Pattern RESPONSE = Pattern.compile("response_status=\"([^\"]*)\"");
	private static final String DELIMITER = "level";

	public WebHookMap extrairDados(String path) {

		Map<String, Integer> requestMap = new HashMap<String, Integer>();
		Map<Integer, Integer> responseMap = new HashMap<Integer, Integer>();
		
		try {
			Scanner sc = new Scanner(new FileReader(path)).useDelimiter(DELIMITER);

			

			Matcher matcherRequest = null;
			Matcher matcherResponse = null;
			String request = null;
			Integer response = null;

			while (sc.hasNext()) {
				String line = sc.nextLine();
				matcherRequest = REQUEST.matcher(line);
				matcherResponse = RESPONSE.matcher(line);
				if (matcherRequest.find()) {
					request = matcherRequest.group(1);
					if (matcherResponse.find()) {
						response = new Integer(matcherResponse.group(1));
					}

					if (requestMap.containsKey(request)) {
						Integer value = requestMap.get(request);
						requestMap.put(request, value + 1);
					} else {
						requestMap.put(request, new Integer(1));
					}

					if (responseMap.containsKey(response)) {
						Integer value = responseMap.get(response);
						responseMap.put(response, value + 1);
					} else {
						responseMap.put(response, new Integer(1));
					}
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Ocorreu um erro ao ler o arquivo= " + path);
			
		}
		return new WebHookMap(requestMap, responseMap);
	}

	public void imprimeDados(WebHookMap webHookMap) {
		
		Map<String, Integer> requestMapSorted = new TreeMap<String, Integer>(new ComparadorValores(webHookMap.getRequestMap()));
		requestMapSorted.putAll(webHookMap.getRequestMap());
		
		Iterator<String> keys = requestMapSorted.keySet().iterator();
		for(int i=0; keys.hasNext() && i < 3; i++){
			String key = keys.next();
			Integer value = webHookMap.getRequestMap().get(key);
			System.out.println(key +" - "+ value);
		}
		
		Iterator<Integer> keyss = webHookMap.getResponseMap().keySet().iterator();
		while( keyss.hasNext()){
			Integer key = keyss.next();
			Integer value = webHookMap.getResponseMap().get( key);
			
			System.out.println(key +" - "+ value);
		}
		
		
	}

}
