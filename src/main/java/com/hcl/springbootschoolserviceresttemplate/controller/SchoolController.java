package com.hcl.springbootschoolserviceresttemplate.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SchoolController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/get")
	public String getTest() {
		final String uri = "http://localhost:8081/student/all";

		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
		return result;
	}

	
	  @PostMapping(value="/post") 
	  public ResponseEntity<String> postTest(){ 
		  final
	  String uri = "http://localhost:8081/student/test2";
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	  
	  
	  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	  
	  //map.add("email", "first.last@example.com"); Student st = new
	  
	  
	  HttpEntity< MultiValueMap<String, String>> request = new HttpEntity< MultiValueMap<String, String> >(map, headers);
	  
	  ResponseEntity<String> response = restTemplate.postForEntity(uri, request,
	  String.class);
	  
	  System.out.println(response); return response; 
	  }
	 

	@PostMapping(value = "/postwithparam")
	public ResponseEntity<String> postWithParamTest() {
		final String uri = "http://localhost:8081/student/test3";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("firstName", "Nandu");
		map.add("lastName", "Leela");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

		System.out.println(response);
		return response;
	}

	@PostMapping(value = "/pathparam")
	public ResponseEntity<String> restTemplatePathVarTest() {
		final String uri = "http://localhost:8081/student/test4/112";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = restTemplate.postForEntity(uri, null, String.class);
		return response;
	}

	@PostMapping(value = "/reqbody")
	public ResponseEntity<String> restTemplateRebodyTest() throws Exception {
		final String uri = "http://localhost:8081/student/test5";

		JSONObject request = new JSONObject();
		request.put("id", 101);
		request.put("firstName", "usk");
		request.put("lastName", "HYD");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

		ResponseEntity<String> loginResponse = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		return loginResponse;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}


