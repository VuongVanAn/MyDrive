package net.codejava.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiExchangeService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public<T> T get(String url, Class<T> classType, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>(headers);
		return restTemplate.exchange(url, HttpMethod.GET, entity, classType).getBody();
	}
	
	public <T> T post(String url, Object object, Class<T> classType, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>(object, headers);
		return restTemplate.exchange(url, HttpMethod.POST, entity, classType).getBody();
	}

	public <T> T postNonToken(String url, Object object, Class<T> classType) {
		return restTemplate.postForEntity(url, object, classType).getBody();
	}

	public void put(String url, Object object, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>(object, headers);
		restTemplate.put(url, entity);
	}

	public void delete(String url, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, entity, void.class);
	}

}
